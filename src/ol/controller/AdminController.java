package ol.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ol.dao.IAdminDao;
import ol.dao.ICoureseDao;
import ol.dao.IEnrollDao;
import ol.dao.IUserDao;
import ol.entity.Admin;
import ol.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
