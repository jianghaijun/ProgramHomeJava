package pres.haijun.programhome.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pres.haijun.programhome.bean.UserBean;
import pres.haijun.programhome.model.BaseModel;
import pres.haijun.programhome.service.UserService;
import pres.haijun.programhome.utils.ConstantUtil;
import pres.haijun.programhome.utils.EncryptionUtil;
import pres.haijun.programhome.utils.StringTranscodingUtil;

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
 *  Created by HaiJun on 2017年10月30日 下午9:33:18
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	/**
	 * 注册用户
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "/regionUser", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseModel regionUser(@RequestParam String userName, @RequestParam String passWord) {
		userName = StringTranscodingUtil.stringToUtf8(userName);
		passWord = StringTranscodingUtil.stringToUtf8(passWord);
		
		
		BaseModel baseModel = new BaseModel();
		if ("".equals(userName) || null == userName || "".equals(passWord) || null == passWord) {
			baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
			baseModel.setMessage(ConstantUtil.USERNAME_PASSWORD_CAN_NOT_EMPTY);
		} else {
			UserBean useBean = new UserBean();
			useBean.setUser_name(userName);
			try {
				EncryptionUtil util = new EncryptionUtil();
				useBean.setPassword(util.encrypt(passWord));
			} catch (Exception e) {
				useBean.setPassword(passWord);
				e.printStackTrace();
			}
			// 是否已经注册过
			boolean isHaveRegion = userService.findUser(useBean) != null ? true : false;
			if (isHaveRegion) {
				baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
				baseModel.setMessage(ConstantUtil.USER_OLREADY_REGION);
			} else {
				int rows = userService.regionUser(useBean);
				if (rows > 0) {
					baseModel.setCode(ConstantUtil.CODE_FLAG_ZERO);
					baseModel.setMessage(ConstantUtil.REGION_SUCCESSFUL);
				} else {
					baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
					baseModel.setMessage(ConstantUtil.REGION_ERROR);
				}
			}
		}
		return baseModel;
	}
	
	@RequestMapping(value = "/findUser", method = RequestMethod.GET)
	@ResponseBody
	public UserBean findUser() {
		UserBean user = userService.findUser(new UserBean());
		return user;
	}
	
}
