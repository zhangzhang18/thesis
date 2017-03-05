package com.fancy.cms.dao;

import com.fancy.cms.model.Students;
import com.fancy.cms.model.StudentsExample;
import com.fancy.cms.model.Teachers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentsMapper {
	int countByExample(StudentsExample example);

	int deleteByExample(StudentsExample example);

	int deleteByPrimaryKey(Integer stuid);

	int insert(Students record);

	int insertSelective(Students record);

	List<Students> selectByExample(StudentsExample example);

	Students selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Students record,
			@Param("example") StudentsExample example);

	int updateByExample(@Param("record") Students record,
			@Param("example") StudentsExample example);

	int updateByPrimaryKeySelective(Students record);

	int updateByPrimaryKey(Students record);

	List<Students> selectlist();

	Integer checkName(String username);

	Students selectByName(String username);

	Students selectBystuId(Integer stuid);

	List<Students> selectall();

	void add(String stuname, Integer stuid, String stupwd, String stusex,
			Integer stuclass, Integer tid);

	void update(Students students);

	void setTidByThesis(Students student);

	void updateTid(Students student);

	List<Students> selectByTid(Integer tid);

	List<Students> selectBystuIdlist(Integer stuid);

	List<Students> tselectall();

	List<Teachers> getPage(@Param("begin") int begin, @Param("end") int end,
			@Param("stuname") String stuname);

	int getCount(@Param("stuname") String stuname);

	void fromXls(Students student);

	void fromXls2(List<Students> students);

}