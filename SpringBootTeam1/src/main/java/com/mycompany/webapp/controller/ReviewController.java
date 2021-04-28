package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Review;
import com.mycompany.webapp.service.ReviewsService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewsService reviewsService;

	@GetMapping("")
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "0") int review_score, String searchType, String searchContent) {

		if (review_score == 0 && searchType == null && searchContent == null) {
			logger.info("review_score 체크1:" + review_score);
			int totalRows = reviewsService.getCount();
			Pager pager = new Pager(10, 5, totalRows, pageNo);
			List<Review> list = reviewsService.getList(pager);
			Map<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("reviews", list);
			return map;
		}

		else if (searchType == null && searchContent == null) {
			logger.info("searchType 체크1:" + searchType);
			logger.info("searchContent 체크1:" + searchContent);
			int totalRows = reviewsService.getCountByFilter(review_score);
			Pager pager = new Pager(10, 5, totalRows, pageNo);
			List<Review> listByFilter = reviewsService.getListByFilter(pager, review_score);
			Map<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("reviews", listByFilter);
			return map;
		}
		else {
			logger.info("searchContent  체크33333:" +searchContent + searchType );
			int totalRows = reviewsService.getCountBySearch(searchType, searchContent);
			Pager pager = new Pager(10, 5, totalRows, pageNo);
			List<Review> listBySearch = reviewsService.getListBySearch(pager, searchType, searchContent);
			Map<String, Object> map = new HashMap<>();
			map.put("pager", pager);
			map.put("reviews", listBySearch);
			return map;
		}
	}

//	@GetMapping("/filter")
//	public Map<String, Object> listByFilter(@RequestParam(defaultValue = "1") int pageNo, int review_score) {
//		logger.info("체크1:" + review_score);
//		logger.info("review_score 체크1:" + review_score);
//		int totalRows = reviewsService.getCountByFilter(review_score);
//		logger.info("체크2:" + totalRows);
//		Pager pager = new Pager(10, 5, totalRows, pageNo);
//		List<Review> listByFilter = reviewsService.getListByFilter(pager, review_score);
//		logger.info("체크3:" + listByFilter);
//		Map<String, Object> map = new HashMap<>();
//		map.put("pager", pager);
//		map.put("reviews", listByFilter);
//		return map;
//	}
//
//	@GetMapping("/search")
//	public Map<String, Object> listBySearch(@RequestParam(defaultValue = "1") int pageNo, String searchType,
//			String searchContent) {
//		logger.info("체크1:" + searchType);
//		logger.info("체크1:" + searchContent);
//		int totalRows = reviewsService.getCountBySearch(searchType, searchContent);
//		logger.info("체크2:" + totalRows);
//
//		Pager pager = new Pager(10, 5, totalRows, pageNo);
//		List<Review> listBySearch = reviewsService.getListBySearch(pager, searchType, searchContent);
//		logger.info("체크3:" + listBySearch);
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("pager", pager);
//		map.put("reviews", listBySearch);
//		return map;
//	}
	

	@GetMapping("/{review_id}")
	public Review read(@PathVariable int review_id) {
		Review review = reviewsService.getReview(review_id);
		return review;
	}

	@PutMapping("")
	// @RequestBody : 요청 http 본문에 json이 포함되어 있을 경우 raw type:json
	public Review update(@RequestBody Review review) {
		reviewsService.update(review);
		return review;
	}
}