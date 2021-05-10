package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Qna;



@Mapper
public interface QnaDao {
   public List<Qna> selectByPage(Map<String, Object> map);
   public Qna selectByQa_id(int qa_id);
   public int update(Qna qna);
   public int deleteByQa_id(int qa_id);
   public int countuser(@Param("qa_category")String qa_category, @Param("keyword")String keyword);
   public int countwait();

}