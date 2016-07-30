package cn.hncu.bookStore.common.uuid.dao.impl;

import java.util.List;

import cn.hncu.bookStore.common.UuidModelConstance;
import cn.hncu.bookStore.common.uuid.dao.dao.UuidDao;
import cn.hncu.bookStore.common.uuid.vo.UuidModel;
import cn.hncu.bookStore.util.FileIoUtil;
/**
 * uuid的具体实现类：采用唱票模式
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class UuidDaoSerImpl implements UuidDao {
	
	private final String FILE_NAME = "Uuid.txt";
	
	@Override
	public String getNextUuid(UuidModelConstance uuidEnum) {
		String modelName = uuidEnum.getName();
		
		//1反序列化，把所有的记录读取出来
		List<UuidModel> lists = FileIoUtil.readFormFile(FILE_NAME);
		
		//2遍历，找当前modelName所对应的 uuid
		for(UuidModel list : lists){
			if(list.getModelName().equals(modelName)){
				//3把当前的uuid返回出去，同时把uuid+1存储到数据库中
				int result = list.getCurrentNum();
				list.setCurrentNum(list.getCurrentNum()+1);
				FileIoUtil.write2file(lists, FILE_NAME);
				return String.valueOf(result);
			}
		}
		//4若数据库中不存在该modelName所对应的uuid，则新建一条记录(类似添加模块的功能),存储且返回1
		int result =1;
		UuidModel uuid = new UuidModel();
		uuid.setModelName(modelName);
		uuid.setCurrentNum(result+1);
		lists.add(uuid);
		FileIoUtil.write2file(lists, FILE_NAME);
		return String.valueOf(result);
	}

}
