package com.fancy.cms.service;

import java.util.List;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.model.Teachers;

public interface TeacherService {

	Teachers getTeacherByCTid(Integer tid);

	void updateSum(Teachers teacher);

	void updatesubsum(Teachers teacher);

	Teachers getTeacherByName(String username);

	List<Teachers> selectall();

	void updateByPrimaryKey(Teachers teacher);

	Pagination getPage(int cpn, int pageSize, String teaname);

	void fromXls(Teachers teacher);

	void fromXls2(List<Teachers> teachers);


}
