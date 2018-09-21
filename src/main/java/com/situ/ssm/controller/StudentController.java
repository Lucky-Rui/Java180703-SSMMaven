package com.situ.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.ssm.entity.Banji;
import com.situ.ssm.entity.Student;
import com.situ.ssm.service.IBanjiService;
import com.situ.ssm.service.IStudentService;
import com.situ.ssm.vo.PageBean;
import com.situ.ssm.vo.StudentSearchCondition;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IBanjiService banjiService;

	@RequestMapping(value = "/jsonList")
	@ResponseBody
	public List<Student> jsonList() {
		return studentService.jsonList();
	}

	@RequestMapping(value = "/list")
	public String list(Integer pageNo, Integer pageSize, Model model) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		List<Student> list = studentService.list();
		model.addAttribute("list", list);
		return "/student/student_list";
	}

	@RequestMapping(value = "/pageList")
	public String pageList(Integer pageNo, Integer pageSize, String name, Integer age, String gender, Model model) {
		if (pageNo == null || pageNo.equals("")) {
			pageNo = 1;
		}
		if (pageSize == null || pageSize.equals("")) {
			pageSize = 3;
		}
		StudentSearchCondition condition = new StudentSearchCondition(pageNo, pageSize, name, age, gender);
		PageBean<Student> pageBean = studentService.getPageBean(condition);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("condition", condition);
		return "/student/student_list";
	}

	@RequestMapping(value = "/deleteById")
	public String deleteById(Integer id, Integer pageNo) {
		boolean result = studentService.deleteById(id);
		return "redirect:/student/pageList.action?pageNo=" + pageNo;
	}

	@RequestMapping(value = "/getAddPage")
	public String getAddPage(Model model) {
		List<Banji> list = banjiService.list();
		model.addAttribute("list", list);
		return "/student/student_add";
	}

	@RequestMapping(value = "/addStudent")
	@ResponseBody
	public boolean addBanji(Student student) {
		return studentService.addStudent(student);
	}
	
	@RequestMapping(value="/getUpdatePage")
	public String getUpdatePage(Integer id,Model model) {
		Student student = studentService.findById(id);
		List<Banji> list = banjiService.list();
		model.addAttribute("list", list);
		model.addAttribute("student", student);
		return "/student/student_update";
	}
	
	@RequestMapping(value="/updateStudent")
	@ResponseBody
	public boolean updateStudent(Student student) {
		return studentService.updateStudent(student);
	}
}
