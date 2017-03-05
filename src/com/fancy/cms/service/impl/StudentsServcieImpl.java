package com.fancy.cms.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.dao.StudentsMapper;
import com.fancy.cms.model.Students;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.service.StudentsService;

@Service
@Transactional
public class StudentsServcieImpl implements StudentsService {

	protected static final Log log = LogFactory
			.getLog(StudentsServcieImpl.class);

	@Autowired
	private StudentsMapper mapper;

	@Override
	public List<Students> selectlist() {
		return mapper.selectlist();
	}

	@Override
	public void save(Students Students) {
		mapper.insert(Students);

	}

	@Override
	public Students selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Students Students) {
		mapper.update(Students);
	}

	@Override
	public Integer checkName(String username) {
		return mapper.checkName(username);
	}

	@Override
	public Students selectByName(String username) {
		return mapper.selectByName(username);
	}

	@Override
	public List<Students> selectall() {
		// TODO Auto-generated method stub
		return mapper.selectall();
	}

	@Override
	public void insert(Students record) {
		// TODO Auto-generated method stub
		mapper.insert(record);
	}

	@Override
	public void deleteByPrimaryKey(Integer stuid) {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(stuid);
	}

	@Override
	public void setTidByThesis(Students student) {
		// TODO Auto-generated method stub
		mapper.setTidByThesis(student);
	}

	@Override
	public Students selectBystuId(Integer stuid) {
		return mapper.selectBystuId(stuid);
	}

	@Override
	public void updateTid(Students student) {
		mapper.updateTid(student);
	}

	@Override
	public List<Students> selectByTid(Integer tid) {
		return mapper.selectByTid(tid);
	}

	@Override
	public List<Students> selectBystuIdlist(Integer stuid) {
		return mapper.selectBystuIdlist(stuid);
	}

	@Override
	public List<Students> tselectall() {
		// TODO Auto-generated method stub
		return mapper.tselectall();
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize, String stuname) {
		int total = 0;
		Pagination pagination = null;
		List<Teachers> users = null;
		TeacherServiceimpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		users = this.mapper.getPage(begin, end, stuname);
		total = this.mapper.getCount(stuname);
		pagination = new Pagination(pageNo, pageSize, total, users);
		return pagination;
	}

	@Override
	public void fromXls(Students student) {
		mapper.fromXls( student);		
	}

	@Override
	public void fromXls2(List<Students> students) {
		mapper.fromXls2( students);
	}

}
