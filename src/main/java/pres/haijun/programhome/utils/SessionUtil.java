package pres.haijun.programhome.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pres.haijun.programhome.bean.UserBean;

public class SessionUtil {
	private static String USER_SESSION = "userSession";
	
	public static void saveSession(HttpServletRequest request, UserBean loginUser) {
		HttpSession session = request.getSession(true);
		session.setAttribute(USER_SESSION, loginUser);
	}
	
	public static void delSession(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.removeAttribute(USER_SESSION);
	}
	
	public static boolean isLogin(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserBean sessionAccount = (UserBean) session.getAttribute(USER_SESSION);
		if(sessionAccount != null &&  sessionAccount.getUserName() != null){
			return true;
		}
		return false;
	}

}
