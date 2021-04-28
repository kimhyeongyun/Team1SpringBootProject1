package com.mycompany.webapp.dto;

public class Photo {


      private int photo_id;
private int p_id;
   private String photo_oname;
   private String photo_sname;
   private String photo_type;
   private String photo_role;
   
   
//   public Photo(int photo_id, int p_id, String photo_oname, String photo_sname, String photo_type, String photo_role) {
//      super();
//      this.photo_id = photo_id;
//      this.p_id = p_id;
//      this.photo_oname = photo_oname;
//      this.photo_sname = photo_sname;
//      this.photo_type = photo_type;
//      this.photo_role = photo_role;
//   }
   
   public int getPhoto_id() {
   return photo_id;
}
public void setPhoto_id(int photo_id) {
   this.photo_id = photo_id;
}
public int getP_id() {
   return p_id;
}
public void setP_id(int p_id) {
   this.p_id = p_id;
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
   
}