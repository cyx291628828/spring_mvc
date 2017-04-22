package com.source.plan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexContorller {
	/**
	 * 进入主页的Contorller
	 * @return
	 */
	@RequestMapping(value="/hello")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/")
	public String index1(){
		return "index";
	}
	
	@RequestMapping(value="/welcome")
	public String index2(RedirectAttributes att){
		att.addFlashAttribute("yuu", "123");
		return "redirect:/index";
	}
	@RequestMapping(value="/index")
	public String index3(@ModelAttribute("yuu") String username){
		System.out.println(username);
		return "index";
	}
	
	
	
	
	@RequestMapping(value="/ok")
	public String ok(){
		System.out.println("123");
		return "ok";
	}
	//2
	@RequestMapping(value="/ok1")
	public String ok1(@RequestParam int id){
		System.out.println(id);
		return "ok";
	}
	//3
	@RequestMapping(value="/ok2")
	public String ok2(int id,int age){
		System.out.println(id);
		System.out.println(age);
		return "ok";
	}


	@RequestMapping(value="/ok3")
	public String ok3(Map<String, Object> map){
		map.put("hello", "world");
		return "ok";
	}


	@RequestMapping(value="/ok4")
	public String ok4(Model model){
		model.addAttribute("hello","world");
		model.addAttribute("world");//keyΪvalue������
		return "ok";
	}
	
	
	//��ȡreq,resp
	@RequestMapping(value="req")
	public String req(HttpServletRequest req,HttpServletResponse resp){
		System.out.println(req.getParameter("id"));
		return "ok";
	}
}
