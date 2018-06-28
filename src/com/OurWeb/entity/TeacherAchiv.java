package com.OurWeb.entity;

public class TeacherAchiv {
	
	private int id;					//成果类别
	private String category;		//成果子类别
	private String title;			//成果内容
	private int authorOrder;		//成果级别
	private int score;				//贡献值
	private String publishDate;		//发表时间
	private String thesisNum;		//论文编号
	private int srcId;				//论文来源号

	
	public int getSrcId() {
		return srcId;
	}
	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}
	public String getThesisNum() {
		return thesisNum;
	}
	public void setThesisNum(String thesisNum) {
		this.thesisNum = thesisNum;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthorOrder() {
		return authorOrder;
	}
	public void setAuthorOrder(int authorOrder) {
		this.authorOrder = authorOrder;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

	
}
