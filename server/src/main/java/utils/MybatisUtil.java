package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public  class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        //获取sqlSessionFatory对象
        try {

            InputStream is = Resources.class.getResourceAsStream("/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //从sqlSessionFactory中获得SqlSession的实例了。
    //SqlSession完全包含了面向数据库执行SQL命令所需的所有方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
