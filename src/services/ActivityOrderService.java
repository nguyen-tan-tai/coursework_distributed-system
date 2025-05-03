package services;

import java.util.ArrayList;
import java.util.List;

import utils.DBConnect;
import utils.Utils;
import entities.ActivityOrder;

public class ActivityOrderService {

	public List<ActivityOrder> gets(String ext){
		DBConnect dbc = new DBConnect();
		dbc.selectSql("SELECT * FROM activity_order" + ext);
		List<ActivityOrder> ao = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				ActivityOrder a = new ActivityOrder();
				a.id = dbc.rs.getInt("id");
				a.activity = new ActivityService().get(dbc.rs.getInt("activity"));
				a.staff = new StaffService().get(dbc.rs.getInt("staff"));
				a.member = new MemberService().get(dbc.rs.getInt("member"));
				a.bookTime= Utils.s2D(dbc.rs.getString("book_time"));
				a.startTime = Utils.s2D(dbc.rs.getString("start_time")); 
				a.endTime = Utils.s2D(dbc.rs.getString("end_time"));
				a.quantity = dbc.rs.getFloat("quantity");
				ao.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return ao;
	}
	
	public ActivityOrder get(int id){
		List<ActivityOrder> ao = this.gets(" WHERE id = " + id);
		if(ao.size() == 1){
			return ao.get(0);
		} else{
			return null;
		}
	}
	
	public int add(ActivityOrder s){
		DBConnect dbc = new DBConnect();
		try{
			dbc.prepareSelect("INSERT INTO `activity_order`(`activity`,`staff`,`member`,`book_time`,`start_time`,`end_time`,`quantity`) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?);");
			dbc.pstm.setInt(1, s.activity.id);
			dbc.pstm.setInt(2, s.staff.getId());
			dbc.pstm.setInt(3, s.member.getId());
			dbc.pstm.setString(4, Utils.d2S(s.getBookTime()));
			dbc.pstm.setString(5,  Utils.d2S(s.getStartTime()));
			dbc.pstm.setString(6,  Utils.d2S(s.getEndTime()));
			dbc.pstm.setFloat(7,  s.getQuantity());
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(ActivityOrder s){
		DBConnect dbc = new DBConnect();
		try{
			dbc.prepareSelect("UPDATE `activity_order` SET `activity` = ?, `staff` = ?, `member` = ?, `book_time` = ?," +
					" `start_time` = ?, `end_time` = ?, `quantity` = ? WHERE `id` = ?;");
			dbc.pstm.setInt(1, s.activity.id);
			dbc.pstm.setInt(2, s.staff.getId());
			dbc.pstm.setInt(3, s.member.getId());
			dbc.pstm.setString(4, Utils.d2S(s.getBookTime()));
			dbc.pstm.setString(5,  Utils.d2S(s.getStartTime()));
			dbc.pstm.setString(6,  Utils.d2S(s.getEndTime()));
			dbc.pstm.setFloat(7,  s.getQuantity());
			dbc.pstm.setInt(8, s.id);
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(int id) {
		DBConnect dbc = new DBConnect();
		try{
			dbc.prepareSelect("DELETE FROM `activity_order` WHERE `id` = ?;");
			dbc.pstm.setInt(1, id);
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
