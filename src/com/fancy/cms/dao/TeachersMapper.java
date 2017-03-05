package com.fancy.cms.dao;

import com.fancy.cms.model.Teachers;
import com.fancy.cms.model.TeachersExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TeachersMapper {
	int countByExample(TeachersExample example);

	int deleteByExample(TeachersExample example);

	int deleteByPrimaryKey(Integer tid);

	int insert(Teachers record);

	int insertSelective(Teachers record);

	List<Teachers> selectByExample(TeachersExample example);

	Teachers selectByPrimaryKey(Integer tid);

	int updateByExampleSelective(@Param("record") Teachers record,
			@Param("example") TeachersExample example);

	int updateByExample(@Param("record") Teachers record,
			@Param("example") TeachersExample example);

	int updateByPrimaryKeySelective(Teachers record);

	int updateByPrimaryKey(Teachers record);

	Teachers getTeacherByCTid(Integer tid);

	void updateSum(Teachers teacher);

	void updatesubsum(Teachers teacher);

	Teachers getTeacherByName(String username);

	List<Teachers> selectall();

	List<Teachers> getPage(@Param("begin") int begin, @Param("end") int end,
			@Param("teaname") String teaname);

	int getCount(@Param("teaname") String teaname);

	void fromXls(Teachers teacher);

	void fromXls2(List<Teachers> teachers);

}