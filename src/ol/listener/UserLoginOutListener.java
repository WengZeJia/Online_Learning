package ol.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ol.bean.ChatMessageCenter;
import ol.entity.User;

/**
 * 用户上下线监听器，更改ChatMessageCenter中的courseUserMap中用户的状态
 */
public class UserLoginOutListener implements HttpSessionListener, HttpSessionAttributeListener {

	/**
     * 定义监听的用户session属性名.
     */
    public final static String LISTENER_USER_KEY = "user";

	
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		if(LISTENER_USER_KEY.equals(sbe.getName())) { //用户上线
			User user = (User) sbe.getValue();
			ChatMessageCenter cmCenter = (ChatMessageCenter) sbe.getSession().getServletContext().getAttribute("chatMessageCenter");
			cmCenter.setUserOnline(user.getUserId());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		if(LISTENER_USER_KEY.equals(sbe.getName())) { //用户下线
			User user = (User) sbe.getValue();
			ChatMessageCenter cmCenter = (ChatMessageCenter) sbe.getSession().getServletContext().getAttribute("chatMessageCenter");
			cmCenter.setUserOutline(user.getUserId());
		}
	}

	
	
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		if(LISTENER_USER_KEY.equals(sbe.getName())) { 
			User outLine = (User) sbe.getValue();
			ChatMessageCenter cmCenter = (ChatMessageCenter) sbe.getSession().getServletContext().getAttribute("chatMessageCenter");
			cmCenter.setUserOutline(outLine.getUserId()); //设置旧登用户下线
			
			User onLine = (User) sbe.getSession().getAttribute("user");
			cmCenter.setUserOnline(onLine.getUserId()); //设置新登用户上线
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session create...");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 如果session超时, 则从map中移除这个用户  
        try {  
			se.getSession().removeAttribute(LISTENER_USER_KEY);  
        } catch (Exception e) {  
        	System.out.println("session删除出错...");
        }  
		
	}

}
