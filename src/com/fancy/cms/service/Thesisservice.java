package com.fancy.cms.service;

import java.util.List;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.model.Thesis;

public interface Thesisservice {

	List<Thesis> selectall();

	void setflag(Thesis thesis);

	Thesis getThesisByCid(Integer cid);

	Thesis getthesisByTid(Integer tid);

	Thesis getthesisBySid(Integer stuid);

	int setflagByStuid(Integer stuid);

	List<Thesis> getthesisByTtid(Integer tid);

	List<Thesis> test(Integer tid);

	List<Thesis> selectByTitle(String ctitle);

	Pagination getPage(int cpn, int pageSize, String ctitle);

	 int  insert(Thesis thesis);

	int updateByPrimaryKey(Thesis thesis);

	void fromXls(Thesis thesis);

	Pagination getPagea(int cpn, int pageSize, String ctitle);

	void fromXls2(List<Thesis> ts);

	
}
