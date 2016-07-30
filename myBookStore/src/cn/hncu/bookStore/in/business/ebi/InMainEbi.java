package cn.hncu.bookStore.in.business.ebi;

import java.util.List;
import java.util.Map;

import cn.hncu.bookStore.in.vo.InDetailModel;
import cn.hncu.bookStore.in.vo.InDetailQueryModel;
import cn.hncu.bookStore.in.vo.InMainModel;
import cn.hncu.bookStore.in.vo.InMainQueryModel;

/**
 * 
 * @author 陈浩翔
 * @version 1.0
 */
public interface InMainEbi {
	
	/**
	 * 创建一个进货模块数据-
	 * @param inMain---进货数据
	 * @param inDetails---进货明细数据
	 * @return---返回true表示创建成功，false表示创建失败
	 */
	public abstract boolean create(InMainModel inMain,List<InDetailModel> inDetails);
	
	/**
	 * 
	 * @return---返回所有的进货模块（包括进货明细）的集合
	 */
	public abstract Map<InMainModel, List<InDetailModel>> getAll();
	
	/**
	 * 
	 * @param imqm---进货管理需要查找的条件
	 * @param idqm---进货明细需要查找的条件
	 * @return---满足条件的所有进货数据的Map集合
	 */
	public abstract Map<InMainModel, List<InDetailModel>> getByCondition(InMainQueryModel imqm,InDetailQueryModel idqm);
	
}
