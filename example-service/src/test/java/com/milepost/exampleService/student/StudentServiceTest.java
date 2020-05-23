//package com.milepost.exampleService.student;
//
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.milepost.api.dto.request.PageParameter;
//import com.milepost.exampleApi.entity.student.Student;
//import com.milepost.exampleApi.entity.student.StudentExample;
//import com.milepost.exampleService.student.service.StudentService;
//import com.milepost.service.config.dynamicDs.DataSourceContextHolder;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.text.ParseException;
//import java.util.*;
//
///**
// * 一般情况下，要对mbg自动生成的代码作如下更改，
// * --1. 要使用二级缓存，则对象要被序列化到硬盘，所以实体类要实现Serializable接口，见本单元测试中的testSecondLevelCache方法，（已经使用插件实现）
// * 		还需要在mapper文件中加<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>，（已经使用插件实现）
// * 2. 如果涉及到两个(多个)表关联的查询，即连接查询，则需要在实体类中增加关联的实体，如在Student中增加有一个private Classes classes属性并重新写toString方法，这样就可以使用这个实体类才传参数和封装结果集了。
// * 3. 自动生成的方法肯定不能满足复杂的业务需求，所以需要自己写sql语句，即更改StudentMapper.java(mapper接口)和StudentMapper.xml(mapper映射文件)
// * @author Huarf
// * 2017年9月16日
// */
//public class StudentServiceTest {
//
//	@Autowired
//	private StudentService studentService;
//
//	@Test
//	public void addTestData() throws ParseException {
//		for(int i=1; i<=23; i++){
//			Student record = new Student();
//			record.setId("" + i);
//			record.setName("" + i);
//			record.setStuNo("" + i);
//			record.setScore((float)i);
//			String birthStr = "1990-01-" + (i>10? "0"+i : i);
//			record.setBirth(DateUtils.parseDate(birthStr, "yyyy-MM-dd"));
//			record.setRemark("" + i);
//			record.setClassesId("0" + i%7);
//			int effectRow = studentService.insert(record);
//			System.out.println(effectRow);
//		}
//	}
//
//	@Test
//	public void testInsert() throws ParseException {
//		Student record = new Student();
//		record.setId(ObjectIdGenerators.UUIDGenerator.getUUID());
//		record.setName("张三");
//		record.setStuNo("1");
//		record.setScore(23f);
//		record.setBirth(DateUtils.parseDate("1990-01-01", "yyyy-MM-dd"));
//		String remark = "";
//		int remarkLength = 10000;
//		for(int i=0; i<remarkLength; i++){
//			remark = remark + "中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国"
//					+ "中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国";
//			System.out.println(i);
//		}
//		record.setRemark(remark);
//
//		record.setClassesId("01");
//		int effectRow = studentService.insert(record);
//		System.out.println(effectRow);
//
//		Student student = studentService.selectByPrimaryKey(record.getId());
//		System.out.println("Clob是否丢失内容：");
//		System.out.println(student.getRemark().length() != (remarkLength * 100));
//	}
//
//	@Test
//	public void testInsertSelective() throws ParseException {
//		Student record = new Student();
//		record.setId(UUIDGenerator.getUUID());
//		//record.setName("张三");
//		record.setStuNo("2");
//		record.setScore(23f);
//		record.setBirth(DateUtils.parseDate("1990-01-02", "yyyy-MM-dd"));
//		record.setRemark("努力学习");
//		record.setClassesId("02");
//		int effectRow = studentService.insertSelective(record);
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testUpdateByPrimaryKey() throws ParseException {
//		Student record = new Student();
//		record.setId("26456cdf7c8043d587994715768f81fb");
//		record.setName("33-");
//		record.setStuNo("33-");
//		record.setScore(33f);
//		record.setBirth(DateUtils.parseDate("1990-01-13", "yyyy-MM-dd"));
//		record.setRemark("33-");
//		record.setClassesId("33-");
//		int effectRow = studentService.updateByPrimaryKey(record);
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testUpdateByPrimaryKeySelective() throws ParseException {
//		Student record = new Student();
//		record.setId("26456cdf7c8043d587994715768f81fb");
//		//record.setName("44");
//		record.setStuNo("44");
//		record.setScore(44f);
//		record.setBirth(DateUtils.parseDate("1990-01-14", "yyyy-MM-dd"));
//		record.setRemark("44");
//		record.setClassesId("44");
//		int effectRow = studentService.updateByPrimaryKeySelective(record);
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testUpdateByExample() throws ParseException {
//		Student record = new Student();
//		record.setId("55");
//		record.setName("55");
//		record.setStuNo("55");
//		record.setScore(55f);
//		record.setBirth(DateUtils.parseDate("1990-01-15", "yyyy-MM-dd"));
//		record.setRemark("55");
//		record.setClassesId("55");
//
//		StudentExample example = new StudentExample();
//
//		StudentExample.Criteria criteria = example.createCriteria();
//		criteria.andIdEqualTo("26456cdf7c8043d587994715768f81fb").andNameLike("%张三%");
//
//		StudentExample.Criteria criteria1 = example.createCriteria();
//		criteria1.andBirthEqualTo(DateUtils.parseDate("1990-01-01 00:00:00")).andScoreEqualTo(1f);
//
//		example.or(criteria1);//两个条件用or连接
//
//		int effectRow = studentService.updateByExample(record, example);
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testUpdateByExampleSelective(){
//		Student record = new Student();
//		//record.setId("55");
//		//record.setName("55");
//		//record.setStuNo("55");
//		record.setScore(66f);
//		record.setBirth(DateUtil.parse("1990-01-16", "yyyy-MM-dd"));
//		record.setRemark("66");
//		record.setClassesId("66");
//
//		StudentExample example = new StudentExample();
//
//		Criteria criteria = example.createCriteria();
//		criteria.andIdEqualTo("55").andNameLike("%55%");
//
//		Criteria criteria1 = example.createCriteria();
//		criteria1.andBirthEqualTo(DateUtil.parse("1990-01-15", "yyyy-MM-dd")).andScoreEqualTo(55f);
//
//		example.or(criteria1);//两个条件用or连接
//
//		int effectRow = studentService.updateByExampleSelective(record, example);
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testDeleteByPrimaryKey(){
//		int effectRow = studentService.deleteByPrimaryKey("55");
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testDeleteByExample(){
//		StudentExample example = new StudentExample();
//
//		example.or().andIdEqualTo("2");
//		example.or().andRemarkLike("%2%").andScoreEqualTo(2f);
//		//不能用andRemarkBetween(between)、andRemarkEqualTo(=)、andRemarkGreaterThan(>)、andRemarkLessThan(<)、andRemarkIn(in)
//		//只能用like，与is null
//		int effectRow = studentService.deleteByExample(example);
//		System.out.println(effectRow);
//	}
//
//	@Test
//	public void testCountByExample(){
//		StudentExample example = new StudentExample();
//		example.or().andNameBetween("1","2");
//		int count = studentService.countByExample(example );
//		System.out.println(count);
//	}
//
//	@Test
//	public void testCountByExample1(){
//		StudentExample example = new StudentExample();
//		example.or().andNameEqualTo("2-");
//		int count = studentService.countByExample(example );
//		System.out.println(count);
//	}
//
//	@Test
//	public void testSelectByPrimaryKey() {
//		Student record = studentService.selectByPrimaryKey("12");
//		System.out.println(record);
//	}
//
//	/**
//	 * 	查询条件1：a=? and (b=? or c=?) 不支持
//		查询条件2：(a=? And b=?) or (a=? And c=?) 支持
//		这两个条件的效果是相同的，需要注意一下
//	 */
//	@Test
//	public void testCriteriaOr() {
//		StudentExample example = new StudentExample();
//
//		//方式一：
////		Criteria criteria = example.createCriteria();
////		//criteria.andIdEqualTo("5").andNameLike("%5%");
////		criteria.andIdEqualTo("5");
////
////		Criteria criteria1 = example.createCriteria();
////		criteria1.andBirthEqualTo(DateUtil.parse("1990-01-15", "yyyy-MM-dd")).andScoreEqualTo(5f);
////
////		example.or(criteria1);//两个条件用or连接
//		//WHERE ( ID = ? and NAME like ? ) or( BIRTH = ? and SCORE = ? )
//
//		//方式二：
////		example.or().andIdEqualTo("5").andNameLike("%5%");
////		example.or().andBirthEqualTo(DateUtil.parse("1990-01-15", "yyyy-MM-dd")).andScoreEqualTo(5f);
//		example.or().andIdEqualTo("5");
//		example.or().andBirthEqualTo(DateUtil.parse("1990-01-15", "yyyy-MM-dd")).andScoreEqualTo(5f);
//
//		List<Student> students = studentService.selectByExample(example );
//		//即时没有数据，也不会返回null，而是返回空list
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//	@Test
//	public void testCriteriaAnd() {
//		StudentExample example = new StudentExample();
//
//		//方式一
////		Criteria criteria = example.createCriteria();
////		criteria.andIdEqualTo("5");
////		criteria.andNameLike("%5%");
//		//WHERE ( ID = ? and NAME like ? )
//
//		//方式二
//		example.or().andIdEqualTo("5").andNameLike("%5%");
//		//WHERE ( ID = ? and NAME like ? )
//
//		List<Student> students = studentService.selectByExample(example );
//		//即时没有数据，也不会返回null，而是返回空list
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//
//	@Test
//	public void testSelectByExample() {
//		StudentExample example = new StudentExample();
//
//		//example.or().andScoreGreaterThan(20f);
//		//example.or().andNameIn(Arrays.asList("1","2","3"));
//		example.or().andNameIn(Arrays.asList("3"));
//		example.setOrderByClause("score desc");
//
//		List<Student> students = studentService.selectByExample(example );
//		//即时没有数据，也不会返回null，而是返回空list
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//	@Test
//	public void testTransactional(){
//		Student record = new Student();
//		record.setId("1-");
//		record.setName("张三");
//		record.setStuNo("zhangsan");
//		record.setScore(23f);
//		record.setBirth(DateUtil.parse("1990-01-01", "yyyy-MM-dd"));
//		record.setRemark("努力学习");
//		record.setClassesId("01");
//
//		Student record2 = new Student();
//		record2.setId("2-");
//		record2.setName("李四");
//		record2.setStuNo("lisi");
//		record2.setScore(23f);
//		record2.setBirth(DateUtil.parse("1990-01-01", "yyyy-MM-dd"));
//		record2.setRemark("好吃懒惰");
//		record2.setClassesId("02");
//
//		studentService.testTransactional(record,record2);
//	}
//
//	@Test
//	public void testTransactional1(){
//		Student record = new Student();
//		record.setId("1-");
//		record.setName("张三");
//		record.setStuNo("zhangsan");
//		record.setScore(23f);
//		record.setBirth(DateUtil.parse("1990-01-01", "yyyy-MM-dd"));
//		record.setRemark("努力学习");
//		record.setClassesId("01");
//
//		Student record2 = new Student();
//		record2.setId("2-");
//		record2.setName("李四");
//		record2.setStuNo("lisi");
//		record2.setScore(23f);
//		record2.setBirth(DateUtil.parse("1990-01-01", "yyyy-MM-dd"));
//		record2.setRemark("好吃懒惰");
//		record2.setClassesId("02");
//
//		studentService.testTransactional1(record,record2);
//	}
//
//	@Test
//	public void testOracleInLength(){
//		StudentExample example = new StudentExample();
//		List<String> ids = new ArrayList<String>();
//		ids.add("12");
//		ids.add("13");
//		//in关键字最多支持1000个元素
//		for(int i=0; i<(1000-2); i++){
//			ids.add(UUIDGenerator.getUUID());
//		}
//		example.or().andIdIn(ids);
//		List<Student> students = studentService.selectByExample(example);
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//	/**
//	 * In关键字可以传入一个List，但是这个list.size()必须>0，否则会报错，所以在使用之前应该判断，
//	 * 解决方法如下：
//	 */
//	@Test
//	public void testInKeyWord(){
//		//错误的
////		List<String> idList = new ArrayList<String>();//从其他方法返回的list
////		StudentExample example = new StudentExample();
////		example.or().andNameLike("%1%").andIdIn(idList);
//
//		StudentExample example = new StudentExample();
//
//		//解决方法一：不能使用这种方法简单的把条件去掉，因为条件去掉之后就会查询出所有数据，而正确的是应该查不出任何数据，
////		List<String> idList = new ArrayList<String>();//从其他方法返回的list
////		Criteria criteria = example.createCriteria();
////		criteria.andNameLike("%1%");
////		if(idList!=null && idList.size()>0){
////			criteria.andIdIn(idList);
////		}
//
//		//解决方法二：
//		List<String> idList = new ArrayList<String>();//从其他方法返回的list
//		if(idList!=null && idList.size()==0){
//			idList.add("特殊字符，使其至少有一个元素，而且不影响查询结果");
//		}
//
//		List<Student> students = studentService.selectByExample(example);
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//
//	@Test
//	public void testSelectAll(){
//		List<Student> students = studentService.selectAll();
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//	@Test
//	public void testSelectOneByExample() throws Exception{
//		StudentExample example = new StudentExample();
//		//example.or().andNameEqualTo(null);//java.lang.RuntimeException: Value for name cannot be null
//		example.or().andNameLike("%23%");
//		Student student = studentService.selectOneByExample(example);
//		System.out.println(student);
//	}
//
//	@Test
//	public void testSelectByExampleForList(){
//		PageParameter pageParameter = new PageParameter(1, 4);
//		//pageParameter.setReasonable(false);
//		//pageParameter.setCountSql(false);
//		StudentExample example = new StudentExample();
//		example.setOrderByClause("score asc");
//		List<Student> students = studentService.selectByExampleForList(example , pageParameter);
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
////	@Test
////	public void testSelectByExampleForPage(){
////		PageParameter pageParameter = new PageParameter(1, 5);
////		//pageParameter.setReasonable(false);
////		//pageParameter.setCountSql(false);//total(总记录数):-1
////		StudentExample example = new StudentExample();
////		example.setOrderByClause("score asc");
////		Page<Student> page = studentService.selectByExampleForPage(example , pageParameter);
////		System.out.println("pages(总页数):" + page.getPages());
////		System.out.println("pageSize(页大小):" + page.getPageSize());
////		System.out.println("pageNum(页码):" + page.getPageNum());
////		System.out.println("total(总记录数):" + page.getTotal());
////		System.out.println("startRow(首行索引，从0开始，结果集包含startRow):" + page.getStartRow());
////		System.out.println("endRow(尾行索引，从0开始，结果集不包含endRow):" + page.getEndRow());
////		for(Student student : page){
////			System.out.println(student);
////		}
////	}
//
//	@Test
//	public void testSelectByExampleForPageInfo(){
//		PageParameter pageParameter = new PageParameter(1, 5);
//		pageParameter.setCountSql(false);//total(总记录数):-1
//		StudentExample example = new StudentExample();
//		example.setOrderByClause("score asc");
//		PageInfo<Student> pageInfo = studentService.selectByExampleForPageInfo(example , pageParameter);
//		System.out.println("pages(总页数):" + pageInfo.getPages());
//		System.out.println("pageSize(页大小):" + pageInfo.getPageSize());
//		System.out.println("size(当前页记录数):" + pageInfo.getPageSize());
//		System.out.println("prePage(前一页):" + pageInfo.getPrePage());
//		System.out.println("pageNum(当前页码):" + pageInfo.getPageNum());
//		System.out.println("nextPage(后一页):" + pageInfo.getNextPage());
//		System.out.println("isFirstPage(是否为第一页):" + pageInfo.isIsFirstPage());
//		System.out.println("isLastPage(是否为最后一页):" + pageInfo.isIsLastPage());
//		System.out.println("hasPreviousPage(是否有前一页):" + pageInfo.isHasPreviousPage());
//		System.out.println("hasNextPage(是否有后一页):" + pageInfo.isHasNextPage());
//		System.out.println("navigatePages(导航页码数):" + pageInfo.getNavigatePages());
//		System.out.print("navigatepageNums(所有导航页号):");
//		int[] navigatepageNums = pageInfo.getNavigatepageNums();
//		for(int i : navigatepageNums){
//			System.out.print(i + ",");
//		}
//		System.out.println("");
//		System.out.println("navigateFirstPage(导航条上的第一页):" + pageInfo.getNavigateFirstPage());
//		System.out.println("navigateLastPage(导航条上的最后一页):" + pageInfo.getNavigateLastPage());
//		System.out.println("total(总记录数):" + pageInfo.getTotal());
//		System.out.println("startRow(首行索引，从1开始，结果集包含startRow):" + pageInfo.getStartRow());
//		System.out.println("endRow(尾行索引，从1开始，结果集包含endRow):" + pageInfo.getEndRow());
//		List<Student> students = pageInfo.getList();
//		for(Student student : students){
//			System.out.println(student);
//		}
//	}
//
//	/**
//	 * mybatis的一级缓存：（本地缓存）：SqlSession级别的缓存。一级缓存是一直开启的，我们没法关闭；是SqlSession级别的一个Map
//	 * 见D:\JavaSoftware\eclipse-mars-RWorkspace\workspace2\MyBatis_05_cache\src\com\atguigu\mybatis\test\MyBatisTest.java
//	 */
//	@Test
//	public void testFirstLevelCache(){
//		studentService.testFirstLevelCache("3");
//	}
//
//	/**
//	 * 需要在mapper映射文件中加：
//	 * <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
//	 * 需要实体类实现Serializable接口
//	 */
//	@Test
//	public void testSecondLevelCache(){
//		Student student1 = studentService.selectByPrimaryKey("3");
//		System.out.println(student1);
//		Student student2 = studentService.selectByPrimaryKey("3");
//		System.out.println(student2);
//		Student student3 = studentService.selectByPrimaryKey("4");
//		student3.setName(student3.getName() + "-");
//		//二级缓存在数据更改之后自动失效
//		studentService.updateByPrimaryKey(student3);
//		Student student4 = studentService.selectByPrimaryKey("3");
//		System.out.println(student4);
//	}
//
//	/**
//	 * 测试下划线转驼峰
//	 * 测试自定义的sql语句
//	 * 测试map传参数
//	 *
//	 * D:\JavaSoftware\eclipse-mars-RWorkspace\workspace2\MyBatis_04_DynamicSQL\mybatis参数处理.txt
//	 *
//	 * 	Map：
//		如果多个参数不是业务模型中的数据，没有对应的pojo，不经常使用，为了方便，我们也可以传入map，这中方式有缺点，就是别人使用方法时候不知道可以应该传入哪些参数，所以不推荐使用
//			#{key}：取出map中对应的值
//
//		TO：
//		如果多个参数不是业务模型中的数据，但是经常要使用，推荐来编写一个TO（Transfer Object）数据传输对象
//		Page{
//			int index;
//			int size;
//		}
//	 *
//	 * @param paramsMap
//	 * @return
//	 */
//	@Test
//	public void testMapUnderscoreToCamelCase(){
//		//mapUnderscoreToCamelCase属性默认是关闭的，mbg自动生成的sql映射都自己定义了resultMap，
//		//所以可以不打开mapUnderscoreToCamelCase属性，但是自己写的sql语句映射，打开mapUnderscoreToCamelCase属性还是很方便的
//
//		Map<String, Object> paramsMap = new HashMap<String, Object>();
//		paramsMap.put("id", "3");
//		//paramsMap.put("name", "1");
//		paramsMap.put("score", 3f);
//		Student student = studentService.testMapUnderscoreToCamelCase(paramsMap);
//		System.out.println(student);
//	}
//
//	/**
//	 * 测试两个表连接的查询，如查询学生和学生所在的班级，显示出列表，并且有分页，有条件
//	 * 首先要在Student中增加Classes属性
//	 * 结果和参数都用Student，sql语句的查询字段列表中被关联的对象(Classes)的字段要写别名，否则映射不到javabean的属性中
//	 * 也可以使用map来封装结果集和传参数，但是那样的代码可读性差，别人不知道这个map中应该放入什么样的参数。
//	 * @param student
//	 * @return
//	 */
//	@Test
//	public void testMultiTableSelect(){
//		Student student = new Student();
//		//student.setId("3");
//		student.setName("%2%");
//		List<Student> students = studentService.testMultiTableSelect(student);
//		for(Student student2 : students){
//			System.out.println(student2);
//		}
//	}
//
//	@Test
//	public void testMultiTableSelectWithPage(){
////		PageHelper.startPage(pageParameter);
////		List<M> list = baseMapper.selectByExample(example);
////		return new PageInfo<M>(list);
//		PageParameter pageParameter = new PageParameter(1, 3);
//
//		PageHelper.startPage(pageParameter);
//		Student student = new Student();
//		student.setName("%%");
//		List<Student> students = studentService.testMultiTableSelect(student);
//		for(Student student2 : students){
//			System.out.println(student2);
//		}
//		PageInfo<Student> pageInfo = new PageInfo<Student>(students);
//		System.out.println(pageInfo);
//	}
//
//	@Test
//	public void testMultiDataSource(){
//		//每次动态改变之后，在一个线程中一直有效，
//		DataSourceContextHolder.setDataSourceType("dataSourceKey2");
//		multiDataSource1();
//		DataSourceContextHolder.clearDataSourceType();
//		multiDataSource2();
//
//		DataSourceContextHolder.clearDataSourceType();
//		//调用了DataSourceContextHolder.setDataSourceType("dataSourceKey2");设置数据源之后，
//		//最好再次调用DataSourceContextHolder.clearDataSourceType();来清空并释放资源。
//	}
//
//	public void multiDataSource1(){
//		Map<String, Object> paramsMap = new HashMap<String, Object>();
//		paramsMap.put("id", "1");
//		//paramsMap.put("name", "1");
//		paramsMap.put("score", 1f);
//		Student student = studentService.testMapUnderscoreToCamelCase(paramsMap);
//		System.out.println(student.getName());
//	}
//
//	public void multiDataSource2(){
//		Map<String, Object> paramsMap = new HashMap<String, Object>();
//		paramsMap.put("id", "1");
//		//paramsMap.put("name", "1");
//		paramsMap.put("score", 1f);
//		Student student = studentService.testMapUnderscoreToCamelCase(paramsMap);
//		System.out.println(student.getName());
//	}
//
//
//
//}
