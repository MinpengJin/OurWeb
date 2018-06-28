package com.OurWeb.database.impl;

import java.sql.*;

import com.OurWeb.database.IConnectDatabase;


public class ConnectDatabaseImpl implements IConnectDatabase {
	
	private Connection conn;
	private ResultSet queryRes;
	
	public ConnectDatabaseImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ourweb?useUnicode=true&characterEncoding=utf-8&useSSL=false",
					"root","13958548266");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ResultSet query(String sql) {
		try {
			Statement stmt = conn.createStatement();
			queryRes = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryRes;
	}

	@Override
	public void update(String sql) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
