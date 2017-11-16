package pres.haijun.programhome.controller;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pres.haijun.programhome.bean.UserBean;
import pres.haijun.programhome.model.BaseModel;
import pres.haijun.programhome.model.LoginModel;
import pres.haijun.programhome.service.UserService;
import pres.haijun.programhome.utils.ConstantUtil;
import pres.haijun.programhome.utils.EncryptionUtil;
import pres.haijun.programhome.utils.RandomCodeUtil;
import pres.haijun.programhome.utils.SessionUtil;
import pres.haijun.programhome.utils.TextUtil;

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
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/homePage")
	public String homePage() {
		return "login";
	}
	
	/**
	 * 登陆接口
	 * 
	 * @param request
	 * @param model
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, String userName, String password) {

		if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
			model.addAttribute("username", "用户名、密码不能为空");
			return "login";
		}
		
		UserBean user = new UserBean();
		user.setUserPhone(userName);
		try {
			EncryptionUtil encryptionUtil = new EncryptionUtil();
			user.setPassword(encryptionUtil.encrypt(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserBean loginUser = userService.login(user);
		if (loginUser == null) {
			model.addAttribute("error", "用户名或密码错误!");
			return "login";
		} else {
			SessionUtil.saveSession(request, loginUser);
			return "redirect:/index";
		}
	}
	
	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		if (SessionUtil.isLogin(request)) {
			SessionUtil.delSession(request);
		}
		return "login";
	}

	/**
	 * 后台首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {

		if (!SessionUtil.isLogin(request)) {
			return "redirect:/admin/login";
		}

		return "index";
	}
	
	/**
	 * 注册用户
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "/user/registeredUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseModel regionUser(@RequestBody UserBean userBean) {
		String userPhone = userBean.getUserPhone();
		String passWord = userBean.getPassword();
		
		BaseModel baseModel = new BaseModel();
		if (TextUtil.isEmpty(userPhone) || TextUtil.isEmpty(passWord)) {
			baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
			baseModel.setMessage(ConstantUtil.USERPHONE_PASSWORD_CAN_NOT_EMPTY);
		} else {
			userBean.setUserId(RandomCodeUtil.createCode(userPhone));
			try {
				EncryptionUtil util = new EncryptionUtil();
				userBean.setPassword(util.encrypt(passWord));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 是否已经注册过
			boolean isHaveRegion = userService.findUser(userBean) != null ? true : false;
			if (isHaveRegion) {
				baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
				baseModel.setMessage(ConstantUtil.USER_OLREADY_REGION);
			} else {
				userBean.setCreateTime(new Date());
				int rows = userService.registeredUser(userBean);
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

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public LoginModel login(@RequestBody UserBean user) {
		String userPhone = user.getUserPhone();
		String password = user.getPassword();
		LoginModel model = new LoginModel();
		if (TextUtil.isEmpty(userPhone) || TextUtil.isEmpty(password)) {
			model.setCode(ConstantUtil.CODE_FLAG_ONE);
			model.setMessage(ConstantUtil.USERPHONE_PASSWORD_CAN_NOT_EMPTY);
			model.setResult(new UserBean());
		} else {
			try {
				EncryptionUtil util = new EncryptionUtil();
				user.setPassword(util.encrypt(password));
			} catch (Exception e) {
				e.printStackTrace();
			}
			user = userService.login(user);
			if (user != null) {
				model.setCode(ConstantUtil.CODE_FLAG_ZERO);
				model.setMessage(ConstantUtil.LOGIN_SUCCESSFUL);
				model.setResult(user);
			} else {
				model.setCode(ConstantUtil.CODE_FLAG_ONE);
				model.setMessage(ConstantUtil.USERPHONE_PASSWORD_ERROR);
				model.setResult(new UserBean());
			}
		}
		return model;
	}
	
	/**
	 * 修改用户名、密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/updatePassWord", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseModel updatePassWord(@RequestBody UserBean user) {
		String userId = user.getUserId();
		String newPassword = user.getNewPassword();
		
		BaseModel model = new BaseModel();
		if (TextUtil.isEmpty(userId) || TextUtil.isEmpty(newPassword)) {
			model.setCode(ConstantUtil.CODE_FLAG_ONE);
			model.setMessage(ConstantUtil.PARAMETER_CAN_NOT_EMPTY);
		} else {
			EncryptionUtil util;
			try {
				util = new EncryptionUtil();
				user.setNewPassword(util.encrypt(newPassword));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int size = userService.updateUser(user);
			if (size > 0) {
				model.setCode(ConstantUtil.CODE_FLAG_ZERO);
				model.setMessage(ConstantUtil.UPDATE_SUCCESSFUL);
			} else {
				model.setCode(ConstantUtil.CODE_FLAG_ONE);
				model.setMessage(ConstantUtil.UPDATE_ERROR);
			}
		}
		return model;
	}
	
	/**
	 * 头像上传
	 * @param file
	 * @param userPhone
	 * @return
	 */
	@RequestMapping(value = "/user/uploadUserHead", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel uploadUserHead(@RequestParam(value = "file", required = false) MultipartFile file, String userId) {
		BaseModel baseModel = new BaseModel();
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			fileName = fileName.substring(fileName.lastIndexOf("."));
			fileName = System.currentTimeMillis() + fileName;
			
			File targetFile = new File(ConstantUtil.USER_HEAD_URL, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			// 保存
			try {
				file.transferTo(targetFile);
				UserBean user = new UserBean();
				user.setUserId(userId);
				user.setUserHead(ConstantUtil.USER_HEAD + fileName);
				userService.updateUser(user);
				baseModel.setCode(ConstantUtil.CODE_FLAG_ZERO);
				baseModel.setMessage(ConstantUtil.UPLOAD_SUCCESSFUL);
			} catch (Exception e) {
				e.printStackTrace();
				baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
				baseModel.setMessage(ConstantUtil.ADD_FILE);
			}
		} else {
			baseModel.setCode(ConstantUtil.CODE_FLAG_ONE);
			baseModel.setMessage(ConstantUtil.ADD_FILE);
		}
		return baseModel;
	}
}
