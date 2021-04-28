package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Qna;



@Mapper
public interface QnaDao {
   public int insert(Qna qna);
   public Qna selectByQa_id(int qa_id);
   public int update(Qna qna);
   public int deleteByQa_id(int qa_id);
   public List<Qna> selectByPage(Map<String, Object> map);
   public int countuser(String qa_category);
   public int countwait();

}