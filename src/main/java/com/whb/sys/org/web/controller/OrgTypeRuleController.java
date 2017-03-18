package com.whb.sys.org.web.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.whb.sys.org.dto.OrgTypeRuleDto;
import com.whb.sys.org.model.OrgType;
import com.whb.sys.org.model.SysException;
import com.whb.sys.org.service.IOrgTypeRuleService;
import com.whb.sys.org.service.IOrgTypeService;

@Controller
@RequestMapping("/admin/orgTypeRule")
public class OrgTypeRuleController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	@Qualifier("orgTypeRuleService")
	private IOrgTypeRuleService orgTypeRuleService;
	@Autowired
	@Qualifier("orgTypeService")
	private IOrgTypeService orgTypeService;
	
	
	@RequestMapping(value="/setRule/{id}",method=RequestMethod.GET)
	public String setRule(@PathVariable int id,Model model) throws SysException{
		OrgType ot = orgTypeService.getById(id);
		List<OrgTypeRuleDto> orgTypeRules = orgTypeRuleService.listOrgTypeRuleByOrg(id);
		model.addAttribute("orgTypeRules", orgTypeRules);
		model.addAttribute("ot", ot);
		return "ftl/orgType/setRule";
	}
	
    @ResponseBody
	@RequestMapping(value="/updateRule",method=RequestMethod.POST)
	public String updateRule(@RequestParam Map<String, Object> body) throws Exception{
		JSONObject json = new JSONObject();
    	try {
			orgTypeRuleService.updateOrgTypeRule(body);
			boolean success = true;
			int status  = 100;
			String url=null;
	    	logger.info("更新组织结构结果:{}",json.toString());
			json.put("msg", "已经更新组织机构");
			json.put("success", success);
			json.put("status", status);
			json.put("url", url);
		} catch (Exception e) {
	    	logger.info("更新组织结构失败:{}",body.size());
	    	logger.info("更新组织结构失败:{}",e);
	    	throw e;
		}
		return json.toString();
	}
	
    @RequestMapping(value="/deleteRule/{pid}/{cid}")
    public String deleteRule(@PathVariable int pid,@PathVariable int cid) throws Exception{
    	try {
        	orgTypeRuleService.deleteOrgTypeRule(pid, cid);
	    	logger.info("删除组织机构成功，pid:{}，cid:{}",pid,cid);
		} catch (Exception e) {
	    	logger.info("删除组织机构失败，pid:{}，cid:{}",pid,cid);
	    	logger.info("删除组织机构失败，e:{}",e);
	    	throw e;
		}
    	return "redirect:/admin/orgTypeRule/setRule/"+pid;
    }
    @ResponseBody
    @RequestMapping(value="/addRule",method=RequestMethod.POST)
    public String addRule(@RequestParam Map<String, Object> body) throws Exception{
    	JSONObject json = new JSONObject();
    	try {
			orgTypeRuleService.addOrgTypeRule(body);
			boolean success = true;
			int status  = 100;
			String url=null;
	    	logger.info("添加组织结构结果:{}",json.toString());
			json.put("success", success);
			json.put("status", status);
			json.put("msg", "已经添加组织机构");
			json.put("url", url);
		} catch (Exception e) {
	    	logger.info("添加组织结构失败:{}",body.size());
	    	logger.info("添加组织结构失败:{}",e);
	    	throw e;
		}
		return json.toString();
    }
}
