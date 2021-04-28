package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dao.PhotosDao;
import com.mycompany.webapp.dto.Photo;


// 사진과 리소스 요청 컨트롤러
@Controller
@RequestMapping("/resource")
public class ResourceController {
   private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
   
   // 메인사진 리소스 요청 메소드. 경로 -> C:/Photos/ProductPhotos/Main
   @GetMapping("/GetPhoto/{photoCategory}/{photoSname}/{photoType}")
   public void GetPhoto(@PathVariable String photoCategory,@PathVariable String photoSname,@PathVariable String photoType, HttpServletResponse response) {
      
      try {
         
         //photoType안에 / 들어갈시 파싱
         
         
         // 응답 HTTP 헤더에 저장될 바디의 타입
         response.setContentType(photoType);

         // 한글 파일일 경우, 깨짐 현상을 방지
         photoSname = new String(photoSname.getBytes("UTF-8"), "ISO-8859-1");
         response.setHeader("Content-Disposition", "attachment; filename=\"" + photoSname+ "\";");
         InputStream is = null;
         // 응답 HTTP 바디에 이미지 데이터를 출력
         switch(photoCategory) {
         case "Main": 
            is = new FileInputStream("C:/Photos/ProductPhotos/Main/" + photoSname );
            break;
         case "Sub": 
            is = new FileInputStream("C:/Photos/ProductPhotos/Sub/" + photoSname );
            break;
         case "Detail": 
            is = new FileInputStream("C:/Photos/ProductPhotos/Detail/" + photoSname );
            break;
         case "Review": 
            is = new FileInputStream("C:/Photos/ProductPhotos/Review/" + photoSname );
            break;
         }
         
         
        
         OutputStream os = response.getOutputStream();
         FileCopyUtils.copy(is, os);
         os.flush();
         is.close();
         os.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   
   // 여기서 바로 사진 저장
   //type => Main / Sub / Detail / Review
   @Autowired
   PhotosDao photoDao;
   public void uploadPhoto(MultipartFile photoFile, String photoCategory) {
      
      if (photoFile != null && !photoFile.isEmpty()) {
         Photo photo = new Photo();
         photo.setPhoto_oname(photoFile.getOriginalFilename());
         photo.setPhoto_sname(new Date().getTime() + "-" + photoFile.getOriginalFilename());
         photo.setPhoto_type(photoFile.getContentType());
         try {
            File file = null;
            switch(photoCategory) {
            case "Main":
               file = new File("C:/Photos/ProductPhotos/Main/" + photo.getPhoto_sname());
               
               break;
            case "Sub":

               file = new File("C:/Photos/ProductPhotos/Sub/" + photo.getPhoto_sname());
               break;
            case "Detail":

               file = new File("C:/Photos/ProductPhotos/Detail/" + photo.getPhoto_sname());
               break;
            case "Review":

               file = new File("C:/Photos/ProductPhotos/Review/" + photo.getPhoto_sname());
               break;
            }
            
            photoFile.transferTo(file);
         } catch (Exception e) {
            e.printStackTrace();
         }
         photoDao.insert(photo);
      }
   }

}