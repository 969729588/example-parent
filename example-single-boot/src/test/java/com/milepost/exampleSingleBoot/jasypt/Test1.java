package com.milepost.exampleSingleBoot.jasypt;

import com.milepost.exampleSingleBoot.ExampleSingleBootApplication;
import com.milepost.test.BaseTest;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Ruifu Hua on 2020/2/7.
 */
public class Test1 extends BaseTest<ExampleSingleBootApplication> {

    private StringEncryptor encryptor;

    @Before
    public void init(){
        encryptor = getBean(StringEncryptor.class);
    }

    @Test
    public void test2(){
        System.out.println("--------");
    }

    /**
     * #jasypt加密的密匙
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        String password = "认证服务";
        System.out.println(encryptor.encrypt(password));
    }
}
