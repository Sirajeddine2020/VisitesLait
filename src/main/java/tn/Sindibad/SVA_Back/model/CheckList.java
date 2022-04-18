package tn.Sindibad.SVA_Back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float hygV;
    private float hygC;
    private float hygCp;
    private float etS;
    private String hygObs;
    private float eauCh;
    private float pD;
    private float ustensite;
    private float prop;
    private String netObs;
    private float hRecp;
    private float repLait;
    private float filtLait;
    private float contTem;
    private float colLait;
    private float agelait;
    private float material;
    private String centreObs;
    private float cloture;
    private float toilette;
    private float vestiare;
    private float salleRep;
    private float lab;
    private float salleStockage;
    private float murs;
    private float moust;
    private float flux;
    private String locObs;
    private float nuisible;
    private float dechet;
    private String nuigObs;


















}
