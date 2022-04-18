package tn.Sindibad.SVA_Back.service;
import tn.Sindibad.SVA_Back.model.CheckList;
import java.util.List;


public interface CheckListService {
    CheckList addCheckList(CheckList ck);
    List<CheckList> getAllCheckList();
    float calculHp(long id);
    float calculNd(long id);
    float calculPc(long id);
    float calculCl(long id);
    float calculNui(long id);
    CheckList retrieveCheckList(String id);
}
