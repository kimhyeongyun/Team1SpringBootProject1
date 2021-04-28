package com.mycompany.webapp.dto;

import java.util.Date;

public class SizeProduct {

private int p_id;
private String p_size;
   
   
//   public SizeProduct(int p_id, String p_size) {
//		super();
//		this.p_id = p_id;
//		this.p_size = p_size;
//	}
   public int getP_id() {
      return p_id;
   }
   public void setP_id(int p_id) {
      this.p_id = p_id;
   }
   public String getP_size() {
      return p_size;
   }
   public void setP_size(String p_size) {
      this.p_size = p_size;
   }
   
}