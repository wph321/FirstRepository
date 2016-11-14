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
		//加载mybatis的配置文件（它也加载关联的映射文件）
		Reader reader = Resources.getResourceAsReader(resource); 
		//构建sqlSession的工厂
SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession();
		//映射sql的标识字符串
	String statement = "com.dealdata.UserMapper.insertUser";
		//执行查询返回一个唯一user对象的sql
    try {
       User users = new User();
    	int user = session.insert(statement,new User(-1,"372531652","13111153283",null,"李冰冰"));
        session.commit();
        System.out.println(user);
        System.out.println("新增用户ID：" + users.getId());
    } finally {
        session.close();
    }
//		System.out.println(user.getId());
//		System.out.println(user.getUser_namber());
//		System.out.println(user.getUser_passworld());
//		System.out.println(user.getUser_name());
	
	}
}
