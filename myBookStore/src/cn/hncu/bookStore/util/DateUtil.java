package cn.hncu.bookStore.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * 日期工具类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class DateUtil {
	/**
	 * 根据传入的long参数 ，把long值转换为固定的年月日格式输出
	 * @param d---传入的参数
	 * @return---一个字符串参数，格式为：yyyy年MM月dd日 HH:mm:ss
	 */
	public static String long2String(long d){
		Date date = new Date(d);
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String str = df.format(date);
		return str;
	}
	/*
	 * 代码抽取技术：
	 *   首先不要想“抽出的方法”怎么写，而是把类似的代码拷在一起，观察其中的变化部分和不变化部分。
	 *   把这段代码中用到的“前面定义的变量”抽取成方法的参数--本例中为txtInDate和erroInfo，把“留给后面使用的”将在这段代码中新
	 *   创建的变量定义成方法的返回值---本例为inDate。
	 */
	
	
	/**
	 * 根据传入的日期格式，把String型的参数转换成long型参数返回<br/>
	 * 如果格式传入错误，会根据传入的erroInfo字符串弹出窗口给出提示！ 
	 * @param txtInDate---传入的日期。
	 * @param erroInfo----传入的错误提示信息
	 * @return---long型的数字，如果格式转换错误，返回-1；
	 */
	public static long string2Long(String txtInDate,String erroInfo){
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long inDate=0;
		try {
			Date d = date.parse(txtInDate);
			inDate = d.getTime();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, erroInfo);
			return -1;
		}
		return inDate;
	}
	
}
