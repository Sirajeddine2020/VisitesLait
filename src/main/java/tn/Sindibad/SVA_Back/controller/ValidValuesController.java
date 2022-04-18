package tn.Sindibad.SVA_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.Sindibad.SVA_Back.model.ValidValues;
import tn.Sindibad.SVA_Back.repository.ValidValuesRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/validValues")
public class ValidValuesController {
    @Autowired
    ValidValuesRepository validValuesRepository;
    @PutMapping("/update")
    @ResponseBody
    public ValidValues updateValidValues(@RequestBody ValidValues v)
    {
        return validValuesRepository.save(v);
    }
    @GetMapping("/get/{value-id}")
    @ResponseBody
    public Optional<ValidValues>getValidValues(@PathVariable ("value-id") Long id){
        return  validValuesRepository.findById(id);
    }
}
