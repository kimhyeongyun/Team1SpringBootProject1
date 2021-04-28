package com.mycompany.webapp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.Qna;
import com.mycompany.webapp.service.QnaService;

@RestController
@RequestMapping("/qna")
public class QnaController {
   
   @Autowired
   private QnaService qnaService;
   
   @GetMapping("")
   public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo, 
                           @RequestParam(defaultValue = "") String qa_category) {
      int totalRows = qnaService.getTotalRows(qa_category);     
      Pager pager = new Pager(10, 5, totalRows, pageNo);
      List<Qna> list = qnaService.getBoardList(pager, qa_category);
      Map<String, Object> map = new HashMap<>(); //map객체 만듦
      map.put("pager", pager);
      map.put("qnas", list);
      map.put("count", totalRows);
      return map; 
   }
   
   @GetMapping("/{qa_id}")
   public Qna read(@PathVariable int qa_id) {
      Qna qna = qnaService.getQna(qa_id);
      return qna;
   }
   
   @PutMapping("")
   public Qna update(@RequestBody Qna qna) {
      qnaService.updateQna(qna);
      return qna;
   }
   
   @DeleteMapping("/{qa_id}")
   public void delete(@PathVariable int qa_id) {
      qnaService.deleteQna(qa_id);
   }
   
   @GetMapping("/readwait")
   public String readWait() {
	   int qnaWait = qnaService.getWaitRows();
	   System.out.println(String.valueOf(qnaWait));
	   return qnaWait + "";
   }
   
}