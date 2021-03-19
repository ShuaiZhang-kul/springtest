package com.shuai.test;

import com.shuai.bean.Employee;
import com.shuai.bean.Employer;
import com.shuai.dao.EmployeeDao;
import com.shuai.dao.EmployerDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test() throws IOException {
//    1.构建sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//    2.获取一次和数据库的一次会话(getConnection)

        SqlSession openSession = sqlSessionFactory.openSession();
//    3.执行操作,得到dao的接口实现
        EmployerDao employerDaoImp = openSession.getMapper(EmployerDao.class);
//    4.接口执行sql语句通过方法
        Employer employer = employerDaoImp.getEmpById(1);

        System.out.println(employer);
    }
}
