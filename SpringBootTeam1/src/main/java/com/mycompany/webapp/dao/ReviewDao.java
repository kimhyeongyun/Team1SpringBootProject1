package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Review;


@Mapper
public interface ReviewDao {


   public int insertReview(Review review);
   public Review selectByREVIEW_ID(int REVIEW_ID);
   public List<String> selectOrderIdForReview(@Param("user_id")String user_id,@Param("p_id") int p_id);
   public int getCount(int p_id);
   
   
   public List<Review> selectByPageByFilter(Pager pager);
   public List<Review> selectByPageBySearch(Pager pager);
   public List<Review> selectByPage(Pager pager);
   public Review selectByReviewId(int review_id);
   public int update(Review review);
   public int count();
   public int countByFilter(int review_score);
   public int countBySearch(String searchType, String searchContent);
         
}



