package com.example.demo.info;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.model.Project;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.*;

@Slf4j
@RestController
public class InfoController {
	
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
}
