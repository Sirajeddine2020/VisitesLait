package tn.Sindibad.SVA_Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.Sindibad.SVA_Back.model.Reportt;
import tn.Sindibad.SVA_Back.model.User;
import tn.Sindibad.SVA_Back.repository.ReportRepository;

import java.time.LocalDateTime;

@Service
public class ReportServiceImpl implements ReportService  {
    @Autowired
    ReportRepository reportRepository;

    public Reportt addReport(Reportt r) {
        r.setDate(LocalDateTime.now());

        return reportRepository.save(r);
    }
    public Reportt retrieveReport(String id){
        Reportt r;
         r=reportRepository.findById(Long.parseLong(id)).orElse(null);
         return r;
    }
}
