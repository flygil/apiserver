package com.example.demo.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.info.model.KeywordData;

public class KeywordRowMapper implements RowMapper<KeywordData>{
	@Override
	public KeywordData mapRow(ResultSet rs, int rowNum) throws SQLException {
		KeywordData keyword = new KeywordData();
		keyword.setUserID(rs.getInt("user_id"));
		keyword.setUserName(rs.getString("user_name"));
		keyword.setKeyword(rs.getString("keyword"));
		keyword.setStartDate(rs.getString("start_date"));
		keyword.setEndDate(rs.getString("end_date"));
		keyword.setTargetPrice(rs.getInt("target_price"));
		keyword.setUseYN(rs.getString("use_yn"));
		
		return keyword;

	}
}
