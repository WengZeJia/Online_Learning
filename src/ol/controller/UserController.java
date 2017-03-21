package ol.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import ol.dao.IContributionsDao;
import ol.dao.IPeriodicalDao;
import ol.dao.IUserDao;
import ol.entity.Contributions;
import ol.entity.LeanQueryModel;
import ol.entity.Periodical;
import ol.entity.SolicitContributions;
import ol.entity.User;
import ol.entity.page.PageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IPeriodicalDao periDao;
	@Autowired
	private IContributionsDao contDao;
	
	@RequestMapping("index.do")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	
	@RequestMapping("loadSolicitContributions.do")
	public ModelAndView loadSolicitContributions(LeanQueryModel condition,PageView<SolicitContributions> page){
		ModelAndView mav = new ModelAndView("scList");
		if(page == null){
			page = new PageView<SolicitContributions>();
		}
		//List<SolicitContributions> scList = contDao.findAllSolicitContributions(page.getFirstResult(), page.getMaxresult());
		List<SolicitContributions> scList = contDao.searchPosition(condition);
		page.setRecords(scList);
		page.setTotalrecord(contDao.findAllSolicitContributionsCount());
		mav.addObject("page", page);
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
	
	@RequestMapping("loadHotPeriod.do")
	public ModelAndView loadHotPeriod(){
		ModelAndView mav = new ModelAndView("hotPeriod");
		List<Periodical> list = periDao.findHotPeriodicals();
		mav.addObject("hotPeriods", list);
		return mav;
	}
	
	@RequestMapping("profile.do")
	public ModelAndView profile(HttpSession session, PageView<Contributions> page){
		ModelAndView mav = new ModelAndView("profile");
		if(page == null){
			page = new PageView<Contributions>();
		}
		User user = (User) session.getAttribute("currUser");
		if(user == null){
			mav.setViewName("login");
			return mav;
		}
		List<Contributions> list = contDao.findUsersContributions(user.getUserId(), page.getFirstResult(), page.getMaxresult());
		page.setRecords(list);
		page.setTotalrecord(contDao.findUsersContributionsCount(user.getUserId()));
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping("scDetail.do")
	public ModelAndView scDetail(int scid){
		ModelAndView mav = new ModelAndView("scDetail");
		SolicitContributions sc = contDao.findSolicitContributions(scid);
		mav.addObject("sc", sc);
		return mav;
	}
	
	@RequestMapping("toContr.do")
	public ModelAndView toContributePage(int scid, HttpSession session){
		ModelAndView mav = new ModelAndView("contribute");
		if(session.getAttribute("currUser") == null){
			mav.setViewName("login");
			return mav;
		}
		SolicitContributions sc = contDao.findSolicitContributions(scid);
		mav.addObject("sc", sc);
		return mav;
	}
	
	@RequestMapping("contribute.do")
	public void contribute(int scid, String content, String title, HttpSession session, PrintWriter pw){
//		ModelAndView mav = new ModelAndView("profile");
		Contributions c = new Contributions();
		c.setPublishTime(new Timestamp(System.currentTimeMillis()));
		c.setContent(content);
		c.setTitle(title);
		c.setSolicitContributions(contDao.findSolicitContributions(scid));
		c.setStatus(0);
		User user = (User) session.getAttribute("currUser");
		if(user == null){
			pw.write(-1);
		}
		c.setUser(user);
		contDao.saveContributions(c);
//		return mav;
		pw.write(0);
	}
	
	@RequestMapping("pdetail.do")
	public ModelAndView viewPeriod(int pid){
		ModelAndView mav = new ModelAndView("period");
		Periodical p = periDao.findPeriodical(pid);
		mav.addObject("p", p);
		return mav;
	}
}
