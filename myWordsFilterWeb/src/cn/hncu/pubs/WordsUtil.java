package cn.hncu.pubs;

import java.util.ArrayList;
import java.util.List;

public class WordsUtil {
	private static List<String> list = new ArrayList<String>();
	static{
		//这里应该从数据库中导入敏感词的，我在这里就直接用词来模拟了
		list.add("骂人");
		list.add("sb");
	}
	public static List<String> getWords(){
		return list;
	}
	public static void reBuild(){
	   //把list中的内容存储到数据库---每一段时间存储一次
	}
}
