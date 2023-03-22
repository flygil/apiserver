package com.example.demo.info;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.domain.Keyword;
import com.example.demo.info.model.KeywordData;
import com.example.demo.info.model.Project;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InfoController {
	
	private InfoService infoService;
	
	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}
	
	@GetMapping("/info")
	public Object projectInfo() {
		log.debug("/info start");
		
		Project project = new Project();
		project.projectName = "API Server TEST";
		project.author = "flygil";
		project.createDate = new Date();
		
		log.info("return {}", project.toString());
		return project;
	}
	
	@GetMapping("/info2")
	public String customJson() {
		JsonObject jo = new JsonObject();
		
		jo.addProperty("projectName", "API Server TEST");
		jo.addProperty("author", "flygil");
		jo.addProperty("createDate", new Date().toString());
		
		JsonArray ja = new JsonArray();
		
		for(int i=0;i<5;i++) {
			JsonObject jobj = new JsonObject();
			jobj.addProperty("prop"+i, i);
			ja.add(jobj);
		}
		
		jo.add("follower", ja);
			
		return jo.toString();
	}
	
	
	@GetMapping("/infoservice")
	public Object Projectinfo() {
		log.debug("/infoservice start");
		
		Project project = this.infoService.getProjectInfo();
		return project;
	}
	
	
	@GetMapping("/keywordList")
	public Object getKeywordList() {
		log.debug("/keywordList start");
		
		List<KeywordData> keywordList = this.infoService.getKeywordList();
		return keywordList;
	}
	
	@GetMapping("/keywordListByMybatis")
	public Object getKeywordListByMybatis() {
		log.debug("/keywordListByMybatis start");
		
		List<Keyword> keywordList = this.infoService.getKeywordListByMybatis();
		return keywordList;
	}
	
	
	@GetMapping("/keywordByPK")
	public Object keywordByPK(@RequestParam("userID") Integer userID, @RequestParam("keyword") String keyword) {
		log.debug("/keywordByPK start");
		log.debug("userID = {}, keyword = {}", userID, keyword);
		
		List<KeywordData> keywordList = this.infoService.findKeywordByPK(userID, keyword);
		return keywordList;
	}
	
	//GET방식
	/*
	@GetMapping("/keywordAdd")
	public ResponseEntity<String> keywordAdd(@RequestParam(value="userID", required=true) Integer userID
							, @RequestParam(value="keyword", required=true) String keyword
							, @RequestParam(value="userName", required=true) String userName
							, @RequestParam(value="startDate", required=true) String startDate
							, @RequestParam(value="endDate", required=true) String endDate
							, @RequestParam(value="targetPrice", required=true) String targetPrice
							, @RequestParam(value="use_yn", required=false, defaultValue = "Y") String useYN) {
		log.debug("/keywordAdd start");
		log.debug("userID = {}, keyword = {}, userName = {}, startDate = {}, endDate = {}, targetPrice = {}, useYN = {}"
				, userID, keyword, userName, startDate, endDate, targetPrice, useYN);
		
		//List<Keyword> keywordList = this.infoService.addKeyword(userID, keyword, userName, startDate, endDate, targetPrice, useYN);
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}
	*/
	
	/*
	@GetMapping(value="keywordAdd")
	public Object cityAdd(Keyword keyword) {
		log.debug("keyword = {}", keyword.toString());
		return "ok";
	}
	*/
	
	
	
	//POST방식

	/*
	 * 요청 파라미터가 JSON일떄
	
	@PostMapping(value="keywordAdd")
	public ResponseEntity<Keyword> keywordAdd(@RequestBody Keyword keyword) {
		try {
			log.debug("keyword = {}", keyword.toString());
			return new ResponseEntity<>(keyword, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}		
	 */
	
	/*
	 * 요청을 Query params로 할때(form태그같은)
	 */
	
	@PostMapping(value="keywordAdd")
	public ResponseEntity<KeywordData> keywordAdd(Integer userID, String keyword, String userName, String startDate, String endDate, Integer targetPrice, String useYN) {
		try {
			log.debug("userID = {}, keyword = {}, userName = {}, startDate = {}, endDate = {}, targetPrice = {}, useYN = {}"
					, userID, keyword, userName, startDate, endDate, targetPrice, useYN);
			KeywordData keywordObj = new KeywordData();
			keywordObj.setUserID(userID);
			keywordObj.setKeyword(keyword);
			keywordObj.setUserName(userName);
			keywordObj.setStartDate(startDate);
			keywordObj.setEndDate(endDate);
			keywordObj.setTargetPrice(targetPrice);
			keywordObj.setUseYN(useYN);
			
			return new ResponseEntity<>(this.infoService.insert(keywordObj), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}		
	
	@PostMapping(value="keywordEdit")
	public ResponseEntity<String> keywordEdit(Integer userID, String keyword, String userName, String startDate, String endDate, Integer targetPrice, String useYN, Integer ID) {
		try {
			log.debug("ID = {}, userID = {}, keyword = {}, userName = {}, startDate = {}, endDate = {}, targetPrice = {}, useYN = {}"
					,ID, userID, keyword, userName, startDate, endDate, targetPrice, useYN);
			KeywordData keywordObj = new KeywordData();
			keywordObj.setID(ID);
			keywordObj.setUserID(userID);
			keywordObj.setKeyword(keyword);
			keywordObj.setUserName(userName);
			keywordObj.setStartDate(startDate);
			keywordObj.setEndDate(endDate);
			keywordObj.setTargetPrice(targetPrice);
			keywordObj.setUseYN(useYN);
			
			Integer updateCnt = this.infoService.updateByPK(keywordObj);
			
			return new ResponseEntity<>(String.format("%d updated", updateCnt), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}		
	
	
}
