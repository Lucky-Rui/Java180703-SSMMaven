package com.situ.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.ssm.entity.Student;
import com.situ.ssm.vo.StudentSearchCondition;

public interface StudentMapper {

	List<Student> jsonList();

	List<Student> list();

	int getTotalCount(StudentSearchCondition condition);

	List<Student> findPageBeanListByCondition(@Param("condition") StudentSearchCondition condition,
			@Param("offset") Integer offset);

	int deleteById(Integer id);

	int addStudent(Student student);

	Student findById(Integer id);

	int updateStudent(Student student);

}
