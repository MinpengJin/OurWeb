package com.OurWeb.entity;

public class TeacherAchiv {
	
	private int id;					//�ɹ����
	private String category;		//�ɹ������
	private String title;			//�ɹ�����
	private int authorOrder;		//�ɹ�����
	private int score;				//����ֵ
	private String publishDate;		//����ʱ��
	private String thesisNum;		//���ı��
	private int srcId;				//������Դ��

	
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
