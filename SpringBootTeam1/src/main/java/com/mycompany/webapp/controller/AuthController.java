package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.security.JwtUtil;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private UsersService usersService;
   private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

   @PostMapping("/login")
   // {"uid" : "user1", "upassword" : "12345"}
   public Map<String, String> login(@RequestBody Map<String, String> user) { // vs코드의 ng-model=user.uid, user.password
                                                            // 의 user랑 같다.
      // 인증 데이터 얻기
      String uid = user.get("uid");
      String upassword = user.get("upassword");
      // 사용자 인증
      UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(uid, upassword);
      org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(upat);

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = JwtUtil.createToken(uid);
      Map<String, String> map = new HashMap<String, String>();
      map.put("uid", uid);
      map.put("authToken", jwt);
      return map;
   }

   @GetMapping("/keywordlist")
   public Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo, String keyword) {
       int totalRows = usersService.getTotalRows(keyword);
      	Pager pager = new Pager(5, 5, totalRows, pageNo);
      	List<User> list = usersService.getList(pager, keyword);
      Map<String, Object> map = new HashMap<>();
      map.put("users", list);
      map.put("pager", pager);
      return map;
   }

   @GetMapping("/{user_id}")
   public User read(@PathVariable String user_id) {
      User user = usersService.getUser(user_id);
      return user;
   }

   @PutMapping("")
   // @RequestBody : 요청 http 본문에 json이 포함되어 있을 경우 raw type:json
   public void update(@RequestBody User user) {
      logger.info("" + user);
      usersService.update(user);
   }

   
   @GetMapping("/usercount")
   public String getcount() {
	   int ucount = usersService.getTotalCount();
	   return ucount + "";
   }
   
}