package com.fancy.cms.dao;

import com.fancy.cms.model.Teachers;
import com.fancy.cms.model.Thesis;
import com.fancy.cms.model.ThesisExample;
import com.fancy.cms.model.ThesisWithBLOBs;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ThesisMapper {
	int countByExample(ThesisExample example);

	int deleteByExample(ThesisExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(ThesisWithBLOBs record);

	int insertSelective(ThesisWithBLOBs record);

	List<ThesisWithBLOBs> selectByExampleWithBLOBs(ThesisExample example);

	List<Thesis> selectByExample(ThesisExample example);

	ThesisWithBLOBs selectByPrimaryKey(Integer cid);

	int updateByExampleSelective(@Param("record") ThesisWithBLOBs record,
			@Param("example") ThesisExample example);

	int updateByExampleWithBLOBs(@Param("record") ThesisWithBLOBs record,
			@Param("example") ThesisExample example);

	int updateByExample(@Param("record") Thesis record,
			@Param("example") ThesisExample example);

	int updateByPrimaryKeySelective(ThesisWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(ThesisWithBLOBs record);

	int updateByPrimaryKey(Thesis record);

	List<Thesis> selectall();

	void setflag(Thesis thesis);

	Thesis getThesisByCid(Integer cid);

	Thesis getthesisByTid(Integer tid);

	Thesis getthesisBySid(Integer stuid);

	int setflagByStuid(Integer stuid);

	List<Thesis> getthesisByTtid(Integer tid);

	List<Thesis> test(Integer tid);

	List<Thesis> selectByTitle(String ctitle);

	List<Teachers> getPage(@Param("begin") int begin, @Param("end") int end,
			@Param("ctitle") String ctitle);

	int getCount(@Param("ctitle") String ctitle);

	int insert(Thesis thesis);

	void fromXls(Thesis thesis);

	List<Teachers> getPagea(@Param("begin") int begin, @Param("end") int end,
			@Param("ctitle") String ctitle);

	int getCounta(@Param("ctitle") String ctitle);

	void fromXls2(List<Thesis> ts);
}