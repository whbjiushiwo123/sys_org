package com.whb.sys.org.web.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whb.sys.org.model.Position;
import com.whb.sys.org.service.IPositionService;


@Controller
@RequestMapping(value="/admin/position")
public class PositionController {
	@Inject
	private IPositionService iPositionService;
	
	@RequestMapping(value = "/positions")
	public String list(Model model){
		model.addAttribute("datas",iPositionService.find());
		return "/ftl/position/list";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("position",new Position());
		return "/ftl/position/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("position")Position position,BindingResult br,Model model){
		if(br.hasFieldErrors()){
			return "/ftl/position/add";
		}
		iPositionService.add(position);
		return "redirect:/admin/position/positions";
	}
	
	@RequestMapping(value = "/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		model.addAttribute("position",iPositionService.get(id));
		return "/ftl/position/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid @ModelAttribute("position")Position position,BindingResult br,Model model){
		if(br.hasFieldErrors()){
			return "/ftl/position/update";
		}
		Position tp = new Position();
		tp.setId(id);
		tp.setManage(position.getManage());
		tp.setName(position.getName());
		tp.setSn(position.getSn());
		iPositionService.update(tp);
		return "redirect:/admin/position/positions";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		iPositionService.delete(id);
		return "redirect:/admin/position/positions";
	}
}
