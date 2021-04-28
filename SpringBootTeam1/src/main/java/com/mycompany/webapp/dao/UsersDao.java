package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.User;

@Mapper

public interface UsersDao {

   public int insert(User user);

   public User selectIdByNameAndPassword(@Param("user_name") String user_name, @Param("user_phone") String user_phone);

   // public int updatePasswordById(@Param("user_password") String user_password,
   // @Param("user_id") String user_id);

   public int updatePhoneById(@Param("user_phone") String user_phone, @Param("user_id") String user_id);

   public int updatePasswordById(@Param("user_password") String user_password, @Param("user_id") String user_id);

   public User selectByUserid(String user_id);

   public int idCheck(String user_id);
   
   public int getUser();
   
   public List<User>userList(@Param("pager")Pager pager, @Param("keyword")String keyword);

   public int update(User user);

   public int count(String keyword);
   

   

}