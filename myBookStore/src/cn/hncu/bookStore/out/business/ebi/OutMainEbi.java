package cn.hncu.bookStore.out.business.ebi;

import java.util.List;
import java.util.Map;

import cn.hncu.bookStore.out.vo.OutMainQueryModel;
import cn.hncu.bookStore.out.vo.OutDetailModel;
import cn.hncu.bookStore.out.vo.OutDetailQueryModel;
import cn.hncu.bookStore.out.vo.OutMainModel;

/**
 * 
 * @author 陈浩翔
 * @version 1.0
 */
public interface OutMainEbi {
	
	/**
	 * 创建一个销售模块数据-
	 * @param inMain---销售数据
	 * @param outDetails---销售明细数据
	 * @return---返回true表示创建成功，false表示创建失败
	 */
	public abstract boolean create(OutMainModel inMain,List<OutDetailModel> outDetails);
	
	/**
	 * 
	 * @return---返回所有的销售模块（包括销售明细）的集合
	 */
	public abstract Map<OutMainModel, List<OutDetailModel>> getAll();
	
	/**
	 * 
	 * @param omqm---销售管理需要查找的条件
	 * @param idqm---销售明细需要查找的条件
	 * @return---满足条件的所有销售数据的Map集合
	 */
	public abstract Map<OutMainModel, List<OutDetailModel>> getByCondition(OutMainQueryModel omqm,OutDetailQueryModel odqm);
	
}
