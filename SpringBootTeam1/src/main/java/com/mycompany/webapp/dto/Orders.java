package com.mycompany.webapp.dto;

import java.util.Date;

public class Orders {
	private String order_id;
	private Date order_date;
	private String order_request;
	private int total_price;
	private String payment;
	private String delivery_address;
	private String delivery_address_detail;
	private int zip;
	private String user_id;
	private String order_name;
	private int order_phone;
	private int order_sprice;
	private int p_id;
	private String delivery_status;
	private int total_amount;
	//조인 데이터
	private int photo_id;
	private String photo_oname;
	private String photo_sname;
	private String photo_type;
	private String photo_role;
	private String p_name;
	private String p_price;

	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getPhoto_oname() {
		return photo_oname;
	}
	public void setPhoto_oname(String photo_oname) {
		this.photo_oname = photo_oname;
	}
	public String getPhoto_sname() {
		return photo_sname;
	}
	public void setPhoto_sname(String photo_sname) {
		this.photo_sname = photo_sname;
	}
	public String getPhoto_type() {
		return photo_type;
	}
	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
	}
	public String getPhoto_role() {
		return photo_role;
	}
	public void setPhoto_role(String photo_role) {
		this.photo_role = photo_role;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_price() {
		return p_price;
	}
	public void setP_price(String p_price) {
		this.p_price = p_price;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public int getOrder_sprice() {
		return order_sprice;
	}
	public void setOrder_sprice(int order_sprice) {
		this.order_sprice = order_sprice;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_request() {
		return order_request;
	}
	public void setOrder_request(String order_request) {
		this.order_request = order_request;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getDelivery_address_detail() {
		return delivery_address_detail;
	}
	public void setDelivery_address_detail(String delivery_address_detail) {
		this.delivery_address_detail = delivery_address_detail;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public int getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(int order_phone) {
		this.order_phone = order_phone;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}




}