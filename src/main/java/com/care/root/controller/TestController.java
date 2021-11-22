package com.care.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.TestServiceImpl;

@Controller
public class TestController {
	
	@Autowired TestServiceImpl ts;
	
	@GetMapping("buy_form")
	public String buy_form() {
		System.out.println("buy_form 컨트롤러 실행");
		return "buy_form";
	}
	
	@RequestMapping("buy")
	public String buy(Model model, @RequestParam(required=false, defaultValue="0") int num) {
		System.out.println("buy 서비스 실행");
		ts.buy(model,num);
		return "result";
	}
	
	@GetMapping("db_result")
	public String dbResult(Model model) {
		System.out.println("dbResult 서비스 실행");
		ts.dbResult(model);
		return "db_result";
	}
	
	@GetMapping("testChart")
	public String testChart() {
		return "testChart";
	}
}
