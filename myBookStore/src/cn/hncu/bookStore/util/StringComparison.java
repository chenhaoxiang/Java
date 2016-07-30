package cn.hncu.bookStore.util;

/**
 * 工具类
 * 字符串比较
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class StringComparison {
	
	/**
	 * str1和str2完全(精确查找)匹配
	 * 这个精确匹配是在str2不为null且str2去掉2端空格的情况下比较的！！！
	 * @param str1---被比较的字符串
	 * @param str2---比较的字符串
	 * @return---如果2个字符串相同或者str2全部是空格或者str2==null，就返回true，如果2个字符串不同，就返回false
	 */
	public static boolean stringEquals(String str1,String str2){
		if(str2==null || str2.trim().length()<=0){
			return true;
		}
		if(!str1.equals(str2.trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * str1与str2模糊匹配
	 * 这个模糊匹配也是在str2不为null且str2去掉2端空格的情况下比较的！！！
	 * @param str1---被比较的字符串
	 * @param str2---比较的字符串
	 * @return---如果str2是str1的子串或者str2全部是空格或str2==null，就返回true，如果str2不是str1的字串，就返回false
	 */
	public static boolean stringIndexOf(String str1,String str2){
		if(str2==null || str2.trim().length()<=0){
			return true;
		}
		
		if(str1.indexOf(str2.trim())==-1){
			return false;
		}
		return true;
	}
	
}
