package com.situ.ssm.service;

import java.util.List;

import com.situ.ssm.entity.Banji;
import com.situ.ssm.vo.PageBean;

public interface IBanjiService {

	PageBean<Banji> getPageBean(Integer pageNo, Integer pageSize);

	boolean deleteById(Integer id);

	boolean checkName(String name);

	boolean addBanji(Banji banji);

	List<Banji> list();

}
