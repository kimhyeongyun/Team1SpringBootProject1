package com.mycompany.webapp.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Photo;

@Mapper
public interface PhotosDao {
   public int insert(Photo photo);
   public int update(Photo photo);
   public int deleteByPhotoid(int photoId);
   public int deleteByPid(int pid);
   public Photo selectname(Photo photo);
   public List<Photo> selectByPid(int pid);
}