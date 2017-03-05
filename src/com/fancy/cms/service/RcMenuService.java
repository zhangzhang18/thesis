package com.fancy.cms.service;

import java.util.List;

import com.fancy.cms.model.RcMenu;
import com.fancy.cms.vo.MenuVo;
import com.fancy.cms.vo.RoleMenuVo;

/**
 * 标题、简要说明. <br>
 * 类详细说明. 菜单资源接口类
 */
public interface RcMenuService {

	/**
	 * @DESC 根据ID删除菜单
	 * @param id
	 * @throws Exception
	 */
	public int deleteById(Integer id);

	/**
	 * @DESC 根据ID查询菜单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RcMenu findById(Integer id);

	/**
	 * @DESC 根据角色ID查询用户菜单权限
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RoleMenuVo> findListMenuByID(int roleId);

	/**
	 * @DESC 查询层级菜单
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> findListMenuVo();

	public List<RcMenu> getList(int parentId);

	/**
	 * @DESC 保存菜单
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int save(RcMenu bean);

	/**
	 * @DESC 更新菜单
	 * @param bean
	 * @throws Exception
	 */
	public int update(RcMenu bean);
}
