package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.SizeProduct;

@Mapper
public interface SizeProductDao {

	int insertSize(SizeProduct sizeproduct);
	int deleteSizeByPid(int p_id);
}
