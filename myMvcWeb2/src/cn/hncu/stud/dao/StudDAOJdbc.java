package cn.hncu.stud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.hncu.domain.Stud;
import cn.hncu.pubs.ConnsUtil;

public class StudDAOJdbc implements StudDAO {

	@Override
	public List<Map<String, String>> query() {
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Connection con = null;
		
		try {
			con = ConnsUtil.getConnection();
			String sql="select * from stud";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("name", rs.getString("name"));
				
				//一个map就是一条记录(数据行)
				list.add(map);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					throw new RuntimeException("数据库连接关闭失败", e);
				}
			}
			
		}
		return list;
	}

	@Override
	public void save(Stud stud) throws InterruptedException, SQLException {
		Connection con = ConnsUtil.getConnection();
		String sql = "insert into stud values(?,?)";
		
		PreparedStatement pst = con.prepareStatement(sql);
		
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		pst.setString(1, uuid);
		pst.setString(2, stud.getName());
		
		pst.executeUpdate();
		
		//在这里为book补外键字段的值
		stud.setId(uuid);
		//这一句执行在前，这样在BookDAOJdbc中会调用book.getStud().getId()就可以拿到该id值了
	}

}
