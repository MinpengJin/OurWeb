package com.OurWeb.database;

import java.sql.ResultSet;

public interface IConnectDatabase {
	//ʵ�ֲ�ѯ����
	ResultSet query(String sql);
	//ʵ�ָ��¹���
	void update(String sql);
	//�ر����ݿ�
	void close();
}
