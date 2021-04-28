package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ReviewDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Review;

@Service
public class ReviewsService {
	 private static final Logger logger = LoggerFactory.getLogger(ReviewsService.class);

   @Autowired
   ReviewDao reviewDao;

   
   //형윤's list
   public List<Review> getList(Pager pager) {
        return reviewDao.selectByPage(pager);
   }
   
   //형윤's list
   public List<Review> getListByFilter(Pager pager, int review_score) {
      pager.setReview_score(review_score);
        return reviewDao.selectByPageByFilter(pager);
   }
   
   //형윤's list
   public List<Review> getListBySearch(Pager pager, String searchType, String searchContent) {
	   pager.setSearchType(searchType);
	   pager.setSearchContent(searchContent);
       return reviewDao.selectByPageBySearch(pager);
   }
   //형윤's list
   public int getCount() {
      return reviewDao.count();
   }
   //형윤's read
   public Review getReview(int review_id) {
      return reviewDao.selectByReviewId(review_id);
   }
   //형윤's update
   public int update(Review review) {
      return reviewDao.update(review);
   }

   public int getCountByFilter(int review_score) {
      
      return reviewDao.countByFilter(review_score);
   }

   public int getCountBySearch(String searchType, String searchContent) {
	   logger.info("체크1service:"+searchType);
	   logger.info("체크1service:"+searchContent);

      return reviewDao.countBySearch(searchType, searchContent);
   }
   

   

}