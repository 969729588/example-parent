package com.milepost.exampleService.mongodb;

import com.milepost.exampleApi.entity.student.Student;
import com.milepost.exampleService.ExampleServiceApplication;
import com.milepost.test.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruifu Hua on 2020-05-12.
 *
 * https://blog.csdn.net/weixin_39729846/article/details/82626721
 *
 * https://www.jianshu.com/p/a576499769ae
 */
public class TestMongoTemplate extends BaseTest<ExampleServiceApplication> {

    private MongoTemplate mongoTemplate;

    /**
     * 获取要测试的bean
     */
    @Before
    public void before(){
        mongoTemplate = getBean(MongoTemplate.class);
    }

    /**
     * 测试后的收尾
     */
    @After
    public void after(){
        System.out.println("测试后的收尾");
    }

    @Test
    public void test1(){
        System.out.println(mongoTemplate.getDb().getName());
    }

    @Test
    public void testInsert(){
        List<Student> studentList = new ArrayList<>();
        for(int i=0; i<10; i++){
            Student student1 = new Student();
            student1.setId(i + "");
            student1.setName("张三" + i);
            student1.setBirth(new Date());
            student1.setScore((float) i);
            studentList.add(student1);
        }
        Collection<Student> result = mongoTemplate.insert(studentList, "test1");
        System.out.println(result);
    }

    @Test
    public void findAll(){
        Collection<Student> result = mongoTemplate.findAll(Student.class, "test1");
        for(Student student : result){
            System.out.println(student);
        }
    }

    @Test
    public void findById(){
        Student student = mongoTemplate.findById("1", Student.class);
        System.out.println(student);
    }
}
