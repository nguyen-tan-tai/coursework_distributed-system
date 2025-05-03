package services;

import java.util.ArrayList;
import java.util.List;

import utils.DBConnect;
import entities.Activity;

public class ActivityService {
	public List<Activity> gets(String ext){
		DBConnect dbc = new DBConnect();
		dbc.selectSql("SELECT * FROM activities" + ext);
		List<Activity> acts = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				Activity a = new Activity();
				a.id = dbc.rs.getInt("id");
				a.name = dbc.rs.getString("aname");
				a.type = dbc.rs.getString("atype");
				a.price = dbc.rs.getFloat("price");
				acts.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return acts;
	}
	
	public Activity get(int id){
		List<Activity> acts = this.gets(" WHERE id = " + id);
		if(acts.size() == 1){
			return acts.get(0);
		} else{
			return null;
		}
	}

}
