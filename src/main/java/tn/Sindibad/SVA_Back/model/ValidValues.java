package tn.Sindibad.SVA_Back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "validValues")
public class ValidValues implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double densityAcceptable;
    private double minOrange;
    private double maxOrange;
    private double mgRed;
    private float acidityMax;
    private float acidityMin;


}
