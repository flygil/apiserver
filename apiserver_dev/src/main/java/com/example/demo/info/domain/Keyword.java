package com.example.demo.info.domain;

import lombok.Data;

@Data
public class Keyword {
	private int id;
	private int userID;
	private String userName;
	private String keyword;
	private String startDate;
	private String endDate;
	private int targetPrice;
	private String useYn;
}
