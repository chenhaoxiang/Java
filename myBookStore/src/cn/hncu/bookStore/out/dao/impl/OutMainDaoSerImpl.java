package cn.hncu.bookStore.out.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hncu.bookStore.out.dao.dao.OutMainDao;
import cn.hncu.bookStore.out.vo.OutMainModel;
import cn.hncu.bookStore.out.vo.OutMainQueryModel;
import cn.hncu.bookStore.util.FileIoUtil;
import cn.hncu.bookStore.util.StringComparison;

/**
 * 销售管理的实现类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutMainDaoSerImpl implements OutMainDao {
	
	private final String FILE_NAME = "OutMain.txt";
	@Override
	public boolean create(OutMainModel outMain) {
		List<OutMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(OutMainModel model:list){
			if(model.getUuid().equals(outMain.getUuid())){//存在，则不能添加
				return false;
			}
		}
		list.add(outMain);
		FileIoUtil.write2file(list, FILE_NAME);
		return true;
	}

	@Override
	public boolean delete(String uuid) {
		List<OutMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(OutMainModel model:list){
			if(model.getUuid().equals(uuid)){//存在，则删除
				list.remove(model);
				FileIoUtil.write2file(list, FILE_NAME);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(OutMainModel outMain) {
		List<OutMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(int i=0;i<list.size();i++){
			OutMainModel model = list.get(i);
			if(model.getUuid().equals(outMain.getUuid())){//存在，则修改
				list.set(i, outMain);
				FileIoUtil.write2file(list, FILE_NAME);
				return true;
			}
		}
		return false;
	}

	@Override
	public OutMainModel getSingle(String uuid) {
		List<OutMainModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(OutMainModel model:list){
			if(model.getUuid().equals(uuid)){//存在，则返回
				return model;
			}
		}
		return null;
	}

	@Override
	public List<OutMainModel> getAll() {
		return FileIoUtil.readFormFile(FILE_NAME);
	}

	@Override
	public List<OutMainModel> getbyCondition(OutMainQueryModel omqm) {
		List<OutMainModel> lists = FileIoUtil.readFormFile(FILE_NAME);
		List<OutMainModel> results = new ArrayList<OutMainModel>();
		for(OutMainModel model : lists){
			
			
			
			if(!StringComparison.stringEquals( model.getUuid(),omqm.getUuid())){
				continue;
			}
			
			if(!StringComparison.stringEquals(model.getOutUserId(), omqm.getOutUserId())){
				continue;
			}
			if(omqm.getOutDate()>0){
				if(model.getOutDate()<omqm.getOutDate()){
					continue;
				}
			}
			if(omqm.getOutDate2()>0){
				if(model.getOutDate()>omqm.getOutDate2()){
					continue;
				}
			}
			results.add(model);
		}
		return results;
	}

}
