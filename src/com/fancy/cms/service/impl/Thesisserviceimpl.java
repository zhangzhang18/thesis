package com.fancy.cms.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.dao.ThesisMapper;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.model.Thesis;
import com.fancy.cms.service.Thesisservice;

@Service
@Transactional
public class Thesisserviceimpl implements Thesisservice {

	protected static final Log log = LogFactory.getLog(Thesisserviceimpl.class);
	@Autowired
	private ThesisMapper thesisMapper;

	@Override
	public List<Thesis> selectall() {
		return thesisMapper.selectall();
	}

	@Override
	public void setflag(Thesis thesis) {
		thesisMapper.setflag(thesis);
	}

	@Override
	public Thesis getThesisByCid(Integer cid) {
		return thesisMapper.getThesisByCid(cid);
	}

	@Override
	public Thesis getthesisByTid(Integer tid) {
		return thesisMapper.getthesisByTid(tid);
	}

	@Override
	public Thesis getthesisBySid(Integer stuid) {
		return thesisMapper.getthesisBySid(stuid);

	}

	@Override
	public int setflagByStuid(Integer stuid) {
		return thesisMapper.setflagByStuid(stuid);
	}

	@Override
	public List<Thesis> getthesisByTtid(Integer tid) {
		return thesisMapper.getthesisByTtid(tid);
	}

	@Override
	public List<Thesis> test(Integer tid) {
		return thesisMapper.test(tid);
	}

	@Override
	public List<Thesis> selectByTitle(String ctitle) {
		return thesisMapper.selectByTitle(ctitle);
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize, String ctitle) {
		int total = 0;
		Pagination pagination = null;
		List<Teachers> users = null;
		Thesisserviceimpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
				+ pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		users = this.thesisMapper.getPage(begin, end, ctitle);
		total = this.thesisMapper.getCount(ctitle);
		pagination = new Pagination(pageNo, pageSize, total, users);
		return pagination;
	}

	@Override
	public Pagination getPagea(int pageNo, int pageSize, String ctitle) {
		int total = 0;
		Pagination pagination = null;
		List<Teachers> users = null;
		Thesisserviceimpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
				+ pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		users = this.thesisMapper.getPagea(begin, end, ctitle);
		total = this.thesisMapper.getCounta(ctitle);
		pagination = new Pagination(pageNo, pageSize, total, users);
		return pagination;
	}

	@Override
	public int insert(Thesis thesis) {
		return thesisMapper.insert(thesis);
	}

	@Override
	public int updateByPrimaryKey(Thesis thesis) {
		return thesisMapper.updateByPrimaryKey(thesis);
	}

	@Override
	public void fromXls(Thesis thesis) {
		thesisMapper.fromXls(thesis);
	}

	@Override
	public void fromXls2(List<Thesis> ts) {
		thesisMapper.fromXls2(ts) ;			
	}

}
