package tn.Sindibad.SVA_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.Sindibad.SVA_Back.model.Reportt;
import tn.Sindibad.SVA_Back.service.ReportService;

import java.util.List;
@RestController
@RequestMapping("/api/report")
public class ReporttController {
    @Autowired
    ReportService reportService;
    @PostMapping("/add")
    @ResponseBody
    public Reportt addReport(@RequestBody Reportt r){
        Reportt report= reportService.addReport(r);
        return report;
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Reportt getById(@PathVariable("id") String id){
        return reportService.retrieveReport(id);
    }
}
