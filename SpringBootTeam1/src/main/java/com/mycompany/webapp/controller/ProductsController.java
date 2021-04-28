package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dao.PhotosDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Photo;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.service.ProductService;

@RestController 
@RequestMapping("/products")
public class ProductsController {
   @Autowired
   private ProductService productService; 
   
   @GetMapping("")
   public Map<String, Object> list(@RequestParam(defaultValue="1") int pageNo,@RequestParam(defaultValue="등록순") String optionVal) {
      System.out.println("optoin" + optionVal);
      int totalRows = productService.getProductsCount();
      Pager pager = new Pager(5, 5, totalRows, pageNo);
      List<Product> list = productService.getProductsByPager(pager,optionVal);
      Map<String, Object> map = new HashMap<>();
      map.put("products", list);
      map.put("pager", pager);
      return map;
   }   
   @GetMapping("CountSort/{countSort}")
   public String GetCountSort(@PathVariable String countSort) {
	   int count = productService.getCountSort(countSort);
	   
      return count+"";
   }   
   @Autowired
   ResourceController resourceController;
   @Autowired
   PhotosDao photoDao;
   
   @PostMapping("")
   // @RequestBody : 요청 http 본문에 json이 포함되어 있을 경우 raw -> type:json // 서버에 따라 @RequestBody로 받거나 안받거나 결정됨.
   public Product create(Product product ) {
      product.setP_salescount(0);
      product.setP_rate(0);
      System.out.println(product);
      productService.createProduct(product);
      int currpid = productService.getCurrentProductID();
      System.out.println(currpid);
      if (product.getP_mainphoto()!= null && !product.getP_mainphoto().isEmpty()) {
            Photo MainPhoto  = new Photo();
         MultipartFile mf = product.getP_mainphoto();
         MainPhoto.setPhoto_oname(mf.getOriginalFilename());
         MainPhoto.setPhoto_sname(new Date().getTime() + "-" + mf.getOriginalFilename());
         String type[] = mf.getContentType().split("/");
         MainPhoto.setPhoto_type(type[1]);
         MainPhoto.setPhoto_role("main");
         MainPhoto.setP_id(currpid);
         try {
            File file = new File("C:/Photos/ProductPhotos/Main/" + MainPhoto.getPhoto_sname());
            mf.transferTo(file);
            photoDao.insert(MainPhoto);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      if (product.getP_subphotos()!= null && product.getP_subphotos().length>0) {
         
         for(int i=0; i<product.getP_subphotos().length; i++ ) {
            Photo SubPhoto  = new Photo();
            MultipartFile mf = product.getP_subphotos()[i];
            SubPhoto.setPhoto_oname(mf.getOriginalFilename());
            SubPhoto.setPhoto_sname(new Date().getTime() + "-" + mf.getOriginalFilename());
            String type[] = mf.getContentType().split("/");
            SubPhoto.setPhoto_type(type[1]);
            SubPhoto.setPhoto_role("sub");
            SubPhoto.setP_id(currpid);
            try {
               File file = new File("C:/Photos/ProductPhotos/Sub/" + SubPhoto.getPhoto_sname());
               mf.transferTo(file);
               photoDao.insert(SubPhoto);
            } catch (Exception e) {
               e.printStackTrace();
            }  
         }
      }
      
      if (product.getP_detailphoto()!= null && !product.getP_detailphoto().isEmpty()) {
            Photo DetailPhoto  = new Photo();
         MultipartFile mf = product.getP_detailphoto();
         DetailPhoto.setPhoto_oname(mf.getOriginalFilename());
         DetailPhoto.setPhoto_sname(new Date().getTime() + "-" + mf.getOriginalFilename());
         String type[] = mf.getContentType().split("/");
         DetailPhoto.setPhoto_type(type[1]);
         DetailPhoto.setPhoto_role("detail");
         DetailPhoto.setP_id(currpid);
         try {
            File file = new File("C:/Photos/ProductPhotos/Detail/" + DetailPhoto.getPhoto_sname());
            mf.transferTo(file);
            photoDao.insert(DetailPhoto);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      product.setP_mainphoto(null);
      product.setP_subphotos(null);
      product.setP_detailphoto(null);
      
      return product;
   }
   
   @GetMapping("/{pid}")
   public Product read(@PathVariable int pid) {
     Product product = productService.getProductDetail(pid);
     System.out.println(pid);
     System.out.println(product.getP_mainphoto());
      return product;
   }   
   
   @PutMapping("")
   // @RequestBody : 요청 http 본문에 json이 포함되어 있을 경우 raw type:json
   public Product update(Product product) { 
      productService.UpdateProduct(product);
      System.out.println(product);
      
      
      System.out.println("------------------------------------------");
      //   System.out.println(product.getP_mainphoto().getOriginalFilename());

         //System.out.println(product.getP_detailphoto().getOriginalFilename());
      //이전에 있던 사진들을 다지우고
      if(product.getPhoto_ids()!=null) {
         for(int i=0;i<product.getPhoto_ids().length; i++) {
                  System.out.println(product.getPhoto_names()[i]);
                       photoDao.deleteByPhotoid(product.getPhoto_ids()[i]);                        
           }   
      }

      System.out.println("------------------------------------------");
      // 새로 업데이트한 사진들을 추가함
      if (product.getP_mainphoto()!= null && !product.getP_mainphoto().isEmpty()) {
         
            Photo MainPhoto  = new Photo();
         MultipartFile mf = product.getP_mainphoto();
         MainPhoto.setPhoto_oname(mf.getOriginalFilename());
         MainPhoto.setPhoto_sname(new Date().getTime() + "-" + mf.getOriginalFilename());
         String type[] = mf.getContentType().split("/");
         MainPhoto.setPhoto_type(type[1]);
         MainPhoto.setPhoto_role("main");
         MainPhoto.setP_id(product.getP_id());
         try {
            File file = new File("C:/Photos/ProductPhotos/Main/" + MainPhoto.getPhoto_sname());
            mf.transferTo(file);
            photoDao.insert(MainPhoto);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      if (product.getP_subphotos()!= null && product.getP_subphotos().length>0) {
         
         for(int i=0; i<product.getP_subphotos().length; i++ ) {
            Photo SubPhoto  = new Photo();
            MultipartFile mf = product.getP_subphotos()[i];
            SubPhoto.setPhoto_oname(mf.getOriginalFilename());
            SubPhoto.setPhoto_sname(new Date().getTime() + "-" + mf.getOriginalFilename());
            String type[] = mf.getContentType().split("/");
            SubPhoto.setPhoto_type(type[1]);
            SubPhoto.setPhoto_role("sub");
            SubPhoto.setP_id(product.getP_id());
            try {
               File file = new File("C:/Photos/ProductPhotos/Sub/" + SubPhoto.getPhoto_sname());
               mf.transferTo(file);
               photoDao.insert(SubPhoto);
            } catch (Exception e) {
               e.printStackTrace();
            }  
         }
      }
      
      if (product.getP_detailphoto()!= null && !product.getP_detailphoto().isEmpty()) {
            Photo DetailPhoto  = new Photo();
         MultipartFile mf = product.getP_detailphoto();
         DetailPhoto.setPhoto_oname(mf.getOriginalFilename());
         DetailPhoto.setPhoto_sname(new Date().getTime() + "-" + mf.getOriginalFilename());
         String type[] = mf.getContentType().split("/");
         DetailPhoto.setPhoto_type(type[1]);
         DetailPhoto.setPhoto_role("detail");
         DetailPhoto.setP_id(product.getP_id());
         try {
            File file = new File("C:/Photos/ProductPhotos/Detail/" + DetailPhoto.getPhoto_sname());
            mf.transferTo(file);
            photoDao.insert(DetailPhoto);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      product.setP_mainphoto(null);
      product.setP_subphotos(null);
      product.setP_detailphoto(null);
      return product;
   }
   
   @DeleteMapping("/{pid}")
   public void delete(@PathVariable int pid) {
      // 상품삭제하기
      productService.RemoveProduct(pid);
      // 사진 삭제
   }
}