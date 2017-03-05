package com.fancy.cms.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.dao.RcAdminMapper;
import com.fancy.cms.model.PortDept;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.model.RcUserRole;
import com.fancy.cms.model.Students;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.service.RcAdminService;
import com.fancy.cms.util.Constants;
import com.fancy.cms.util.UserUtil;
import com.fancy.cms.vo.RoleVo;

/**
 * @author rc.wys
 * @date 2013-5-27 下午5:06:37
 * @DESC RC管理员服务接口实现类
 */
@Service
@Transactional
public class RcAdminServiceImpl implements RcAdminService {

	protected static final Log log = LogFactory
			.getLog(RcAdminServiceImpl.class);

	@Autowired
	private RcAdminMapper rcAdminMapper;

	@Override
	public List<RcAdmin> getUserList(Map<String, Object> map) {
		return rcAdminMapper.getUserList(map);
	}

	@Override
	public void addUser(RcAdmin admin) {
		rcAdminMapper.addUser(admin);
	}

	@Override
	public void deleteUserById(String[] userId) {
		rcAdminMapper.deleteUserById(userId);
	}

	@Override
	public RcAdmin getUserById(String adminId) {
		return rcAdminMapper.getUserById(adminId);
	}

	@Override
	public void updateUser(RcAdmin admin) {
		rcAdminMapper.updateUser(admin);
	}

	public static void main(String[] args) throws Exception {
		RcAdminService ps = new RcAdminServiceImpl();
		System.err.println(ps.getPasswdByUsername("admin").getCreateTime());
		// List<RcAdmin> rcAdmins = ps.getList();
		// List<RoleVo> roleVos = ps.findListRoleByID(1);
		// System.err.println();
	}

	@Override
	@Transactional(readOnly = true)
	public RcAdmin findByID(int rcAdminId) {
		RcAdmin rcAdmin = new RcAdmin();
		rcAdmin = this.rcAdminMapper.selectByPrimaryKey(rcAdminId);
		return rcAdmin;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RcAdmin> getList() {
		List<RcAdmin> rcAdmins = new ArrayList<RcAdmin>();
		rcAdmins = this.rcAdminMapper.getList();
		return rcAdmins;
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		int total = 0;
		Pagination pagination = null;
		List<RcAdmin> rcAdmins = null;
		RcAdminServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
				+ pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		rcAdmins = this.rcAdminMapper.getPage(begin, end);
		total = this.rcAdminMapper.getCount();
		pagination = new Pagination(pageNo, pageSize, total, rcAdmins);
		return pagination;
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageForContacts(int pageNo, int pageSize,
			List<Integer> contactIds) {
		int total = 0;
		Pagination pagination = null;
		List<RcAdmin> rcAdmins = null;
		RcAdminServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
				+ pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		rcAdmins = this.rcAdminMapper
				.getPageForContacts(begin, end, contactIds);
		total = this.rcAdminMapper.getCountForContacts(contactIds);
		pagination = new Pagination(pageNo, pageSize, total, rcAdmins);
		return pagination;
	}

	@Override
	@Transactional(readOnly = true)
	public RcAdmin getPasswdByUsername(String userName) {
		RcAdmin rcAdmin = new RcAdmin();
		rcAdmin = this.rcAdminMapper.getRcAdminByUsername(userName);
		return rcAdmin;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, List<RcAdmin>> getUserListByLevel() {
		Map<String, List<RcAdmin>> map = new LinkedHashMap<String, List<RcAdmin>>();
		List<RcAdmin> rcAdmins = this.rcAdminMapper.getListOrderbyLevel();
		for (RcAdmin rcAdmin : rcAdmins) {
			if (map.containsKey(Constants.DEPTLEVEL_MAP.get(rcAdmin
					.getDeptLevel()))) {
				map.get(Constants.DEPTLEVEL_MAP.get(rcAdmin.getDeptLevel()))
						.add(rcAdmin);
			} else {
				List<RcAdmin> list = new ArrayList<RcAdmin>();
				list.add(rcAdmin);
				map.put(Constants.DEPTLEVEL_MAP.get(rcAdmin.getDeptLevel()),
						list);
			}
		}
		return map;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, List<RcAdmin>> getUserMapByIds(String[] ids) {
		Map<String, List<RcAdmin>> map = new LinkedHashMap<String, List<RcAdmin>>();
		List<RcAdmin> rcAdmins = this.rcAdminMapper.getListByIds(ids);
		for (RcAdmin rcAdmin : rcAdmins) {
			if (map.containsKey(Constants.DEPTLEVEL_MAP.get(rcAdmin
					.getDeptLevel()))) {
				map.get(Constants.DEPTLEVEL_MAP.get(rcAdmin.getDeptLevel()))
						.add(rcAdmin);
			} else {
				List<RcAdmin> list = new ArrayList<RcAdmin>();
				list.add(rcAdmin);
				map.put(Constants.DEPTLEVEL_MAP.get(rcAdmin.getDeptLevel()),
						list);
			}
		}
		return map;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RcAdmin> getListByDepts(String[] deptIds) {
		return this.rcAdminMapper.getListByDepts(deptIds);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RcAdmin> getListByIds(String[] userIds) {
		return this.rcAdminMapper.getListByIds(userIds);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RcAdmin> getListByNames(String[] userNames) {
		return this.rcAdminMapper.getListByNames(userNames);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RcAdmin> getListByDeptId(Integer deptId) {
		return this.rcAdminMapper.getListByDeptId(deptId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fancy.cms.service.RcAdminService#checkPapers(java.lang.String)
	 */
	@Override
	public int checkPapers(String papers) {
		return rcAdminMapper.checkPapers(papers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fancy.cms.service.RcAdminService#getPasswdByPapers(java.lang.String)
	 */
	@Override
	public RcAdmin getPasswdByPapers(String papers) {
		RcAdmin rcAdmin = new RcAdmin();
		rcAdmin = this.rcAdminMapper.getPasswdByPapers(papers);
		return rcAdmin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fancy.cms.service.RcAdminService#checkPapersByEditId(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public int checkPapersByEditId(String userid, String papers) {
		return rcAdminMapper.checkPapersByEditId(userid, papers);
	}

	// 根据专业ID查询对应专业人员
	@Override
	public List<RcAdmin> getUserListByDeptId(String deptId) {
		// TODO Auto-generated method stub
		return rcAdminMapper.getListByProfessions(deptId);
	}

	@Override
	public List<PortDept> getddpfList(HttpServletRequest request,
			ModelMap model, String dpfId) {
		RcAdmin user = UserUtil.getUser(request);
		// 专业list
		List<PortDept> dpfList = user.getDeptprofessions();
		dpfList.remove(user.getDept());
		if (dpfList.size() == 1) {
			dpfList.add(0, user.getDept());
			dpfList.remove(user.getDept());
		} else {
			dpfList.add(0, user.getDept());
		}
		// 回传下拉选中的deptId
		String professionID = "";
		if (dpfId == null || "undefined".equals(dpfId)) {
			professionID = dpfList.get(0).getNo();
		} else {
			professionID = dpfId;
		}
		model.addAttribute("dpfList", dpfList);
		model.addAttribute("professionID", professionID);
		request.setAttribute("pfID", professionID);
		return dpfList;
	}

	@Override
	public List<Integer> getDeptIdByUserId(String[] userID) {
		// TODO Auto-generated method stub
		return rcAdminMapper.getDeptIdByUserId(userID);
	}

	@Override
	public List<RcAdmin> getAdminList() {
		return rcAdminMapper.getAdminList();
	}

	@Override
	public int addAdmin(RcAdmin rcAdmin) {

		return rcAdminMapper.addAdmin(rcAdmin);
	}

	@Override
	public RcAdmin getAdminById(Integer id) {
		return rcAdminMapper.getAdminById(id);
	}

	@Override
	public int updateRcAdmin(RcAdmin rcAdmin) {

		return rcAdminMapper.updateRcAdmin(rcAdmin);
	}

	@Override
	public void delRcAdminById(String id) {
		rcAdminMapper.deRcAdminById(id);
	}

	@Override
	public List<RcAdmin> getAdminListByDeptId(String deptId) {
		return rcAdminMapper.getAdminListByDeptId(deptId);
	}

	@Override
	public RcAdmin getAdminName(String checkIdList) {
		// TODO Auto-generated method stub
		return rcAdminMapper.getAdminName(checkIdList);
	}

	@Override
	public List<RcAdmin> getAdminByIds(String ids) {
		// TODO Auto-generated method stub
		return rcAdminMapper.getAdminByIds(ids);
	}

	@Override
	public Integer getRolebyName(String name) {
		return rcAdminMapper.getRolebyName(name);
	}

	@Override
	public String getpwdByPapers(String papers) {
		return rcAdminMapper.getpwdByPapers(papers);
	}

	@Override
	public void updatebystuname(RcAdmin rcAdmin) {
		rcAdminMapper.updatebystuname(rcAdmin);
	}

	@Override
	public RcAdmin getAdminByName(String stuname) {
		return rcAdminMapper.getAdminByName(stuname);
	}

	@Override
	public List<RcAdmin> selectadmin() {
		return rcAdminMapper.selectadmin();
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize, String papers) {
		int total = 0;
		Pagination pagination = null;
		List<Teachers> users = null;
		TeacherServiceimpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
				+ pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		users = this.rcAdminMapper.getPage(begin, end, papers);
		total = this.rcAdminMapper.getCount(papers);
		pagination = new Pagination(pageNo, pageSize, total, users);
		return pagination;
	}

	@Override
	public void fromXls2(List<RcAdmin> rcAdmins) {
		rcAdminMapper.fromXls2(rcAdmins);
	}

}
