package cn.hncu.bookStore.out.business.ebo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.hncu.bookStore.book.business.ebi.BookEbi;
import cn.hncu.bookStore.book.business.factory.BookEbiFactory;
import cn.hncu.bookStore.common.UuidModelConstance;
import cn.hncu.bookStore.common.uuid.dao.dao.UuidDao;
import cn.hncu.bookStore.common.uuid.dao.factory.UuidDaoFactory;
import cn.hncu.bookStore.out.business.ebi.OutMainEbi;
import cn.hncu.bookStore.out.dao.dao.OutDetailDao;
import cn.hncu.bookStore.out.dao.dao.OutMainDao;
import cn.hncu.bookStore.out.dao.factory.OutDetailDaoFactory;
import cn.hncu.bookStore.out.dao.factory.OutMainDaoFactory;
import cn.hncu.bookStore.out.vo.OutDetailModel;
import cn.hncu.bookStore.out.vo.OutDetailQueryModel;
import cn.hncu.bookStore.out.vo.OutMainModel;
import cn.hncu.bookStore.out.vo.OutMainQueryModel;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutMainEbo implements OutMainEbi{
	//注入dao
	
	OutMainDao outMainDao = OutMainDaoFactory.getOutMainDao();
	OutDetailDao outDetailDao = OutDetailDaoFactory.getOutDetailDao();
	UuidDao uuidDao = UuidDaoFactory.getUuidDao();
	BookEbi bookEbi = BookEbiFactory.getBookEbi();
	
	@Override
	public boolean create(OutMainModel outMain, List<OutDetailModel> outDetails) {
		//////////1存储outMain信息///////////
		//补全outMain中的数据
		//需要:inUuid,inDate,inUserUuid   已组织:inUserUuid
		//还缺(需补):inUuid,inDate
		String outUuid = uuidDao.getNextUuid(UuidModelConstance.OUT_MAIN);
		outMain.setUuid(outUuid);
		outMain.setOutDate(System.currentTimeMillis());
		outMainDao.create(outMain);
		
		 //////////2存储inDetail信息///////////
		for(OutDetailModel model:outDetails){
			//补全每一个inDetail中的数据
			//需要:inDetailUuid,outMainUuid,bookUuid,sumNum,sumMoney   已组织:bookUuid,sumNum
	        //还缺(需补):inDetailUuid,outMainUuid,sumMoney
			model.setUuid(uuidDao.getNextUuid(UuidModelConstance.OUT_DETAIL));
			model.setOutId(outUuid);
			
			double sumMoney = model.getSumNum() * bookEbi.getSingle(model.getBookId()).getSalePrice();
			model.setSumMoney(sumMoney);
			outDetailDao.create(model);
		}
		return true;
	}

	@Override
	public Map<OutMainModel, List<OutDetailModel>> getAll() {
		Map<OutMainModel,List<OutDetailModel>> map = new TreeMap<OutMainModel, List<OutDetailModel>>();
		
		List<OutMainModel> outMains = outMainDao.getAll();
		
		for(OutMainModel outMain: outMains ){
			//查询条件值对象的创建
			OutDetailQueryModel odqm = new OutDetailQueryModel();
			String inUuid = outMain.getUuid();
			odqm.setOutId(inUuid);

			List<OutDetailModel> details = outDetailDao.getbyCondition(odqm);
			
			map.put(outMain, details);
		}
		
		return map;
	}

	@Override
	public Map<OutMainModel, List<OutDetailModel>> getByCondition(
			OutMainQueryModel imqm, OutDetailQueryModel odqm) {
		Map<OutMainModel, List<OutDetailModel>> map = new TreeMap<OutMainModel, List<OutDetailModel>>();
		
		List<OutMainModel> list = outMainDao.getbyCondition(imqm);
		
		for(OutMainModel outMain : list){
			odqm.setOutId(outMain.getUuid());
			
			List<OutDetailModel> details = outDetailDao.getbyCondition(odqm);
			if(details.size()!=0){
				map.put(outMain, details);
			}
		}
		
		return map;
	}

}
