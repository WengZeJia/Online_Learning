package ol.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ol.dao.IAdminDao;
import ol.dao.IContributionsDao;
import ol.dao.IPeriodicalDao;
import ol.dao.IUserDao;
import ol.entity.Admin;
import ol.entity.Contributions;
import ol.entity.Periodical;
import ol.entity.PeriodicalType;
import ol.entity.SolicitContributions;
import ol.entity.User;
import ol.entity.page.PageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private IPeriodicalDao perioDao;
	@Autowired
	private IContributionsDao contrDao;
	@Autowired
	private IUserDao userDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping("login.do")
	public String login(){
		return "admin/login";
	}
	
	/**
	 * 后台登录
	 * @param username
	 * @param pwd
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("loginAction.do")
	public ModelAndView loginAction(String username, String pwd, int role, HttpSession session, HttpServletRequest request){
		session.setAttribute("adminRole", role);
		if(role == 1){
			//系统管理员登录 
			Admin currAdmin = adminDao.findAdmin(username, pwd);
			if(currAdmin != null){
				session.setAttribute("currAdmin", currAdmin);
				return new ModelAndView("redirect:index.do");
			}else{
				request.setAttribute("msg", "用户名或密码错误！");
			}
		}else{
			//老师登录
			User user= userDao.findUserByUsernameAndPwd(username, pwd);
			if(user != null){
				session.setAttribute("user", user);
				return new ModelAndView("redirect:index.do");
			}else{
				request.setAttribute("msg", "用户名或密码错误！");
			}
		}
		return new ModelAndView("admin/login");
	}
	
	/**
	 * 退出登录 
	 * @param session
	 * @return
	 */
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute("currAdmin");
		session.removeAttribute("currPeriod");
		session.removeAttribute("adminRole");
		return new ModelAndView("admin/login");
	}
	
	/**
	 * 后台首页
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("index.do")
	public ModelAndView index(HttpServletRequest request, HttpSession session){
		System.out.println(session.getAttribute("currAdmin"));
		System.out.println(session.getAttribute("currPeriod"));
		/*if(session.getAttribute("currAdmin") != null || session.getAttribute("currPeriod") != null){
			return new ModelAndView("admin/login");
		}else{
			
		}*/
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
	
	/**
	 * 用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("listUser.do")
	public ModelAndView listUser(HttpServletRequest request){
		String username = request.getParameter("username");
		if(username == null)
			username = "";
		List<User> users =  userDao.findUserByUsername(username);
		return new ModelAndView("admin/listUser").addObject("userlist", users).addObject("username", username);
	}
	
	/**
	 * 用户编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("editUser.do")
	public ModelAndView editUser(HttpServletRequest request){
		String userId = request.getParameter("userId");
		if(userId == null){
			return new ModelAndView("admin/editUser");
		}else{
			return new ModelAndView("admin/editUser").addObject("user", userDao.findUser(Integer.parseInt(userId)));
		}
	}
	
	/**
	 * 用户编辑提交
	 * @param request
	 * @return
	 */
	@RequestMapping("saveUser.do")
	public ModelAndView saveUser(HttpServletRequest request,User user){
		try {
			userDao.saveUser(user);
		} catch (Exception e) {
			return new ModelAndView("admin/editUser").addObject("error","提交失败，请联系管理员！");
		}
		return new ModelAndView("redirect:listUser.do");
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @return
	 */
	@RequestMapping("delUser.do")
	public String delUser(HttpServletRequest request,User user, PrintWriter pw){
		try{
			userDao.delete(user.getUserId());
			return "success"; 
		}catch(Exception e){ 
			return "error";
		}
	}
	
	/**
	 * 
	 * @param page
	 * @param step
	 * @return
	 */
	@RequestMapping("periodMgr.do")
	public ModelAndView periodMgr(PageView<Periodical> page, int step){
		ModelAndView mav = new ModelAndView("admin/list-periodical");
		if(page == null){
			page = new PageView<Periodical>();
		}
		List<Periodical> list = perioDao.findAllPeriodicals(page.getFirstResult(), page.getMaxresult());
		mav.addObject("list", list);
		if(step == 99){
			mav.addObject("step", 99);
		}
		return mav;
	}
	
	@RequestMapping("toAddPeriod.do")
	public String toAddPeriod(HttpServletRequest request){
		request.setAttribute("typeList", perioDao.findAllPeriodicalTypes());
		return "admin/add-periodical";
	}
	
	@RequestMapping("savePeriod.do")
	public ModelAndView savePeriod(Periodical p, @RequestParam("periodPic") MultipartFile periodPic, HttpServletRequest request){
		if(!periodPic.isEmpty()){
			String realPath = request.getServletContext().getRealPath("/images/");
			String fileName = periodPic.getOriginalFilename();
			File filePath = new File(realPath, fileName);
			if(!filePath.getParentFile().exists()){
				filePath.getParentFile().mkdir();
			}
			try {
				periodPic.transferTo(new File(realPath+File.separator+fileName));
				p.setPic(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		perioDao.savePeriodical(p);
		return new ModelAndView("redirect:periodMgr.do?step=1");
	}
	
	@RequestMapping("toModifyPeriod.do")
	public ModelAndView toModifyPeriod(int pid, HttpServletRequest request){
		request.setAttribute("typeList", perioDao.findAllPeriodicalTypes());
		ModelAndView mav = new ModelAndView("admin/modify-periodical");
		mav.addObject("p", perioDao.findPeriodical(pid));
		return mav;
	}
	
	@RequestMapping("modifyPeriod.do")
	public ModelAndView modifyPeriod(Periodical p, @RequestParam("periodPic") MultipartFile periodPic, HttpServletRequest request){
		if(!periodPic.isEmpty()){
			String realPath = request.getServletContext().getRealPath("/images/");
			String fileName = periodPic.getOriginalFilename();
			File filePath = new File(realPath, fileName);
			if(!filePath.getParentFile().exists()){
				filePath.getParentFile().mkdir();
			}
			try {
				periodPic.transferTo(new File(realPath+File.separator+fileName));
				p.setPic(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			p.setPic(request.getParameter("_orgPicFile"));
		}
		perioDao.updatePeriodical(p);
		return periodMgr(null, 99);
	}
	
	/**
	 * 删除刊物
	 * @param pid
	 * @param pw
	 */
	@RequestMapping("deletePeriod.do")
	public void deletePeriod(int pid, PrintWriter pw){
		try {
			Periodical p = perioDao.findPeriodical(pid);
			p.setStatus(-1);
			perioDao.updatePeriodical(p);
			pw.write("0");
		} catch (Exception e) {
			pw.write("-1");
			e.printStackTrace();
		}
	}
	
	@RequestMapping("toAddPeriodType.do")
	public String toAddPeriodType(HttpServletRequest request){
		return "admin/add-periodical-type";
	}
	
	@RequestMapping("savePeriodType.do")
	public ModelAndView savePeriodType(PeriodicalType pt){
		perioDao.savePeriodicalType(pt);
		return new ModelAndView("redirect:periodTypTypeMgr.do");
	}
	
	@RequestMapping("periodTypTypeMgr.do")
	public ModelAndView periodTypeMgr(){
		ModelAndView mav = new ModelAndView("admin/list-periodical-type");
		List<PeriodicalType> list = perioDao.findAllPeriodicalTypes();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("toModifyType.do")
	public ModelAndView toModifyType(int ptid){
		ModelAndView mav = new ModelAndView("admin/modify-periodical-type");
		mav.addObject("pt", perioDao.findPeriodicalType(ptid));
		return mav;
	}
	
	@RequestMapping("updatePeriodType.do")
	public ModelAndView updatePeriodType(PeriodicalType pt){
		perioDao.updatePeriodicalType(pt);
		return new ModelAndView("redirect:periodTypTypeMgr.do");
	}
	
	/**
	 * 征稿启示管理
	 * @param step
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("scMgr.do")
	public ModelAndView solicitConMgr(int step, HttpSession session, PageView<SolicitContributions> page){
		ModelAndView mav = new ModelAndView("admin/list-solicit-contributions");
		if(page == null){
			page = new PageView<SolicitContributions>();
		}
		if(step == 99){
			mav.addObject("step", 99);
		}
		Periodical currPeriod = (Periodical) session.getAttribute("currPeriod");
		List<SolicitContributions> list = contrDao.findAllMySolicitContributions(currPeriod.getPeriodicalId(), page.getFirstResult(), page.getMaxresult());
		for (SolicitContributions sc : list) {
			sc.setContributionsCount(sc.getContributionses().size());
		}
		page.setRecords(list);
		page.setTotalrecord(contrDao.findAllMySolicitContributionsCount(currPeriod.getPeriodicalId()));
		mav.addObject("page", page);
		mav.addObject("today", new Date());
		return mav;
	}
	
	@RequestMapping("toAddSolicitCont.do")
	public ModelAndView toAddSolicitCont(){
		ModelAndView mav = new ModelAndView("admin/add-solicit-contributions");
		return mav;
	}
	
	/**
	 * 发布征稿启示
	 * @param sc
	 * @param step
	 * @param session
	 * @return
	 */
	@RequestMapping("saveSolicitCon.do")
	public ModelAndView saveSolicitCon(SolicitContributions sc, int step, HttpSession session){
		sc.setPublishTime(new Timestamp(System.currentTimeMillis()));
		Periodical currPeriod = (Periodical) session.getAttribute("currPeriod");
		sc.setPeriodical(currPeriod);
		contrDao.saveSolicitContributions(sc);
		return solicitConMgr(1, session, null);
	}
	
	/**
	 * 停止征稿
	 * @param scid
	 * @param pw
	 */
	@RequestMapping("stopSc.do")
	public void stopSc(int scid, PrintWriter pw){
		try {
			SolicitContributions sc = contrDao.findSolicitContributions(scid);
			sc.setDeadLine(new Date());
			contrDao.updateSolicitContributions(sc);
			pw.write("0");
		} catch (Exception e) {
			pw.write("-1");
			e.printStackTrace();
		}
	}
	
	/**
	 * 转到更新征稿信息页面
	 * @param scid
	 * @param session
	 * @return
	 */
	@RequestMapping("toModifySolicitCon.do")
	public ModelAndView toModifySolicitCon(int scid, HttpSession session){
		ModelAndView mav = new ModelAndView("admin/modify-solicit-contributions");
		mav.addObject("sc", contrDao.findSolicitContributions(scid));
		return mav;
	}
	
	/**
	 * 更新征稿信息
	 * @param sc
	 * @param step
	 * @param session
	 * @return
	 */
	@RequestMapping("updateSolicitCon.do")
	public ModelAndView updateSolicitCon(SolicitContributions sc, int step, HttpSession session){
		sc.setPublishTime(new Timestamp(System.currentTimeMillis()));
		contrDao.updateSolicitContributions(sc);
		return solicitConMgr(step, session, null);
	}
	
	
	/**
	 * 来稿管理
	 * @param step
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("contributionsMgr.do")
	public ModelAndView contributionsMgr(int step, HttpSession session, PageView<Contributions> page){
		ModelAndView mav = new ModelAndView("admin/list-contributions");
		if(page == null){
			page = new PageView<Contributions>();
		}
		Periodical currPeriod = (Periodical) session.getAttribute("currPeriod");
		List<Contributions> list = contrDao.findPeriodicalsContributions(currPeriod.getPeriodicalId(), page.getFirstResult(), page.getMaxresult());
		page.setTotalrecord(contrDao.findPeriodicalsContributionsCount(currPeriod.getPeriodicalId()));
		page.setRecords(list);
		mav.addObject("page", page);
		if(step == 99){
			mav.addObject("step", 99);
		}
		return mav;
	}
	
	@RequestMapping("contributionDetail.do")
	public ModelAndView contributionDetail(int cid){
		ModelAndView mav = new ModelAndView("admin/contributions-detail");
		Contributions c = contrDao.findContributions(cid);
		mav.addObject("cont", c);
		return mav;
	}
	
	@RequestMapping("adopt.do")
	public void adopt(int cid, PrintWriter pw){
		try {
			Contributions ctr = contrDao.findContributions(cid);
			ctr.setStatus(1);
			contrDao.updateContributions(ctr);
			pw.write("0");
		} catch (Exception e) {
			pw.write("-1");
			e.printStackTrace();
		}
	}
	
	@RequestMapping("refuse.do")
	public void refuse(int cid, PrintWriter pw){
		try {
			Contributions ctr = contrDao.findContributions(cid);
			ctr.setStatus(-1);
			contrDao.updateContributions(ctr);
			pw.write("0");
		} catch (Exception e) {
			pw.write("-1");
			e.printStackTrace();
		}
	}
}
