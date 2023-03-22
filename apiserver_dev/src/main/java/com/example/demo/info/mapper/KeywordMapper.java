package com.example.demo.info.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.info.domain.Keyword;


@Mapper
public interface KeywordMapper {
	
	List<Keyword> keywordListByMybatis();
}
