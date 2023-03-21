package com.example.demo.info;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.model.Keyword;
import com.example.demo.info.model.Project;
import com.example.demo.info.repository.KeywordRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService {
	
	@Autowired
	private final KeywordRepository keywordRepository;
	
	public InfoService(KeywordRepository keywordRepository) {
		this.keywordRepository = keywordRepository;
	}

	public Project getProjectInfo() {
		
		Project project = new Project();
		project.projectName ="API Server Service TEST";
		project.author ="flygil";
		project.createDate = new Date();
		return project;
	}
	
	public List<Keyword> getKeywordList() {
		
		return this.keywordRepository.findList();
	}
	
	public List<Keyword> findKeywordByPK(Integer userID, String keyword) {
		
		return this.keywordRepository.findKeywordByPK(userID, keyword);
	}
	
	public Keyword insert(Keyword keyword) {
		return this.keywordRepository.insert(keyword);
	}
	
	public Integer updateByPK(Keyword keyword) {
		log.debug("keyword = {}", keyword.toString());
		return this.keywordRepository.updateByPK(keyword);
	}
	
}
