package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.controller.AuthController;
import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.User;


@Service
public class UsersService {
   @Autowired
   private UsersDao usersDao;
   
   private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

   public void join(User user) {
      usersDao.insert(user);
   }
   
   public int idCheck(String user_id) {
      
      return usersDao.idCheck(user_id);
   }
   
   // 읽어오기, 비밀번호 찾기(searchId)
   public User getUser(String user_id) {
      User user = usersDao.selectByUserid(user_id);
      return user;
   }

//   // 아이디(이메일) 찾기(searchPw)
//   public User getUserid(String user_name, String user_phone) {
//      User user = usersDao.selectIdByNameAndPassword(user_name, user_phone);
//      return user;
//   }
//
//   // 비밀번호 변경
//   public void updateUser(String user_password, String user_id) {
//      usersDao.updatePasswordById(user_password, user_id);
//   }
//   // 휴대번호 변경
//   public void updateUser2(String user_phone, String user_id) {
//      usersDao.updatePhoneById(user_phone, user_id);
//   }
   
   public int getTotalRows(String keyword) {
	   logger.info("keyword" + keyword);
	   int rows = usersDao.count(keyword);
	   logger.info("keywordrows : " + rows);
	   return rows;
   }
   

   public List<User> getList(Pager pager, String keyword) {
	   logger.info("keywordget : " + keyword);
	   logger.info("pagerpager" + pager.getTotalRows());
	   List<User> list = usersDao.userList(pager, keyword);
	
      return list;
   }
   public int getTotalCount() {
      return usersDao.getUser();
   }
   
//   public List<User> getList(Pager pager) {
//      return usersDao.userList(pager);
//   }

   public int update(User user) {
      return usersDao.update(user);
   }
   
}