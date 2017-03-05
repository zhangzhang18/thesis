package com.fancy.cms.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.dao.TeachersMapper;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.service.TeacherService;

@Service
@Transactional
public class TeacherServiceimpl implements TeacherService {
	protected static final Log log = LogFactory.getLog(TeacherServiceimpl.class);
	@Autowired
	private TeachersMapper teachersMapper;

	
	@Override
	public Teachers getTeacherByCTid(Integer tid) {
		return teachersMapper.getTeacherByCTid(tid);
	}

	@Override
	public void updateSum(Teachers teacher) {
		teachersMapper.updateSum(teacher);
	}

	@Override
	public void updatesubsum(Teachers teacher) {
		teachersMapper.updatesubsum(teacher);
	}

	@Override
	public Teachers getTeacherByName(String username) {
		return teachersMapper.getTeacherByName(username);
	}

	@Override
	public List<Teachers> selectall() {
		return teachersMapper.selectall();
	}

	@Override
	public void updateByPrimaryKey(Teachers teacher) {
		teachersMapper. updateByPrimaryKey( teacher);		
	}
	
	@Override
	public Pagination getPage(int pageNo, int pageSize, String districtName) {
		int total = 0;
		Pagination pagination = null;
		List<Teachers> users = null;
		TeacherServiceimpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		users = this.teachersMapper.getPage(begin, end, districtName);
		total = this.teachersMapper.getCount(districtName);
		pagination = new Pagination(pageNo, pageSize, total, users);
		return pagination;
	}

	@Override
	public void fromXls(Teachers teacher) {
		teachersMapper.fromXls(teacher);		
	}

	@Override
	public void fromXls2(List<Teachers> teachers) {
		teachersMapper.fromXls2(teachers);		
	}

}
