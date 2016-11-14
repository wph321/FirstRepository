package com.dealdata;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {
	public static void main(String[] args) throws IOException {
		
		String resource = "conf.xml"; 
		//����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
		Reader reader = Resources.getResourceAsReader(resource); 
		//����sqlSession�Ĺ���
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//������ִ��ӳ���ļ���sql��sqlSession
		SqlSession session = sessionFactory.openSession();
		//ӳ��sql�ı�ʶ�ַ���
	String statement = "com.dealdata.UserMapper.insertUser";
		//ִ�в�ѯ����һ��Ψһuser�����sql
    try {
       User users = new User();
    	int user = session.insert(statement,new User(-1,"372531652","13111153283",null,"�����"));
        session.commit();
        System.out.println(user);
        System.out.println("�����û�ID��" + users.getId());
    } finally {
        session.close();
    }
//		System.out.println(user.getId());
//		System.out.println(user.getUser_namber());
//		System.out.println(user.getUser_passworld());
//		System.out.println(user.getUser_name());
	
	}
}
