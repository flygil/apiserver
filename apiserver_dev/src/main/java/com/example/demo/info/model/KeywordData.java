package com.example.demo.info.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeywordData {
	private Integer ID;
	private Integer userID;
	private String userName;
	private String keyword;
	private String startDate;
	private String endDate;
	private Integer targetPrice;
	private String useYN;
}
