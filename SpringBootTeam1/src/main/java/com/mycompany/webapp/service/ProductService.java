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
   private ReviewDao qnaDao;
   
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
   //추천 상품 리스트 가져오기
   public List<Product> getRecommandProductsByPager(Pager pager){
      List<Product> products = productDao.selectRecommandAllByPager(pager);
      return products;
   }
   //추천 상품 리스트 개수 가져오기
   public int getRecommandProductCount(){
      int count = productDao.recommandcount();
      return count;
   }
   //베스트 리뷰 상품 리스트 가져오기
   public List<Product> getBestReviewProducts(){
      List<Product> products = productDao.selectBestReviewAll();
      return products;
   }
   //베스트 리뷰 상품 리스트 개수 가져오기
   public int getBestReviewProductCount(){
      int count = productDao.bestReviewcount();
      return count;
   }
   //랭킹 상품 리스트 가져오기
   public List<Product> getRankProducts(String category){
      List<Product> products;
      //만약 전체 상품 가져와야 한다면
      if(category.equals("전체")) {
         products = productDao.selectRankAll();
      }
      // 만약 해당 카테고리의 상품을 가져와야 한다면
      else {
         products = productDao.selectRankCategory(category);
      }
      return products;
   }   
   //랭킹 상품 카테고리 리스트 개수 가져오기
   public int RankProductCount(String category){
      int count = productDao.rankcategorycount(category);
      return count;
   }
   //검색한 상품 리스트 가져오기
   public List<Product> getSearchProducts(Pager pager, String searchword,String category){
      List<Product> products;
      //만약 전체카테고리 상품 가져와야 한다면
      if(category.equals("전체")) {
         products = productDao.selectSearchAll(pager,searchword);
      }
      // 만약 특정 카테고리의 상품을 가져와야 한다면
      else {
         products = productDao.selectSearchCategory(pager,searchword,category);
      }
      return products;
   }   
   //검색 상품 리스트 개수 가져오기 - 전체 카테고리
   public int SearchProductCount(String searchword,String category){
      int count =0;
      //전체 카테고리의 검색상품 개수 가져오기
      if(category.equals("전체")) {
         count = productDao.searchcount(searchword);
      }
      // 특정 카테고리의 검색상품 개수 가져오기
      else {
         count = productDao.searchcategorycount(searchword,category);
      }
      return count;
   }



   //선택한 카테고리 상품 리스트 가져오기
   public List<Product> getCategoryProducts(String category, Pager pager){
      List<Product> products;
      //만약 전체 상품 가져와야 한다면
      if(category.equals("전체")) {
         products =null;// productDao.selectAllByPager(pager);
      }
      // 만약 해당 카테고리의 상품을 가져와야 한다면
      else {
         products = productDao.selectCategory(category,pager);
      }
      return products;
   }   
   //선택한 카테고리 상품 리스트 개수 가져오기
   public int CategoryProductCount(String category){
      int count = 0;
      if(category.equals("전체")) {
         count = productDao.count();
      }
      // 만약 해당 카테고리의 상품을 가져와야 한다면
      else {
         count = productDao.categorycount(category);
      }
      
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
   //구매된 상품 업데이트(구매횟수 추가 + 재고수량 감소)
   public void UpdateSaledProduct(int pid) {
      productDao.updateSalescountAndStock(pid);
   }



   //Delete
   //상품 삭제하기
   public void RemoveProduct(int pid) {
      System.out.println("delete : " + pid);
      // 사진 삭제
      List<Photo> photos = photosDao.selectByPid(pid);
      File file=null;
      for(Photo p:photos) {
         if(p.getPhoto_role().equals("main")) {
            file = new File("C:/Photos/ProductPhotos/Main/" + p.getPhoto_sname() );
            }
         else if(p.getPhoto_role().equals("sub")) {
            file = new File("C:/Photos/ProductPhotos/Sub/" + p.getPhoto_sname() );
            
         }
         else if(p.getPhoto_role().equals("detail")) {
            file = new File("C:/Photos/ProductPhotos/Detail/" + p.getPhoto_sname() );
         }
         
         if( file.exists() ){
            if(file.delete())
            { System.out.println("파일삭제 성공"); }
            else{ System.out.println("파일삭제 실패"); } 
            }
            else{ System.out.println("파일이 존재하지 않습니다."); }
            
         
         }
      photosDao.deleteByPid(pid);
      
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


   public Review getQna(int bno) {
      Review qna = qnaDao.selectByREVIEW_ID(bno);
      return qna;
   }


   public int getTotalRows() {
      int rows = qnaDao.count();
      return rows;
   }

   public List<String> GetOrderIdForReview(String user_id, int p_id) {
      // TODO Auto-generated method stub
      List<String> orderid = reviewDao.selectOrderIdForReview(user_id, p_id);
      
      return orderid;
   }

   public void ChangeRate(int p_id,int review_score) {
      // TODO Auto-generated method stub
      int reviewcount =reviewDao.getCount(p_id);
      int p_rate = productDao.selectBypid(p_id).getP_rate();
      
      p_rate+=review_score;
      p_rate /= (reviewcount);
      
      // rate update
      productDao.updateRate(p_id,p_rate);
      
   }
   
 


public int getCountSort(String countSort) {
	int count = productDao.countSort(countSort);
	return count;
}
   

}