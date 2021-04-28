package com.mycompany.webapp.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mycompany.webapp.dto.OrderProduct;
import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrdersService  ordersService;
	
	@GetMapping("")
	public Map<String,Object> list(@RequestParam(defaultValue="1") int pageNo, String keyword, String searchStatus){
		logger.info(keyword);
		logger.info(searchStatus);
		int totalRows = ordersService.getTotalRows(keyword,searchStatus);
		Pager pager = new Pager(10,5,totalRows,pageNo);
		List<Orders> list = ordersService.getOrdersList(pager,keyword,searchStatus);
		Map<String,Object> map =new HashMap<>();
		map.put("pager", pager);
		map.put("orders", list);
		map.put("count", totalRows);
		return map;
	}
	

	
	 @GetMapping("/{order_id}")
	   public Map<String,Object> read(@PathVariable String order_id) {
		   Orders orders = ordersService.ReadOrders(order_id);
		   List<OrderProduct> list = ordersService.getProductList(order_id);
		   Map<String,Object> map = new HashMap<>();
		   map.put("order", orders);
		   map.put("orderProduct", list);
		   return map;
	   }
	 
	 @GetMapping("/ordercount")
	 public String getcount() {
		 int rcount = ordersService.getReadyCount();
		 return rcount + "";
	 }
	 
	
	
	@PutMapping("")
	public Orders update(@RequestBody Orders orders) {
		ordersService.deleteOrder(orders);
		return orders;
	}
	
	 @DeleteMapping("/{order_id}")
	   public void delete(@PathVariable String order_id) {
		 logger.info(order_id);
		 ordersService.deleteOrderProduct(order_id);
		 ordersService.delete(order_id);
	   }
}

