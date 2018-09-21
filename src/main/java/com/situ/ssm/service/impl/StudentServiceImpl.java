package com.situ.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssm.dao.StudentMapper;
import com.situ.ssm.entity.Student;
import com.situ.ssm.service.IStudentService;
import com.situ.ssm.vo.PageBean;
import com.situ.ssm.vo.StudentSearchCondition;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> jsonList() {
		return studentMapper.jsonList();
	}

	@Override
	public List<Student> list() {
		return studentMapper.list();
	}

	@Override
	public PageBean<Student> getPageBean(StudentSearchCondition condition) {
		PageBean<Student> pageBean = new PageBean<Student>();
		pageBean.setPageNo(condition.getPageNo());
		pageBean.setPageSize(condition.getPageSize());
		// 总记录数（是符合condition条件的总记录数）
		int totalCount = studentMapper.getTotalCount(condition);
		pageBean.setTotalCount(totalCount);
		// 一共有多少页
		int totalPage = (int) Math.ceil((double) totalCount / condition.getPageSize());
		pageBean.setTotalPage(totalPage);
		// 得到当前页的数据
		int offset = (condition.getPageNo() - 1) * condition.getPageSize();
		List<Student> list = studentMapper.findPageBeanListByCondition(condition, offset);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public boolean deleteById(Integer id) {
		int count = studentMapper.deleteById(id);
		return count == 1 ? true : false;
	}

	@Override
	public boolean addStudent(Student student) {
		int count = studentMapper.addStudent(student);
		return count == 1 ? true : false;
	}

	@Override
	public Student findById(Integer id) {
		return studentMapper.findById(id);
	}

	@Override
	public boolean updateStudent(Student student) {
		int count = studentMapper.updateStudent(student);
		return false;
	}


}
