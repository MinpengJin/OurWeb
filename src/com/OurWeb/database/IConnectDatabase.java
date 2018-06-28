package com.OurWeb.database;

import java.sql.ResultSet;

public interface IConnectDatabase {
	//实现查询功能
	ResultSet query(String sql);
	//实现更新功能
	void update(String sql);
	//关闭数据库
	void close();
}
