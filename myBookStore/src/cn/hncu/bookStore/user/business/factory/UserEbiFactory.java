package cn.hncu.bookStore.user.business.factory;

import cn.hncu.bookStore.user.business.ebi.UserEbi;
import cn.hncu.bookStore.user.business.ebo.UserEbo;
/**
 * 
 * @author ³ÂºÆÏè
 *
 * @version 1.0
 */
public class UserEbiFactory {
	public static UserEbi getUserEbi(){
		return new UserEbo();
	}
}
