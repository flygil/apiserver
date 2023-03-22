package com.example.demo.info.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.info.model.KeywordData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class KeywordRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final KeywordRowMapper keywordRowMapper;
	
	public KeywordRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.keywordRowMapper = new KeywordRowMapper();
	}
	
	public List<KeywordData> findList(){
		//String keywordSql = "select * from keyword limit 1000";
		
		log.debug("findList query : {}", KeywordSql.SELECT);
		
		return namedParameterJdbcTemplate.query(KeywordSql.SELECT
												, EmptySqlParameterSource.INSTANCE
												, this.keywordRowMapper);
	}	
	
	public List<KeywordData> findKeywordByPK(Integer userID, String keyword){
		//String keywordSql = "select * from keyword limit 1000";
		
		log.debug("findList query : {}", KeywordSql.SELECT
											+ KeywordSql.USER_ID_CONDITION
											+ KeywordSql.KEYWORD_CONDITION
				);
		
		SqlParameterSource param = new MapSqlParameterSource("userID", userID)
				.addValue("keyword", keyword);
		
		return namedParameterJdbcTemplate.query(KeywordSql.SELECT + KeywordSql.USER_ID_CONDITION + KeywordSql.KEYWORD_CONDITION
				, param
				, this.keywordRowMapper);
	}	
	
	public KeywordData insert(KeywordData keyword) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("userID", keyword.getUserID())
				.addValue("keyword", keyword.getKeyword())
				.addValue("userName", keyword.getUserName())
				.addValue("startDate", keyword.getStartDate()) 
				.addValue("endDate", keyword.getEndDate()) 
				.addValue("targetPrice", keyword.getTargetPrice()) 
				.addValue("useYN", keyword.getUseYN()); 
		int affectedRows = namedParameterJdbcTemplate.update(KeywordSql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKey());
		keyword.setID(keyHolder.getKey().intValue());
		return keyword;
	}
	
	public Integer updateByPK(KeywordData keyword) {
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("ID", keyword.getID())
				.addValue("userID", keyword.getUserID())
				.addValue("keyword", keyword.getKeyword())
				.addValue("userName", keyword.getUserName())
				.addValue("startDate", keyword.getStartDate()) 
				.addValue("endDate", keyword.getEndDate()) 
				.addValue("targetPrice", keyword.getTargetPrice()) 
				.addValue("useYN", keyword.getUseYN()); 
		
		return namedParameterJdbcTemplate.update(KeywordSql.UPDATE + KeywordSql.ID_CONDITION + KeywordSql.USER_ID_CONDITION + KeywordSql.KEYWORD_CONDITION, parameterSource);
	}
}