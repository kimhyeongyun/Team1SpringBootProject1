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

   int update(Product product);

   int updateSalescountAndStock(int pid);

   int deleteBypid(int p_id);

   

   List<Product> selectAllByPager(Pager pager, String optionVal);
   int count();
   

   Product selectProductDetail(int pid);


int selectcurrent();

int countSort(String countSort);
   

}