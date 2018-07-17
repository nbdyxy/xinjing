package cn.gzggzy.yyh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {
	
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/dongtai")
	public String dongtai() {
		return "dongtai";
	}
	
	@GetMapping("/huixiang")
	public String huixiang() {
		return "huixiang";
	}
	
	@GetMapping("/gongxiu")
	public String gongxiu() {
		return "gongxiu";
	}
	
	@GetMapping("/gongxiu_personally")
	public String gongxiu_personally() {
		return "gongxiu_personally";
	}
	
}
