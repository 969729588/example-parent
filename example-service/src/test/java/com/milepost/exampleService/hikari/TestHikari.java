package com.milepost.exampleService.hikari;

import com.milepost.exampleService.ExampleServiceApplication;
import com.milepost.test.BaseTest;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Ruifu Hua on 2020/3/23.
 */
public class TestHikari extends BaseTest<ExampleServiceApplication>{

    private DataSource dataSource;

    @Before
    public void init(){
        dataSource = getBean(DataSource.class);
    }

    /**
     * 测试Hikari连接池属性配置是否有效果
     */
    @Test
    public void test1() throws SQLException {
        for(int i=0; i<20; i++){
            System.out.println("第"+ (i+1) +"个连接池：" + dataSource.getConnection());
            System.out.println("---------------");
        }
    }
}
