package com.mycompany.webapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.OrderProductsDao;
import com.mycompany.webapp.dao.OrdersDao;
import com.mycompany.webapp.dto.OrderProduct;
import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;

@Service
public class OrdersService {

	@Autowired
	OrdersDao ordersDao;

	@Autowired
	OrderProductsDao orderProductDao;




	//주문 삭제
	public void updateStatus(Orders orders) {
		ordersDao.updateStatus(orders);
	}

	//주문서 읽어오기
	public Orders ReadOrders(String order_id) {

		Orders orders=ordersDao.orderSelectByOne(order_id);
		return orders;
	}
	
	//주문내역 읽어오기
	public List<Orders> getOrdersList(Pager pager,String keyword,String status){ 
		List<Orders> list = ordersDao.orderListByPage(pager, keyword,status);

		return list;
	}

	public int getTotalRows(String keyword,String status) {
		int rows = ordersDao.count(keyword,status);
		return rows;
	}
	
	// 배송준비중 상태의 주문 개수
	public int getReadyCount() {
		int count = ordersDao.readycount();
		return count;
	}
	
	//주문삭제
	public int delete(String order_id) {
		return ordersDao.deleteByoid(order_id);
	}

	



	//orderProduct 테이블 관련 

	public List<OrderProduct> getProductList(String oid){
		List<OrderProduct> plist = orderProductDao.productList(oid);
		return plist;
	}
	
	public int deleteOrderProduct(String order_id) {
		return orderProductDao.deleteByOrderProduct(order_id);
	}



}
