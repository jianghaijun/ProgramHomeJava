package pres.haijun.programhome.utils;

public class TextUtil {
	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if("".equals(s) || s == null) {
			return true;
		} else {
			return false;
		}
	}
}
