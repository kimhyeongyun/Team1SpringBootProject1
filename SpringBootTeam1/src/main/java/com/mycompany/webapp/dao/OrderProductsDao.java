package com.mycompany.webapp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.OrderProduct;

@Mapper
public interface OrderProductsDao {
	List<OrderProduct> productList(String oid);
	public int deleteByOrderProduct(String order_id);
}


