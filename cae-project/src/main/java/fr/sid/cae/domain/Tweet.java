package fr.sid.cae.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tweet")
public class Tweet {
	
	private static final String textFormat = "dd/MM/yyyy HH:mm:ss";
	private static final String dbFormat = "yyyyMMddHHmmss";
	
	@Id
	@Column(name = "id", length = 36)
	private String id;
	@Column(name = "userid", length = 36)
	private String userId;
	@Column(name = "date")
	private Date date = new Date();
	@Column(name = "content")
	private String content;
	
	//private User user;
	
	public String getDateText(){
		return new SimpleDateFormat(textFormat).format(date);
	}
	
	public String getDateDB(){
		return new SimpleDateFormat(dbFormat).format(date);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
