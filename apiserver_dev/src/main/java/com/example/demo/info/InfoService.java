package com.example.demo.info;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.domain.Keyword;
import com.example.demo.info.mapper.KeywordMapper;
import com.example.demo.info.model.KeywordData;
import com.example.demo.info.model.Project;
import com.example.demo.info.repository.KeywordRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService {
	
	@Autowired
	private final KeywordRepository keywordRepository;
	
	@Autowired
	private final KeywordMapper keywordMapper;
	
	public InfoService(KeywordRepository keywordRepository, KeywordMapper keywordMapper) {
		this.keywordRepository = keywordRepository;
		this.keywordMapper = keywordMapper;
	}

	public Project getProjectInfo() {
		
		Project project = new Project();
		project.projectName ="API Server Service TEST";
		project.author ="flygil";
		project.createDate = new Date();
		return project;
	}
	
	public List<KeywordData> getKeywordList() {
		
		return this.keywordRepository.findList();
	}
	
	public List<KeywordData> findKeywordByPK(Integer userID, String keyword) {
		
		return this.keywordRepository.findKeywordByPK(userID, keyword);
	}
	
	public KeywordData insert(KeywordData keyword) {
		return this.keywordRepository.insert(keyword);
	}
	
	public Integer updateByPK(KeywordData keyword) {
		log.debug("keyword = {}", keyword.toString());
		return this.keywordRepository.updateByPK(keyword);
	}
	
	public List<Keyword> getKeywordListByMybatis() {
		return keywordMapper.keywordListByMybatis();
	}
	
}
