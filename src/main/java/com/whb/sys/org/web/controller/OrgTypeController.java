package com.whb.sys.org.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.whb.sys.org.model.OrgType;
import com.whb.sys.org.model.SysException;
import com.whb.sys.org.service.IOrgTypeService;


@Controller
@RequestMapping("/admin/orgType")
public class OrgTypeController {
	private final static Logger logger=LoggerFactory.getLogger(OrgTypeController.class);
	
	@Autowired
	@Qualifier("orgTypeService")
	private IOrgTypeService orgTypeService;
	
	@RequestMapping(value="/orgTypes",method=RequestMethod.GET)
	public String list(Model model) throws SysException{
		model.addAttribute("orgTypes",orgTypeService.list());
		return "ftl/orgType/list";
	}
	
	@RequestMapping(value="/orgTypes",method=RequestMethod.POST)
	public String listByPost(Model model) throws SysException{
		model.addAttribute("orgTypes",orgTypeService.list());
		return "ftl/orgType/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) throws SysException{
		try{
			model.addAttribute("ot",new OrgType());
		}catch (Exception e) {
			logger.error("跳转添加组织机构页面出错：{}",e);
			throw new SysException("跳转添加组织机构页面出错");
		}
		return "ftl/orgType/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(OrgType orgType) throws SysException{
		try {
			orgTypeService.add(orgType);
		} catch (Exception e) {
			logger.error("完成添加组织机构出错：{}",e);
			throw new SysException("完成添加组织机构出错");
		}
		return "forward:/admin/orgType/orgTypes";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) throws SysException{
		try {
			OrgType ot = orgTypeService.getById(id);
			model.addAttribute("ot",ot);
		}catch (Exception e) {
			logger.error("登录更新页面出错了：{}",e);
			throw new SysException("登录更新页面出错了");
		}
		return "ftl/orgType/update";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id ,@RequestParam Map<String, Object> body) throws SysException{
		OrgType ort = new OrgType();
		ort = orgTypeService.getById(id);
		ort.setName((String)body.get("name"));
		ort.setSn((String)body.get("sn"));
		orgTypeService.update(ort);
		return "forward:/admin/orgType/orgTypes";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) throws SysException{
		try{
			logger.error("删除组织机构,id：{}",id);
			orgTypeService.delete(id);
		}catch (Exception e) {
			logger.error("完成删除组织机构出错：{}",e);
		}
		return "forward:/admin/orgType/orgTypes";
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model){
		model.addAttribute("ot", orgTypeService.getById(id));
		model.addAttribute("childs",orgTypeService.listChileType(id));
		return "ftl/orgType/show";
	}
}
