package com.inred.media.controller.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inred.media.controller.SchoolContoller;
import com.inred.media.model.Pagination;
import com.inred.media.pojo.Grade;
import com.inred.media.pojo.IdEntity;
import com.inred.media.pojo.User;
import com.inred.media.service.impl.BaseServiceImpl;
import com.inred.media.util.Constant;
/**
 * 
 * @author mac
 *
 * @param <T> :子action.
 * @param <M> :子action 返回的路径
 */
public class BaseController <T extends IdEntity> {
	private static final Logger log = LoggerFactory.getLogger(SchoolContoller.class);
	@Autowired
	BaseServiceImpl<T> baseServiceImpl;
	
	private Class<T> classes;//
	private String filePath;//跳转页面的地址
	private String actionpackage;//当前Action的包的路径
	
	private int superpageSize;
	
	
	/**
	 * 
	 * @param classes    当前Action对应的Pojo类
	 * @param filePath   跳转的jsp路径
	 * @param actionpackage 重定向的路径
	 */
	public void updateClasses(Class<T> classes,String filePath,String actionpackage){
		this.classes=classes;
		this.filePath=filePath;
		this.actionpackage=actionpackage;
	}
	public void updateClasses(Class<T> classes,String filePath,String actionpackage,int superpageSize){
		this.classes=classes;
		this.filePath=filePath;
		this.actionpackage=actionpackage;
		this.superpageSize=superpageSize;
	}

	/**
	 * 通用的 List信息
	 * @param 
	 */
	@RequestMapping("/list")
	public ModelAndView findlist(
			@RequestParam(value = "pid", defaultValue = "0") String pid,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(filePath+"/list");
		// 分页查询数据
		if(this.superpageSize>0){
			pageSize=superpageSize;
		}
//		User user=(User) session.getAttribute(Constant.LOGIN_USR_AUTH);//get session
		Pagination<T> pagination = baseServiceImpl.findPaginationByQuery(new Query(), pageNo, pageSize, classes);
		System.out.println(pagination.getDatas().size());
		if(pagination==null)
			pagination = new Pagination<T>();
		modelAndView.addObject("pageList", pagination);

		return modelAndView;
	}
	
	
	/**
	 * 删除与批量删除
	 * 
	 * @param id
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(
			@RequestParam(value = "id", defaultValue = "0") String id,
			@RequestParam(value = "ids", defaultValue = "0") String ids) {
		ModelAndView modelAndView = new ModelAndView();
		if(actionpackage.isEmpty())
			modelAndView.setViewName("redirect:/list");
		else
			modelAndView.setViewName("redirect:/"+actionpackage+"/list");
		try {
			if (!id.isEmpty() && !id.equals("0")) {// 删除
				log.info("ID删除");
				T t = baseServiceImpl.findOneById(id, classes);
				baseServiceImpl.remove(t);

			} else if (!ids.isEmpty() && !ids.equals("0")) {
				// 批量删除
				log.info("批量删除");
				String[] idsStr = ids.split(",");
				for (String galleryIds : idsStr) {
					T t = baseServiceImpl.findOneById(galleryIds, classes);
					baseServiceImpl.remove(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 跳转到(添加与更新)页面
	 * 
	 * @return
	 */
	@RequestMapping("/updateOrCreate")
	public ModelAndView updateOrCreate(
			@RequestParam(value = "id", defaultValue = "0") String id,
			HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(filePath+"/editor");
		T t = null;
		try {
			if (!id.equals("0")) {// 修改

				log.info("id:" + id);
				t = baseServiceImpl.findOneById(id, classes);

				
			} else {// 新增

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}// 以ID查找当前用户信息
		modelAndView.addObject("editorInfo", t);

		return modelAndView;

	}

	/**
	 * 添加与更新
	 * 
	 
	 * @return
	 */
	@RequestMapping(value = "/createOrUpdateToFind", method = RequestMethod.POST)
	public ModelAndView createOrUpdateToFind(T gradeInfo) {
		ModelAndView modelAndView = new ModelAndView();
		if(actionpackage.isEmpty())
			modelAndView.setViewName("redirect:/list");
		else
			modelAndView.setViewName("redirect:/"+actionpackage+"/list");
		
		T ts = (T)gradeInfo;
		try {
			
			if (ts.getId() == null) {// 添加信息
				
				baseServiceImpl.insert(ts);//添加记录
			} else {// 更新信息
				T updateInfo = baseServiceImpl.findOneById(ts.getId(), classes);// 查找到当前记录.
					updateInfo=ts;
				baseServiceImpl.save(updateInfo);// 更新数据信息
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	/**
	 * 添加Query条件。参数支持传无限个，前者为：key,后者为：value
	 * 示例数据：craeteQueryWhere("username","jack","password","123456")
	 * @param keyValue
	 * @return
	 */
	public Query craeteQueryWhere(String ... keyValue){
		Query query =new Query();
		if(keyValue!=null){
			for(int i=0;i<keyValue.length;i++){
				query.addCriteria(Criteria.where(keyValue[i]).is(keyValue[i+1]));
				i++;
			}
		}
		log.info("query where :"+query.toString());
		return query;
		
	}
	

}
