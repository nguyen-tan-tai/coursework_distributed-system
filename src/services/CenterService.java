package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnect;
import entities.Center;

public class CenterService {
	public List<Center> gets(String ext){
		DBConnect dbc = new DBConnect();
		dbc.selectSql("SELECT * FROM centers" + ext);
		List<Center> centers = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				Center c = new Center();
				c.setId(dbc.rs.getInt("id"));
				c.setCode(dbc.rs.getString("code"));
				c.setName(dbc.rs.getString("name"));
				c.setAddress(dbc.rs.getString("address"));
				c.setPhone(dbc.rs.getString("phone"));
				c.setIp(dbc.rs.getString("ip"));
				c.setDn(dbc.rs.getString("dn"));
				c.setDu(dbc.rs.getString("du"));
				c.setDp(dbc.rs.getString("dp"));
				centers.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return centers;
	}
	
	public Center get(int id){
		List<Center> centers = this.gets(" WHERE id = " + id);
		if(centers.size() == 1){
			return centers.get(0);
		} else{
			return null;
		}
	}
}
