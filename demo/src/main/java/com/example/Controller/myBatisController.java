package com.example.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.myBatisTest.Model.users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class myBatisController  {


    public void HelloWorld(){
        System.out.println("HelloWorld!!!");
    }

    @RequestMapping("/test01")
    @ResponseBody
    public void myBatisTest () throws IOException {
        String resource = "myBatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            users user = sqlSession.selectOne("com.example.myBatisTest.Model.usersMapper.selectUsers", 1);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
    }
}
