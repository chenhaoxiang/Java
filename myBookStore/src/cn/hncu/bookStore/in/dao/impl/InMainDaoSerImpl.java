package cn.hncu.bookStore.in.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hncu.bookStore.in.dao.dao.InMainDao;
import cn.hncu.bookStore.in.vo.InMainModel;
import cn.hncu.bookStore.in.vo.InMainQueryModel;
import cn.hncu.bookStore.util.FileIoUtil;
import cn.hncu.bookStore.util.StringComparison;

/**
 * 进货管理的实现类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InMainDaoSerImpl implements InMainDao {
	
	private final String FILE_NAME = "InMain.txt";
	@Override
	public boolean create(InMainModel inMain) {
		List<InMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(InMainModel model:list){
			if(model.getUuid().equals(inMain.getUuid())){//存在，则不能添加
				return false;
			}
		}
		list.add(inMain);
		FileIoUtil.write2file(list, FILE_NAME);
		return true;
	}

	@Override
	public boolean delete(String uuid) {
		List<InMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(InMainModel model:list){
			if(model.getUuid().equals(uuid)){//存在，则删除
				list.remove(model);
				FileIoUtil.write2file(list, FILE_NAME);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(InMainModel inMain) {
		List<InMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(int i=0;i<list.size();i++){
			InMainModel model = list.get(i);
			if(model.getUuid().equals(inMain.getUuid())){//存在，则修改
				list.set(i, inMain);
				FileIoUtil.write2file(list, FILE_NAME);
				return true;
			}
		}
		return false;
	}

	@Override
	public InMainModel getSingle(String uuid) {
		List<InMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(InMainModel model:list){
			if(model.getUuid().equals(uuid)){//存在，则返回
				return model;
			}
		}
		return null;
	}

	@Override
	public List<InMainModel> getAll() {
		return FileIoUtil.readFormFile(FILE_NAME);
	}

	@Override
	public List<InMainModel> getbyCondition(InMainQueryModel imqm) {
		List<InMainModel> lists = FileIoUtil.readFormFile(FILE_NAME);
		List<InMainModel> results = new ArrayList<InMainModel>();
		for(InMainModel model : lists){
			
			
			
			if(!StringComparison.stringEquals( model.getUuid(),imqm.getUuid())){
				continue;
			}
			
			if(!StringComparison.stringEquals(model.getInUserId(), imqm.getInUserId())){
				continue;
			}
			if(imqm.getInDate()>0){
				if(model.getInDate()<imqm.getInDate()){
					continue;
				}
			}
			if(imqm.getInDate2()>0){
				if(model.getInDate()>imqm.getInDate2()){
					continue;
				}
			}
			results.add(model);
		}
		return results;
	}

}
