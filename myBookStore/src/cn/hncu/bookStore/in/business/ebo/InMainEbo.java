package cn.hncu.bookStore.in.business.ebo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.hncu.bookStore.book.business.ebi.BookEbi;
import cn.hncu.bookStore.book.business.factory.BookEbiFactory;
import cn.hncu.bookStore.common.UuidModelConstance;
import cn.hncu.bookStore.common.uuid.dao.dao.UuidDao;
import cn.hncu.bookStore.common.uuid.dao.factory.UuidDaoFactory;
import cn.hncu.bookStore.in.business.ebi.InMainEbi;
import cn.hncu.bookStore.in.business.factory.InMainEbiFactory;
import cn.hncu.bookStore.in.dao.dao.InDetailDao;
import cn.hncu.bookStore.in.dao.dao.InMainDao;
import cn.hncu.bookStore.in.dao.factory.InDetailDaoFactory;
import cn.hncu.bookStore.in.dao.factory.InMainDaoFactory;
import cn.hncu.bookStore.in.vo.InDetailModel;
import cn.hncu.bookStore.in.vo.InDetailQueryModel;
import cn.hncu.bookStore.in.vo.InMainModel;
import cn.hncu.bookStore.in.vo.InMainQueryModel;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InMainEbo implements InMainEbi{
	//注入dao
	
	InMainDao inMainDao = InMainDaoFactory.getInMainDao();
	InDetailDao inDetailDao = InDetailDaoFactory.getInDetailDao();
	UuidDao uuidDao = UuidDaoFactory.getUuidDao();
	BookEbi bookEbi = BookEbiFactory.getBookEbi();
	
	@Override
	public boolean create(InMainModel inMain, List<InDetailModel> inDetails) {
		//////////1存储inMain信息///////////
		//补全inMain中的数据
		//需要:inUuid,inDate,inUserUuid   已组织:inUserUuid
		//还缺(需补):inUuid,inDate
		String inUuid = uuidDao.getNextUuid(UuidModelConstance.IN_MAIN);
		inMain.setUuid(inUuid);
		inMain.setInDate(System.currentTimeMillis());
		inMainDao.create(inMain);
		
		 //////////2存储inDetail信息///////////
		for(InDetailModel model:inDetails){
			//补全每一个inDetail中的数据
			//需要:inDetailUuid,inMainUuid,bookUuid,sumNum,sumMoney   已组织:bookUuid,sumNum
	        //还缺(需补):inDetailUuid,inMainUuid,sumMoney
			model.setUuid(uuidDao.getNextUuid(UuidModelConstance.IN_DETAIL));
			model.setInId(inUuid);
			
			double sumMoney = model.getSumNum() * bookEbi.getSingle(model.getBookId()).getInPrice();
			model.setSumMoney(sumMoney);
			inDetailDao.create(model);
		}
		return true;
	}

	@Override
	public Map<InMainModel, List<InDetailModel>> getAll() {
		Map<InMainModel,List<InDetailModel>> map = new TreeMap<InMainModel, List<InDetailModel>>();
		
		List<InMainModel> inMains = inMainDao.getAll();
		
		for(InMainModel inMain: inMains ){
			//查询条件值对象的创建
			InDetailQueryModel idqm = new InDetailQueryModel();
			String inUuid = inMain.getUuid();
			idqm.setInId(inUuid);

			List<InDetailModel> details = inDetailDao.getbyCondition(idqm);
			
			map.put(inMain, details);
		}
		
		return map;
	}

	@Override
	public Map<InMainModel, List<InDetailModel>> getByCondition(
			InMainQueryModel imqm, InDetailQueryModel idqm) {
		Map<InMainModel, List<InDetailModel>> map = new TreeMap<InMainModel, List<InDetailModel>>();
		
		List<InMainModel> list = inMainDao.getbyCondition(imqm);
		
		for(InMainModel inMain : list){
			idqm.setInId(inMain.getUuid());
			
			List<InDetailModel> details = inDetailDao.getbyCondition(idqm);
			if(details.size()!=0){
				map.put(inMain, details);
			}
		}
		
		return map;
	}

}
