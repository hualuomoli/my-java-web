package com.github.hualuomoli.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.exception.VersionConflictException;
import com.github.hualuomoli.demo.entity.Demo;
import com.github.hualuomoli.demo.service.IDemoService;

/**
 * test controller
 * @author hualuomoli
 *
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

	@Autowired
	private IDemoService demoService;

	// list
	@RequestMapping(value = "list")
	public String list(Demo demo, Model model, HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		Page page = null;
		if (StringUtils.isEmpty(pageNo)) {
			page = new Page(1, 7);
		} else {
			page = new Page(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		}
		page = demoService.findPage(demo, page);
		model.addAttribute("page", page);
		return "demo/list";
	}

	// form
	@RequestMapping(value = "form")
	public String form(Demo demo, Model model, HttpServletRequest request, HttpServletResponse response) {
		demo = demoService.get(demo);
		if (demo == null) {
			demo = new Demo();
		}
		model.addAttribute("demo", demo);
		return "demo/form";
	}

	// insert
	@RequestMapping(value = "insert")
	@ResponseBody
	public String insert(Demo demo, Model model, HttpServletRequest request, HttpServletResponse response) {
		demoService.insert(demo);
		return "insert ok";
	}

	// update
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Demo demo, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			demoService.update(demo);
		} catch (VersionConflictException e) {
			return e.getMessage();
		}
		return "update ok";
	}

	// delete
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Demo demo, Model model, HttpServletRequest request, HttpServletResponse response) {
		demoService.delete(demo);
		return "delete ok";
	}

}
