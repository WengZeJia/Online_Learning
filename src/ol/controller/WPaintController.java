package ol.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ol.bean.ChatMessageCenter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wPaint")
public class WPaintController {

	@RequestMapping("toPaintPage.do")
	public ModelAndView toChatPage(HttpServletRequest req, Integer courseId,
			HttpServletResponse resp) {
		return new ModelAndView("wPaint/paintPage").addObject("courseId", courseId);
	}

	@RequestMapping("saveImg.do")
	public void saveImg(HttpServletRequest req, String image, Integer courseId, HttpServletResponse resp) {
		ChatMessageCenter cmCenter = (ChatMessageCenter) req.getServletContext().getAttribute("chatMessageCenter");
		cmCenter.addCourseImage(courseId, image);
	}


	@RequestMapping("getImage.do")
	public void saveImg(HttpServletRequest req, PrintWriter pw) {
		ChatMessageCenter cmCenter = (ChatMessageCenter) req.getServletContext().getAttribute("chatMessageCenter");
		String courseImage = cmCenter.getCourseImage(1);
		if(!StringUtils.isBlank(courseImage)) {
			pw.write(courseImage);
		}
	}

}
