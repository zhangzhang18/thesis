package com.fancy.cms.service;

import java.util.List;

import com.fancy.cms.model.RcRole;

/**
 * 标题、简要说明. <br>
 * 类详细说明. 角色服务接口类
 */
public interface RcRoleService {

	public int deleteById(Integer id);

	public RcRole findById(Integer id);

	public List<RcRole> getList();

	public int save(RcRole bean, Integer[] menuIds);

	public int update(RcRole bean, Integer[] menuIds);

	public List<RcRole> getRoleList();

	public RcRole getNameById(Integer integer);

}
