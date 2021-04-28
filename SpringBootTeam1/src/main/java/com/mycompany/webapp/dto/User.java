package com.mycompany.webapp.dto;

public class User {
   private String user_id;
   private String user_name;
   private String user_password;
   private String user_phone;
   private String dog_size;
   private int enabled;
   private String authority;
   
   
   
   public String getUser_id() {
      return user_id;
   }
   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }
   public String getUser_name() {
      return user_name;
   }
   public void setUser_name(String user_name) {
      this.user_name = user_name;
   }
  
   public String getUser_password() {
      return user_password;
   }
   public void setUser_password(String user_password) {
      this.user_password = user_password;
   }
   public String getUser_phone() {
      return user_phone;
   }
   public void setUser_phone(String user_phone) {
      this.user_phone = user_phone;
   }
   public String getDog_size() {
      return dog_size;
   }
   public void setDog_size(String dog_size) {
      this.dog_size = dog_size;
   }
   public int getEnabled() {
      return enabled;
   }
   public void setEnabled(int enabled) {
      this.enabled = enabled;
   }
   public String getAuthority() {
      return authority;
   }
   public void setAuthority(String authority) {
      this.authority = authority;
   }
   
}