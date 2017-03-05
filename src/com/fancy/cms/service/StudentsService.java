package com.fancy.cms.service;

import java.util.List;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.model.Students;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 */
public interface StudentsService {

	public List<Students> selectlist();

	public void save(Students students);

	public Students selectById(Integer id);

	public void update(Students students);

	public Integer checkName(String username);

	public Students selectByName(String username);
	public Students selectBystuId(Integer stuid);

	public List<Students> selectall();

	public void insert(Students record);

	public void deleteByPrimaryKey(Integer stuid);

	public void setTidByThesis(Students student);

	public void updateTid(Students student);

	public List<Students> selectByTid(Integer tid);

	public List<Students> selectBystuIdlist(Integer stuid);

	public List<Students> tselectall();

	public Pagination getPage(int cpn, int pageSize, String stuname);

	public void fromXls(Students student);

	public void fromXls2(List<Students> students);




}
