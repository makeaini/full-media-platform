//package com.java.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.inred.media.dao.UserDaoI;
//import com.inred.media.model.ResourcesRole;
//import com.inred.media.model.UserType;
//import com.inred.media.pojo.User;
//import com.inred.media.util.FileHashUtil;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application-config.xml")
//public class UserTest {
//	@Autowired
//	private UserDaoI userDaoI;
//	@Test
//    public void adduser(){
//		User user=new User();
//		user.setEmail("527266321@qq.com");
//		user.setName("刘庆");
//		user.setUsername("aaron");
//		user.setPassword("aaron");
//		user.setRemark("我是一个学校管理员!");
//		user.setResourcesRole(ResourcesRole.HAVEROLE);
//		user.setSchooldIds("cored");
//		user.setSchoolId("560786738b6b73276de01242");
//		user.setType(UserType.SCHOOLMANAGER);
//		String password=FileHashUtil.stringMD5(user.getPassword());
//		user.setPassword(password);
//		userDaoI.insertUser(user);
//		
//		
//		
//		
//	}
//
//}
