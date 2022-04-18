package tn.Sindibad.SVA_Back.service;

import tn.Sindibad.SVA_Back.model.Reportt;

import java.util.List;

public interface ReportService {
    Reportt addReport(Reportt r);
    //List<Reportt> retrieveAllReports();
    //void deleteReport(String id);
    //Reportt updateUser(Reportt u);
    Reportt retrieveReport(String id);
}
