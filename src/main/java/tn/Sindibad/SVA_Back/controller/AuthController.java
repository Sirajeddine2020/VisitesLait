package tn.Sindibad.SVA_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.Sindibad.SVA_Back.model.*;
import tn.Sindibad.SVA_Back.repository.ConfirmationTokenRepository;
import tn.Sindibad.SVA_Back.repository.RoleRepository;
import tn.Sindibad.SVA_Back.repository.UserRepository;
import tn.Sindibad.SVA_Back.request.LoginRequest;
import tn.Sindibad.SVA_Back.request.SignupRequest;
import tn.Sindibad.SVA_Back.response.JwtResponse;
import tn.Sindibad.SVA_Back.response.MessageResponse;
import tn.Sindibad.SVA_Back.security.JwtUtils;
import tn.Sindibad.SVA_Back.service.EmailSenderService;
import tn.Sindibad.SVA_Back.service.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
     AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_AgentDeVisite)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else
        {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "agentdevisite":
                        Role agentRole = roleRepository.findByName(ERole.ROLE_ResponsableAppro)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(agentRole);
                        break;
                    default:
                        Role responsableRole = roleRepository.findByName(ERole.ROLE_AgentDeVisite)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(responsableRole);
                }
            });
       }
        user.setRoles(roles);
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("darinet953@gmail.com");
        mailMessage.setText("To connect to the platform your username is "+user.getUsername()+" and your password is "+signUpRequest.getPassword()+" To confirm your account, please click here : "
                +"https://localhost:44392/Registration/ConfirmRegistration?token={"+confirmationToken.getConfirmationToken()+"}");

        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    //http://localhost:8080/api/auth/confirm-account?token="58255f35-be01-4244-81ba-523829ff9673"
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {   String msg="";
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken.substring(1,confirmationToken.length()-1));

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            confirmationTokenRepository.delete(token);
            msg="accountVerified";
        }
        else
        {
            msg="message,The link is invalid or broken!";
        }

        return msg;
    }
    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody User u) {
        if (userRepository.existsByEmail(u.getEmail())){
            User user = userRepository.findByEmail(u.getEmail());
            //Create token
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            //save it
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Reset Password!");
            mailMessage.setFrom("darinet953@gmail.com");
            mailMessage.setText("To reset your password please click here: "
                    +"https://localhost:44392/Registration/ResetPassword?token={"+confirmationToken.getConfirmationToken()+"}");
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok(new MessageResponse("you have received an email to reset password!"));

        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email does not exist"));
    }
    @RequestMapping(value="/reset-password", method= {RequestMethod.GET})
    public String resetPassword(@RequestParam("token")String passwordToken)
    {   String msg="";
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(passwordToken.substring(1,passwordToken.length()-1));

        if(token != null)
        {   User user = userRepository.findByEmail(token.getUser().getEmail());
            confirmationTokenRepository.delete(token);
            msg="you can reset your password";
        }
        else
        {
            msg="message,The link is invalid or broken!";
        }
        return msg;

    }
    @PostMapping("/new-password")
    @ResponseBody
    public String modifyUser(@RequestBody User u) {
        String msg="";
        User user= userRepository.findByEmail(u.getEmail());
    user.setPassword(encoder.encode(u.getPassword()));
    userRepository.save(user);
    msg="password updated";

        return  msg;
    }
}
