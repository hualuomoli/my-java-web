package com.github.hualuomoli.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hualuomoli.demo.entity.Demo;

/**
 * test controller
 * @author hualuomoli
 *
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

	@RequestMapping(value = "list")
	public String list(Demo demo, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "demo/demo";
	}

}
