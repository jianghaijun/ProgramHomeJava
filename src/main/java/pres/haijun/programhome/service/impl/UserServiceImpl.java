package pres.haijun.programhome.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pres.haijun.programhome.bean.UserBean;
import pres.haijun.programhome.dao.UserDao;
import pres.haijun.programhome.service.UserService;

/**
 * 
 *                     _ooOoo_
 *                    o8888888o
 *                    88" . "88
 *                    (| -_- |)
 *                    O\  =  /O
 *                 ____/`---'\____
 *               .'  \\|     |//  `.
 *              /  \\|||  :  |||//  \
 *             /  _||||| -:- |||||-  \
 *             |   | \\\  -  /// |   |
 *             | \_|  ''\---/''  |   |
 *             \  .-\__  `-`  ___/-. /
 *           ___`. .'  /--.--\  `. . __
 *        ."" '<  `.___\_<|>_/___.'  >'"".
 *       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *       \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                     `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 			                     佛祖保佑       永无BUG
 *  Created by HaiJun on 2017年10月30日 下午9:34:39
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public int regionUser(UserBean user) {
		return userDao.regionUser(user);
	}
	
	public UserBean findUser(UserBean user) {
		return userDao.findUser(user);
	}

}