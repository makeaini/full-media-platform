package com.inred.media.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inred.media.controller.base.BaseController;
import com.inred.media.pojo.ResourcesType;
import com.inred.media.service.ResourcesTypeServiceI;

@Controller
@RequestMapping("/resourcesType")
public class ResourcesTypeContoller extends BaseController<ResourcesType> {
	private static final Logger log = LoggerFactory
			.getLogger(ResourcesTypeContoller.class);
	@Autowired
	private ResourcesTypeServiceI resourcesTypeServiceI;
	
	
	//毕需重构父类中的 （updateClasses）信息
	ResourcesTypeContoller(){
		super.updateClasses(ResourcesType.class,"admin/resourcesType","resourcesType",100);
	}
	
	
}
