package ol.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ol.bean.ChatMessageCenter;
import ol.bean.UserBean;
import ol.dao.IAdminDao;
import ol.dao.ICoureseDao;
import ol.dao.IEnrollDao;
import ol.dao.IUserDao;
import ol.entity.Admin;
import ol.entity.Courese;
import ol.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IEnrollDao enrollDao;
	@Autowired
	private ICoureseDao coureseDao;
	
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
			if(user != null && user.getRole() == 1){
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
		session.removeAttribute("user");
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
			return new ModelAndView("admin/editUser").addObject("u", userDao.findUser(Integer.parseInt(userId)));
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
			if(user.getUserId() != null){
				User u = userDao.findUser(user.getUserId());
				u.setPwd(user.getPwd());
				u.setRealName(user.getRealName());
				u.setRole(user.getRole());
				userDao.saveUser(u);
			}else
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
	@ResponseBody
	public String delUser(HttpServletRequest request,User user){
		try{
			userDao.delete(user.getUserId());
			return "success"; 
		}catch(Exception e){ 
			return "error";
		}
	}
	
	/**
	 * 后台课程列表
	 * @param request
	 * @return
	 */
	@RequestMapping("listCourese.do")
	public ModelAndView listCourese(HttpServletRequest request, HttpSession session){
//		String username = request.getParameter("cname");
//		String type = request.getParameter("type");
		try {
			User user = (User) session.getAttribute("user");
			List<Courese> coureses =  coureseDao.findAllCoureses(user.getUserId());
			return new ModelAndView("admin/listCourese").addObject("coureses", coureses);
		} catch (Exception e) {
			return new ModelAndView("redirect:login.do");
		}
	}
	
	/**
	 * 课程编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("editCourese.do")
	public ModelAndView editCourese(HttpServletRequest request){
		String coureseId = request.getParameter("coureseId");
		if(coureseId == null){
			return new ModelAndView("admin/editCourese");
		}else{
			return new ModelAndView("admin/editCourese").addObject("courese", coureseDao.findCourese(Integer.parseInt(coureseId)));
		}
	}
	
	/**
	 * 课程编辑提交
	 * @param request
	 * @return
	 */
	@RequestMapping("saveCourese.do")
	public ModelAndView saveCourese(HttpServletRequest request,Courese courese, HttpSession session){
		User user = (User) session.getAttribute("user");
		try {
			if (courese.getCoureseId() == null) {
				courese.setStatus(0);
			}
			courese.setReleaseTime(new Timestamp(new Date().getTime()));
			courese.setUser(user);
			coureseDao.saveCourese(courese);
		} catch (Exception e) {
			return new ModelAndView("admin/editCourese").addObject("error","提交失败，请联系管理员！");
		}
		ServletContext application = request.getServletContext();  //获取application
		ChatMessageCenter cmCenter =  (ChatMessageCenter) application.getAttribute("chatMessageCenter"); //获取聊天信息数据center
		cmCenter.addCourse(courese.getCoureseId()); //添加课程
		UserBean courseTeacher = cmCenter.getCourseTeacher(courese.getCoureseId());
		if(courseTeacher == null) {
			cmCenter.addCourseUser(courese.getCoureseId(), new UserBean(user));
		}
		cmCenter.setUserOnline(user.getUserId());
		return new ModelAndView("redirect:listCourese.do");
	}
	
	/**
	 * 更改课程上线状态
	 * @param request
	 * @return
	 */
	@RequestMapping("changeCStatus.do")
	@ResponseBody
	public String changeCStatus(HttpServletRequest request){
		try{
			String status = request.getParameter("status");
			String coureseId = request.getParameter("coureseId");
			Courese cou = coureseDao.findCourese(Integer.parseInt(coureseId));
			cou.setStatus(Integer.parseInt(status));
			coureseDao.saveCourese(cou);
			return "success"; 
		}catch(Exception e){ 
			return "error";
		}
	}
	
	/**
	 * 课程报名列表
	 * @param request
	 * @return
	 */
	@RequestMapping("listEnroll.do")
	public ModelAndView listEnroll(HttpServletRequest request, HttpSession session){
		String coureseId = request.getParameter("coureseId");
		try {
			Courese cou = coureseDao.findCourese(Integer.parseInt(coureseId));
			return new ModelAndView("admin/listEnrolls").addObject("enrolls", cou.getEnrolls());
		} catch (Exception e) {
			return new ModelAndView("redirect:login.do");
		}
	}
	
	/**
	 * 实时课程列表
	 * @param request
	 * @return
	 */
	@RequestMapping("listCoureseChat.do")
	public ModelAndView listCoureseChat(HttpServletRequest request, HttpSession session){
		try {
			User user = (User) session.getAttribute("user");
			List<Courese> coureses =  coureseDao.findAllCoureses(user.getUserId());
			return new ModelAndView("admin/listCoureseChat").addObject("coureses", coureses);
		} catch (Exception e) {
			return new ModelAndView("redirect:login.do");
		}
	}
	
}
