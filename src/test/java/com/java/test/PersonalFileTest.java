//package com.java.test;
//
//import java.util.Date;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.inred.media.model.FileType;
//import com.inred.media.model.Pagination;
//import com.inred.media.model.QueryPersonalFileBean;
//import com.inred.media.pojo.PersonalFile;
//import com.inred.media.service.PersonalFileServiceI;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application-config.xml")
//public class PersonalFileTest {
//	@Autowired
//	private PersonalFileServiceI  personalFileServiceI;
//	@Test
//	public void test() {
//		QueryPersonalFileBean personalFileBean=new QueryPersonalFileBean();
//		personalFileBean.setUserId("1111");
//		personalFileBean.setPageSize(10);
//		personalFileBean.setPageNo(1);
//		personalFileBean.setFileType(FileType.DOCUMENT);
//		Pagination<PersonalFile> pagination=personalFileServiceI.queryPersonalFileByBean(personalFileBean);
//		System.out.println(pagination.getDatas());
//		for (PersonalFile iterable_element : pagination.getDatas()) {
//			System.out.println(iterable_element.getFileType());
//		}
//		System.out.println(pagination.getDatas().size());
//	}
//	@Test
//	@Ignore
//	public void testadd(){
//		try {
//			for (int i = 0; i <10; i++) {
//				PersonalFile personalFile=new PersonalFile();
//				personalFile.setFileType(FileType.FOLDER);
//				personalFile.setUserId("111");
//				personalFile.setGenerateName("111111");
//				personalFile.setCreateTime(new Date());
//				personalFileServiceI.createFolder(personalFile);	
//			}
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
