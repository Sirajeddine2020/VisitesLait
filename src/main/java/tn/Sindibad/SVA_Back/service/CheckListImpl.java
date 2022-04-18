package tn.Sindibad.SVA_Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.Sindibad.SVA_Back.model.CheckList;
import tn.Sindibad.SVA_Back.repository.CheckListRepository;

import java.util.List;

@Service
public class CheckListImpl implements CheckListService{
    @Autowired
    CheckListRepository checkListRepository;
    public CheckList addCheckList(CheckList ck){
        return checkListRepository.save(ck);
    }
    public List<CheckList> getAllCheckList(){
        return checkListRepository.findAll();
    }
    public float calculHp(long id){
        CheckList checkL=checkListRepository.getById(id);
        float s=(checkL.getHygV()*2+checkL.getHygC()*1+checkL.getHygCp()*1+checkL.getEtS()*1)/5;
        return  s;
    }
    public float calculNd(long id){
        CheckList checkL=checkListRepository.getById(id);
        float s=checkL.getEauCh()*3+checkL.getPD()*3+checkL.getUstensite()*1+checkL.getProp()*3/10;
        return  s;
    }
    public float calculPc(long id){
        CheckList checkL=checkListRepository.getById(id);
        float s=checkL.getHRecp()*3+checkL.getRepLait()*2+checkL.getFiltLait()*2+checkL.getContTem()*2+checkL.getColLait()*3
                +checkL.getAgelait()*3+checkL.getMaterial()*3/18;
        return  s;
    }
    public float calculCl(long id){
        CheckList checkL=checkListRepository.getById(id);
        float s=(checkL.getCloture()*1+checkL.getToilette()*2+checkL.getVestiare()*2+checkL.getSalleRep()*3+checkL.getLab()*3
                +checkL.getSalleStockage()*3+checkL.getMurs()*2+checkL.getMoust()*2+checkL.getFlux()*1)/19;
        return  s;
    }
    public float calculNui(long id){
        CheckList checkL=checkListRepository.getById(id);
        float s=(checkL.getNuisible()*1+checkL.getDechet()*1)/2;
        return  s;
    }
    public CheckList retrieveCheckList(String id){
        CheckList ck;
        ck=checkListRepository.findById(Long.parseLong(id)).orElse(null);
        return ck;
    }
}
