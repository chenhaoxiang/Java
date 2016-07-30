package cn.hncu.bookStore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 用户的公用数据读取写入类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class FileIoUtil {

	public FileIoUtil() {
	}
	
	
	/**
	 *  从数据库中读取所有的数据并返回出来
	 *  
	 * @param fileName：(数据表对应的文件名字)
	 * @return 所有表的记录！
	 */
	@SuppressWarnings("unchecked")//压警告
	public static<E> List<E> readFormFile(String fileName){
		List<E> list = new ArrayList<E>();
		final File file = new File(fileName);
		
		ObjectInputStream in =null;
		if(!file.exists()){
			//JOptionPane.showMessageDialog(null, "数据表不存在！");
			return list;
		}
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			try {
				list = (List<E>) in.readObject();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException("数据库关闭失败");
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 写入一个list集合进入数据文件fileName
	 * 
	 * @param list(需要存储的数据集合)
	 * @param fileName(写入到哪个文件的文件名字)
	 */
	public static<E> void write2file(List<E> list, String fileName){
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException("数据库关闭失败!");
				}
			}
		}
	}
	
}
