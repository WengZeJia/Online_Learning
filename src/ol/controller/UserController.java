package ol.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ol.dao.IAdminDao;
import ol.dao.ICoureseDao;
import ol.dao.IEnrollDao;
import ol.dao.IUserDao;
import ol.entity.Courese;
import ol.entity.Enroll;
import ol.entity.LeanQueryModel;
import ol.entity.User;
import ol.entity.page.PageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;


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
	
	@RequestMapping("list.do")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
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
		session.removeAttribute("user");
		return index();
	}
	
	@RequestMapping("loginForm.do")
	public ModelAndView login(String username, String pwd, HttpSession session){
		User user = userDao.findUserByUsernameAndPwd(username, pwd);
		if(user != null){
			user.setLastLogin(new Timestamp(System.currentTimeMillis()));
			userDao.updateUser(user);
			session.setAttribute("user", user);
			return new ModelAndView("redirect:index.do");
		}else{
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("msg", "用户名或密码错误！");
			return mav;
		}
	}
	@RequestMapping("search.do")
	public ModelAndView searchCourese(HttpServletRequest request,LeanQueryModel condition){
		int pageNo =  request.getParameter("pageNo") ==null?1:Integer.parseInt(request.getParameter("pageNo"));
		PageView<Courese> page = new PageView<Courese>(4, pageNo);
		if(condition.getKeyword()==null) condition.setKeyword("");
		condition.setFirstResult(page.getFirstResult());
		condition.setMaxResutl(page.getMaxresult());
		List<Courese> scList = coureseDao.searchCourese(condition);
		page.setTotalrecord(coureseDao.findCount(condition));
		page.setRecords(scList);
		page.setTotalrecord(coureseDao.findCount(condition));
		return new ModelAndView("scList").addObject("page", page);
	}
	
	@RequestMapping("index.do")
	public ModelAndView loadCourese(HttpServletRequest request,LeanQueryModel condition){
		int pageNo =  request.getParameter("pageNo") ==null?1:Integer.parseInt(request.getParameter("pageNo"));
		PageView<Courese> page = new PageView<Courese>(4, pageNo);
		if(condition.getKeyword()==null) condition.setKeyword("");
		condition.setFirstResult(page.getFirstResult());
		condition.setMaxResutl(page.getMaxresult());
		List<Courese> scList = coureseDao.searchCourese(condition);
		page.setTotalrecord(coureseDao.findCount(condition));
		page.setRecords(scList);
		return new ModelAndView("index").addObject("page", page);
	}
	//报名记录
	@RequestMapping("record.do")
	public ModelAndView searchEnroll(HttpServletRequest request,LeanQueryModel condition){
		User user = (User) request.getSession().getAttribute("user");
		int pageNo =  request.getParameter("pageNo") ==null?1:Integer.parseInt(request.getParameter("pageNo"));
		PageView<Enroll> page = new PageView<Enroll>(4,pageNo);
		if(user != null){
			condition.setFirstResult(page.getFirstResult());
			condition.setMaxResutl(page.getMaxresult());
			List<Enroll> list = enrollDao.findEnrollByUserId(user.getUserId(), condition);
			page.setTotalrecord(enrollDao.findCount(condition));
			page.setRecords(list);
			return new ModelAndView("profile").addObject("page", page);
		}else{
			return new ModelAndView("redirect:login.do");
		}
	}
	/**
	 * 新增报名记录
	 */
	@RequestMapping(value="save.do")
	public void save(HttpServletRequest request,HttpSession session, PrintWriter pw) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> rs =new HashMap<String, Object>();
		if(user != null){
			try{
				int pId = Integer.parseInt(request.getParameter("pid"));
				Courese cs = coureseDao.findCourese(pId);
				List<Enroll> els = enrollDao.findEnroll(pId, user.getUserId());
				if(els == null || els.isEmpty()){
					Enroll el = new Enroll();
					el.seteTime(new Date());
					el.setCourese(cs);
					el.setStatus(1);
					el.setUser((User)session.getAttribute("user"));
					enrollDao.saveEnroll(el);
					rs.put("result", "00");
					rs.put("msg","报名成功！");
				}else {
					rs.put("result", "02");
				}
				
     		} catch(Exception e){ 
				rs.put("result", "03");
//     			throw new RuntimeException(e);
			}
		}else{
			rs.put("result", "01");
			}
		pw.write(new Gson().toJson(rs));
	}
	//报名记录
		@RequestMapping("scDetail.do")
		public ModelAndView scDetail(HttpServletRequest request,LeanQueryModel condition){
				int pid = Integer.parseInt(request.getParameter("coureseId"));
				Courese c = (Courese) coureseDao.findCourese(pid);
				return new ModelAndView("scDetail").addObject("c", c);
						
		}
}
