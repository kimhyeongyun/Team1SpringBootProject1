package com.mycompany.webapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.QnaDao;
import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Qna;
import com.mycompany.webapp.dto.User;


@Service
public class QnaService {
   @Autowired
   private QnaDao qnaDao;


   public Qna getQna(int qa_id) {
      Qna qna = qnaDao.selectByQa_id(qa_id);
      return qna;
   }

   public void updateQna(Qna qna) {
      qnaDao.update(qna);
   }
   
   public void deleteQna(int qa_id) {
      qnaDao.deleteByQa_id(qa_id);
   }
   
   public void insertQna(Qna qna) {
      qnaDao.insert(qna);
   }
   
//   public List<Qna> getBoardList(){
//         List<Qna> list = qnaDao.selectAll();
//         return list;
//   }
 
   public List<Qna> getBoardList(Pager pager, String qa_category) {
      Map<String, Object> map = new HashMap<>();
      map.put("pager", pager);
      map.put("qa_category", qa_category);
      List<Qna> list = qnaDao.selectByPage(map);
       return list; 
   }

   public int getTotalRows(String qa_category) {
       int rows = qnaDao.countuser(qa_category);
       return rows;
    }
   
   public int getWaitRows() {
	   int waitrows = qnaDao.countwait();
	   return waitrows;
   }

   
}