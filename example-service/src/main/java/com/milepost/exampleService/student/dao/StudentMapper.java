package com.milepost.exampleService.student.dao;

import com.milepost.exampleApi.entity.student.Student;
import com.milepost.exampleApi.entity.student.StudentExample;
import com.milepost.service.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author Huarf
 * 2017年9月17日
 */
@Mapper//相当于org.mybatis.spring.mapper.MapperScannerConfigurer.basePackage=com.milepost.**.dao
public interface StudentMapper extends BaseMapper<Student, StudentExample> {

	Student testMapUnderscoreToCamelCase(Map<String, Object> paramsMap);

	List<Student> testMultiTableSelect(@Param("example") Student student);

}