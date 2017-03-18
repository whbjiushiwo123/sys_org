package com.whb.sys.org.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.dto.OrgTypeDto;
import com.whb.sys.org.model.Org;
import com.whb.sys.org.service.IOrgService;
import com.whb.sys.org.service.IOrgTypeService;
import com.whb.sys.org.service.IPersonService;

@Controller
@RequestMapping("/admin/org")
public class OrgController {
	private static Logger logger = LoggerFactory.getLogger(OrgController.class);
	@Inject
	private IOrgService orgService;
	@Inject
	private IOrgTypeService orgTypeServer;
	
	@Inject
	private IPersonService personService;
	@RequestMapping(value="/orgs",method=RequestMethod.GET)
	public String list(){
		return "/ftl/org/list";
	}
	/**
	 * 初始化管理类型
	 * @return
	 */
	private Map<Integer,String> initManager(){
		Map<Integer,String> manager = new HashMap<Integer,String>();
		manager.put(0, "默认类型");
		manager.put(1, "所有类型");
		manager.put(2, "自定义类型");
		manager.put(-1, "不具备管理类型");
		return manager;
	}
	
	@ResponseBody  
	@RequestMapping(value="/treeAll",method=RequestMethod.POST)
	public String treeAll(){
		List<TreeDto> ltd = orgService.tree();
		JSONObject json = new JSONObject();
		json.put("orgTree", ltd);
		return json.toString();
	}
	
	@RequestMapping(value="/orgs/{id}",method=RequestMethod.GET)
	public String listChilds(@PathVariable int id,Integer typeId,Model model) throws Exception{
		try {
			logger.error("列出所有子组织机构,组织机构ID为id:{}",id);
			Org org=orgService.get(id);
			model.addAttribute("parent",org);
			model.addAttribute("childs",orgService.findByParent(id,typeId));
			model.addAttribute("orgTypes", orgTypeServer.listChileType(org.getTypeId()));
			return "/ftl/org/listChilds";
		} catch (Exception e) {
			logger.error("列出所有子组织机构出错",e.getMessage());
			throw e;
		}
	}
	@RequestMapping(value="/orgs/{pid}/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int pid,@PathVariable Integer id,Model model) throws Exception{
		try {
			logger.error("根据父ID列出子组织机构：pid:{},id:{}",pid,id);
			model.addAttribute("parent",orgService.get(pid));
			model.addAttribute("org",orgService.get(id));
			model.addAttribute("person",personService.listPersonAndPosByOrg(id, null) );
			return "/ftl/org/listChilds";
		} catch (Exception e) {
			logger.error("根据父ID列出子组织机构出错",e.getMessage());
			throw e;
		}
	}
	/*
	@RequestMapping(value="/orgs/{pid}/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int pid,@PathVariable Integer id,Model model) throws SysException{
		try {
			orgService.delete(id);
			return "forward:/admin/org/orgs/+{"+id+"}";
		} catch (Exception e) {
			logger.error("删除组织机构出错id:{}",id);
			logger.error("删除组织机构出错",e.getMessage());
			throw e;
		}
	}*/
	
	@RequestMapping(value = "/orgs/{id}/add",method = RequestMethod.GET)
	public String add(@PathVariable int id,Model model){
		model.addAttribute("org",new Org());
		Org parent = orgService.get(id);
		model.addAttribute("parent",parent);
		List<OrgTypeDto> orgTypes = orgTypeServer.listChileType(parent.getTypeId());
		model.addAttribute("orgTypes",orgTypes);
		model.addAttribute("managerTypes",initManager());
		return "/ftl/org/add";
		
	}

	@RequestMapping(value = "/orgs/{id}/add",method = RequestMethod.POST)
	public String add(@PathVariable int id,@RequestParam Map body,@Valid @ModelAttribute("org") Org org,BindingResult br ,Integer pid){
		if(br.hasErrors()){
			return "org/add";
		}
		orgService.add(org,pid);
		return "redirect:/admin/org/orgs/"+id;
	}
	/**
	 * 根据组织机构ID删除组织机构
	 * @param pid 要删除组织的父ID
	 * @param id 要删除的组织结构ID
	 * @return
	 */
	@RequestMapping(value = "/orgs/{pid}/delete/{id}",method = RequestMethod.GET)
	public String  delete(@PathVariable int pid,@PathVariable int id){
		orgService.delete(id);
		return "redirect:/admin/org/orgs/"+pid;
	}
	
	/**
	 * 更新组织机构
	 * @param pid
	 * @param id
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/orgs/{pid}/update/{id}",method = RequestMethod.GET)
	public String update(@PathVariable int pid,@PathVariable int id ,Model model){
		Org org = orgService.get(id);
		Org parent = orgService.get(pid);
		model.addAttribute("org",org);
		model.addAttribute("orgTypes",orgTypeServer.listChileType(pid));
		model.addAttribute("parent",parent);
		return "/ftl/org/update";
	}
	
	@RequestMapping(value = "orgs/{pid}/update/{id}",method = RequestMethod.POST)
	public String update(@PathVariable int pid,@PathVariable int id,@Valid  @ModelAttribute("org") Org org,BindingResult br,Model model ){
		if(br.hasErrors()){
			return "org/update";
		}
		try {
			orgService.update(org,id,pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/org/orgs/"+pid;
	}
	
/*	@RequestMapping(value="/orgs/{pid}/{id}",method = RequestMethod.GET)
	public String show(@PathVariable int pid,@PathVariable int id,Model model){
		model.addAttribute("parent",orgService.load(pid));
		model.addAttribute("parent",orgService.load(id));
		return "org/show";
	}*/
	
	/**
	 * 根据部门ID 设置部门的组织
	 * @return
	 */
	@RequestMapping(value="/setRule/{id}")
	public String setRule(@PathVariable int id,Model model){
		model.addAttribute("id",id);
		//model.addAttribute("org",orgService.get(id));
		//model.addAttribute("mids",orgService.listManagerRules(id));
		return "/ftl/org/setRule";
	}
}
