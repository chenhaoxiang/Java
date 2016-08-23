package cn.hncu.lib;

import org.apache.log4j.Logger;
/**
 * @author 陈浩翔
 *
 * 2016-8-23
 */
public class Log4jDemo {
	public static void main(String[] args) {
		//日志的名字是传入的任何字符串，通常为类名或包名
		Logger log = Logger.getLogger(Log4jDemo.class);
		
		for(int i=0;i<10;i++){
			log.fatal("严重错误信息:"+i);
			log.error("错误信息:"+i);
			log.warn("警告信息:"+i);
			log.info("一般信息:"+i);
			log.debug("调试信息:"+i);
		}
	}
}
