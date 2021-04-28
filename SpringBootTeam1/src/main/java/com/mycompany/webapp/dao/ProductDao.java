package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Product;

@Mapper
public interface ProductDao {

   int insert(Product product);

   Product selectBypid(int pid);

   

   
   int updateEnabledBypid(int p_id);
   

   List<Product> selectRankAll();

   

   

   int update(Product product);

   int updateSalescountAndStock(int pid);

   int deleteBypid(int p_id);

   

   List<Product> selectAllByPager(Pager pager, String optionVal);
   int count();
   
   List<Product> selectRecommandAllByPager(Pager pager);
   int recommandcount();
   
   List<Product> selectBestReviewAll();
   int bestReviewcount();

   List<Product> selectRankCategory(String category);
   int rankcategorycount(String category);
   
   List<Product> selectSearchAll(@Param("pager") Pager pager, @Param("searchword")String searchword);
   int searchcount(String searchword);
   
   List<Product> selectCategory(@Param("category")String category,@Param("pager") Pager pager);
   int categorycount(String category);

   List<Product> selectSearchCategory(@Param("pager")Pager pager, @Param("searchword")String searchword, @Param("category")String category);
   int searchcategorycount(@Param("searchword")String searchword, @Param("category")String category);

   Product selectProductDetail(int pid);

int updateRate(@Param("p_id")int p_id,@Param("p_rate")int p_rate);

int selectcurrent();

int countSort(String countSort);
   

}