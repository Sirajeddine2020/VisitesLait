package tn.Sindibad.SVA_Back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "reports")
public class Reportt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String code;
    @Column(name="Date")
    private LocalDateTime date;
    private double quantity;
    private double density;
    private boolean alcoholTest;
    private float acidity;
    private float ph;
    private double mg;
    private double protein;
    private double lactose;
    private double esd;
    private float wetting;
    private double antibiotic;
    private int nt;
    @Size(max = 20)
    private String startTimeReception;
    @Size(max = 20)
    private String endTimeReception;
    @Size(max = 100)
    private String observation;
    private String photo;
    private String propTanklait;
    private String propBR;
    private String propFR;
    private String propCB;
    private String propMs;
    private String propEC;
    private String propAlcalin;
    private String propAcide;
    private String propAutre;
    private String propObs;
    private String cDensity;
    private String cTestA;
    private String cAntib;
    private String cTraceAl;
    private String cEnreg;
    private String cObs;
    private String metHR;
    private String metR;
    private String metAl;
    private String metCt;
    private String methObservation;
    @OneToMany(cascade =CascadeType.ALL,mappedBy = "report")
    private Set<FileDB> rpFiles;








}
