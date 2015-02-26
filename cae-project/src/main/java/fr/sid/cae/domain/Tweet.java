package fr.sid.cae.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet {
	
	private static final String textFormat = "dd/MM/yyyy HH:mm:ss";
	private static final String dbFormat = "yyyyMMddHHmmss";
	
	private String id;
	private String userId;
	private Date date = new Date();
	private String content;
	
	private User user;
	
	public String getDateText(){
		return new SimpleDateFormat(textFormat).format(date);
	}
}
