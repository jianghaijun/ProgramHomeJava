package pres.haijun.programhome.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pres.haijun.programhome.bean.OpinionBean;
import pres.haijun.programhome.dao.OpinionDao;
import pres.haijun.programhome.service.OpinionService;
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
@Service
public class OpinionServiceImpl implements OpinionService{
	@Resource
	private OpinionDao opinionDao;
	
	@Override
	public List<OpinionBean> selectOpinions() {
		return opinionDao.selectOpinions();
	}

	@Override
	public int insertOpinion(OpinionBean opinionBean) {
		opinionBean.setSubmitTime(new Date());
		return opinionDao.insertOpinion(opinionBean);
	}

}
