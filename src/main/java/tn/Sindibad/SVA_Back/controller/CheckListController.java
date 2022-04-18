package tn.Sindibad.SVA_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.Sindibad.SVA_Back.model.CheckList;
import tn.Sindibad.SVA_Back.model.Reportt;
import tn.Sindibad.SVA_Back.service.CheckListService;

import java.util.List;

@RestController
@RequestMapping("/api/checkList")
public class CheckListController {
    @Autowired
    CheckListService checkListService;
    @PostMapping("/add")
    @ResponseBody
    public CheckList addCheck(@RequestBody CheckList c){
        CheckList ch=checkListService.addCheckList(c);
        return ch;
    }
    @GetMapping("/getAll")
    @ResponseBody
    public List<CheckList> GetAllList(){
        List<CheckList> list= checkListService.getAllCheckList();
        return list;
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public CheckList getById(@PathVariable("id") String id){
        return checkListService.retrieveCheckList(id);
    }
    @GetMapping("/getShp/{id}")
    @ResponseBody
    public float getShp(@PathVariable("id") long id){
        return checkListService.calculHp(id);
    }
    @GetMapping("/getSnd/{id}")
    @ResponseBody
    public float getSnd(@PathVariable("id") long id){
        return checkListService.calculNd(id);
    }
    @GetMapping("/getSpc/{id}")
    @ResponseBody
    public float getSpc(@PathVariable("id") long id){
        return checkListService.calculPc(id);
    }
    @GetMapping("/getScl/{id}")
    @ResponseBody
    public float getScl(@PathVariable("id") long id){
        return checkListService.calculCl(id);
    }

    @GetMapping("/getSnui/{id}")
    @ResponseBody
    public float getS(@PathVariable("id") long id){
        return checkListService.calculNui(id);
    }
    @GetMapping("/getStotal/{id}")
    @ResponseBody
    public float getStotal(@PathVariable("id") long id){
        return (checkListService.calculHp(id)*15+checkListService.calculNd(id)*20+checkListService.calculPc(id)*15+
                checkListService.calculCl(id)*15+checkListService.calculNui(id)*5)/70;
    }

}
