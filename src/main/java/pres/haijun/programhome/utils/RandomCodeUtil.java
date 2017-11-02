package pres.haijun.programhome.utils;

import java.util.UUID;
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
 * 			  Create HaiJun By 2017年11月2日
 *
 */
public class RandomCodeUtil {

	/**
	 * 创建用户id
	 * @param userPhone
	 * @return
	 */
	public static String createCode(String userPhone) {
		String userId = UUID.randomUUID().toString().replace("-", "");
		return userId;
	}

}
