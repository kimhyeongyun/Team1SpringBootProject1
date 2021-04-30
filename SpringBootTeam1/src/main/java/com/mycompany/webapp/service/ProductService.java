package com.mycompany.webapp.service;

import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.PhotosDao;
import com.mycompany.webapp.dao.ProductDao;
import com.mycompany.webapp.dao.ReviewDao;
import com.mycompany.webapp.dao.SizeProductDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Photo;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.Review;
import com.mycompany.webapp.dto.SizeProduct;

@Service
public class ProductService {
   @Autowired
   ProductDao productDao;

   @Autowired
   SizeProductDao sizeProductDao;

   
   @Autowired
   PhotosDao photosDao;

   @Autowired
   ReviewDao reviewDao;
   //Create 
   // 상품 생성
   public void createProduct(Product product) {
	   //상품 생성
      productDao.insert(product); 
      //사이즈 생성
      for(int i=0; i< product.getP_size().length; i++) {
    	  if(!product.getP_size()[i].equals("false")) {
    		  SizeProduct size = new SizeProduct();
    		  size.setP_id(productDao.selectcurrent());
    		  size.setP_size(product.getP_size()[i]);
    		  sizeProductDao.insertSize(size);
    	  }
      }

   }

   //리뷰 저장하기
   public void createReview(Review review) {
      reviewDao.insertReview(review);
   }


   //Read   
   //특정 상품 가져오기
   public int getCurrentProductID() {
      int currpid = productDao.selectcurrent();
      return currpid;
   }
   public Product getProduct(int pid) {
      Product product = productDao.selectBypid(pid);
      return product;
   }
   //신규 상품 리스트 가져오기
   public List<Product> getProductsByPager(Pager pager, String optionVal){
      List<Product> products = productDao.selectAllByPager(pager,optionVal);
      return products;
   }
   //신규 상품 리스트 개수 가져오기
   public int getProductsCount(){
      int count = productDao.count();
      return count;
   }
 
   // 디테일 정보 가져오기
   public Product getProductDetail(int pid) {
      // TODO Auto-generated method stub
      return productDao.selectProductDetail(pid);
      
      
   }
   
   
   

   //Update
   //상품 수정하기
   public void UpdateProduct(Product product) {
      productDao.update(product);
      //사이즈 삭제
    sizeProductDao.deleteSizeByPid(product.getP_id());
    
      
      
      //사이즈 생성
      for(int i=0; i< product.getP_size().length; i++) {
    	  if(!product.getP_size()[i].equals("false")) {
    		  SizeProduct size = new SizeProduct();
    		  size.setP_id(product.getP_id());
    		  size.setP_size(product.getP_size()[i]);
    		  sizeProductDao.insertSize(size);
    	  }
      }
   }
  


   //Delete
   //상품 삭제하기
   public void RemoveProduct(int pid) {
      System.out.println("delete : " + pid);
      // 사진 삭제
//      List<Photo> photos = photosDao.selectByPid(pid);
//      File file=null;
//      for(Photo p:photos) {
//         if(p.getPhoto_role().equals("main")) {
//            file = new File("C:/Photos/ProductPhotos/Main/" + p.getPhoto_sname() );
//            }
//         else if(p.getPhoto_role().equals("sub")) {
//            file = new File("C:/Photos/ProductPhotos/Sub/" + p.getPhoto_sname() );
//            
//         }
//         else if(p.getPhoto_role().equals("detail")) {
//            file = new File("C:/Photos/ProductPhotos/Detail/" + p.getPhoto_sname() );
//         }
//         
//         if( file.exists() ){
//            if(file.delete())
//            { System.out.println("파일삭제 성공"); }
//            else{ System.out.println("파일삭제 실패"); } 
//            }
//            else{ System.out.println("파일이 존재하지 않습니다."); }
//            
//         
//         }
//      photosDao.deleteByPid(pid);
      
      //사이즈 삭제
      sizeProductDao.deleteSizeByPid(pid);
      
      
      // 상품 삭제
      productDao.updateEnabledBypid(pid);
   
   }

   /////////////////////
   
   //사진 생성
   public void createPhoto(Photo p) {
      photosDao.insert(p);
   }
   //사이즈 생성
   public void createSize(SizeProduct s) {
      sizeProductDao.insertSize(s);
   }


  
public int getCountSort(String countSort) {
	int count = productDao.countSort(countSort);
	return count;
}
   

}