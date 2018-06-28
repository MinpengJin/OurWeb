package com.OurWeb.entity;


public class Teacher {

	private int id;				//工号
	private String name;		//名字
	private String title;		//职称
	private String examType;	//考核类型
	private String examTime;		//考核时间
	private String password;	//密码
	private int targetWork;		//目标工作量
	private int currentWork;	//当前工作量
	
	public int getCurrentWork() {
		return currentWork;
	}
	public void setCurrentWork(int currentWork) {
		this.currentWork = currentWork;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getTargetWork() {
		return targetWork;
	}
	public void setTargetWork(int targetWork) {
		this.targetWork = targetWork;
	}
	
	
}
