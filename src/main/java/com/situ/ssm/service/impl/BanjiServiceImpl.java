package com.situ.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssm.dao.BanjiMapper;
import com.situ.ssm.entity.Banji;
import com.situ.ssm.service.IBanjiService;
import com.situ.ssm.vo.PageBean;

@Service
public class BanjiServiceImpl implements IBanjiService {
	
	@Autowired
	private BanjiMapper banjiMapper;

	@Override
	public PageBean<Banji> getPageBean(Integer pageNo, Integer pageSize) {
		PageBean<Banji> pageBean = new PageBean<>();
		// 当前是第几页
		pageBean.setPageNo(pageNo);
		// 一页有多少条数据
		pageBean.setPageSize(pageSize);
		// 总记录数
		int totalCount = banjiMapper.getTotalCount();
		pageBean.setTotalCount(totalCount);
		// 一共有多少页,向上取整
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		// 当前页的数据
		int offset = (pageNo - 1) * pageSize;
		List<Banji> list = banjiMapper.pageList(offset, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean deleteById(Integer id) {
		int count = banjiMapper.deleteById(id);
		return count == 1 ? true : false;
	}

	@Override
	public boolean checkName(String name) {
		int count = banjiMapper.findCountByName(name);
		return count > 0 ? true : false;
	}

	@Override
	public boolean addBanji(Banji banji) {
		int count = banjiMapper.addBanji(banji);
		return count == 1 ? true : false;
	}

	@Override
	public List<Banji> list() {
		return banjiMapper.list();
	}

}
