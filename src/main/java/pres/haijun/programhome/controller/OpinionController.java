package pres.haijun.programhome.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pres.haijun.programhome.bean.OpinionBean;
import pres.haijun.programhome.model.BaseModel;
import pres.haijun.programhome.model.OpinionModel;
import pres.haijun.programhome.service.OpinionService;
import pres.haijun.programhome.utils.ConstantUtil;
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
 * 			                  佛祖保佑       永无BUG
 *
 * 			  Create HaiJun By 2017年11月14日
 *
 */
@Controller
@RequestMapping(value = "/opinion")
public class OpinionController {
	@Resource
	private OpinionService opinionService;

	/**
	 * 添加意见
	 * @return
	 */
	@RequestMapping(value = "/addOpinion", method = RequestMethod.POST)
	@ResponseBody
	public BaseModel addOpinion(@RequestBody OpinionBean opinionBean) {
		BaseModel model = new BaseModel();
		String userId = opinionBean.getUserId();
		String opinion = opinionBean.getOpinion();
		if (TextUtil.isEmpty(userId) || TextUtil.isEmpty(opinion)) {
			model.setCode(ConstantUtil.CODE_FLAG_ONE);
			model.setMessage(ConstantUtil.PARAMETER_CAN_NOT_EMPTY);
		} else {
			int row = opinionService.insertOpinion(opinionBean);
			if (row > 0) {
				model.setCode(ConstantUtil.CODE_FLAG_ZERO);
				model.setMessage(ConstantUtil.ADD_SUCCESSFUL);
			} else {
				model.setCode(ConstantUtil.CODE_FLAG_ONE);
				model.setMessage(ConstantUtil.ADD_ERROR);
			}
		}
		return model;
	}
	
	/**
	 * 查询意见
	 * @return
	 */
	@RequestMapping(value = "/selectOpinions", method = RequestMethod.POST)
	@ResponseBody
	public OpinionModel selectOpinions() {
		OpinionModel model = new OpinionModel();
		List<OpinionBean> opinionBeans = opinionService.selectOpinions();
		model.setCode(ConstantUtil.CODE_FLAG_ZERO);
		model.setMessage(ConstantUtil.SUCCESSFUL);
		model.setResult(opinionBeans);
		return model;
	}
}
