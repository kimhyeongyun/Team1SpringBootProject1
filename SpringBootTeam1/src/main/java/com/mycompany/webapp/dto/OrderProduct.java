package com.mycompany.webapp.dto;

import java.util.Date;

public class OrderProduct {
   private String order_id;
   private int p_id;
   private int amount;
   
   //조인
   private String p_name;
   private int p_price;
   private int p_stock;
   private int p_salescount;
   private String p_category_name;
   private Date p_upload_date;
   private String p_description;
   private int photo_id;
   private String photo_oname;
   private String photo_sname;
   private String photo_type;
   private String photo_role;
public String getP_name() {
	return p_name;
}
public void setP_name(String p_name) {
	this.p_name = p_name;
}
public int getP_price() {
	return p_price;
}
public void setP_price(int p_price) {
	this.p_price = p_price;
}
public int getP_stock() {
	return p_stock;
}
public void setP_stock(int p_stock) {
	this.p_stock = p_stock;
}
public int getP_salescount() {
	return p_salescount;
}
public void setP_salescount(int p_salescount) {
	this.p_salescount = p_salescount;
}
public String getP_category_name() {
	return p_category_name;
}
public void setP_category_name(String p_category_name) {
	this.p_category_name = p_category_name;
}
public Date getP_upload_date() {
	return p_upload_date;
}
public void setP_upload_date(Date p_upload_date) {
	this.p_upload_date = p_upload_date;
}
public String getP_description() {
	return p_description;
}
public void setP_description(String p_description) {
	this.p_description = p_description;
}
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
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public String getOrder_id() {
	return order_id;
}
public void setOrder_id(String order_id) {
	this.order_id = order_id;
}
public int getP_id() {
	return p_id;
}
public void setP_id(int p_id) {
	this.p_id = p_id;
}

   
 
   
   
   
   
   
   
}