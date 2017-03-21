package ol.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import ol.dao.IAdminDao;
import ol.dao.ICoureseDao;
import ol.dao.IEnrollDao;
import ol.dao.IUserDao;
import ol.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IEnrollDao enrollDao;
	@Autowired
	private ICoureseDao coureseDao;
	
	@RequestMapping("index.do")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	
	@RequestMapping("toRegist.do")
	public String toRegist(){
		return "regist";
	}
	
	@RequestMapping("regist.do")
	public ModelAndView regist(User user){
		ModelAndView mav = new ModelAndView("login");
		try {
			userDao.saveUser(user);
			
			mav.addObject("msg", "注册成功，请登录！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}	
		
	
	@RequestMapping("login.do")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute("currUser");
		return index();
	}
	
	@RequestMapping("loginForm.do")
	public ModelAndView login(String username, String pwd, HttpSession session){
		User user = userDao.findUserByUsernameAndPwd(username, pwd);
		if(user != null){
			user.setLastLogin(new Timestamp(System.currentTimeMillis()));
			userDao.updateUser(user);
			session.setAttribute("currUser", user);
			return new ModelAndView("redirect:index.do");
		}else{
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("msg", "用户名或密码错误！");
			return mav;
		}
	}
}
