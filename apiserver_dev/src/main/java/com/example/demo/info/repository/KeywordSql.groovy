package com.example.demo.info.repository

class KeywordSql {
	public static final String SELECT = """
		SELECT user_id, user_name, keyword, start_date, end_date, target_price, use_yn FROM keyword_data WHERE 1=1
	""";
	
	public static final String USER_ID_CONDITION = """
		AND user_id = :userID
	""";
	
	public static final String KEYWORD_CONDITION = """
			AND keyword = :keyword
			""";
	
	public static final String ID_CONDITION = """
			AND id = :ID
			""";
			
	public static final String INSERT = """
		INSERT INTO keyword_data(user_id, keyword, user_name, start_date, end_date, target_price, use_yn)
		values(:userID, :keyword, :userName, :startDate, :endDate, :targetPrice, :useYN )
	""";
	
	public static final String UPDATE = """
			UPDATE keyword_data SET user_name = :userName, start_date = :startDate, end_date = :endDate, target_price = :targetPrice, use_yn = :useYN
			WHERE 1=1
			""";
}
