package services;

import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import utils.Utils;
import entities.FoodBeverageOrder;

public class FoodBeverageOrderService {

	public List<FoodBeverageOrder> gets(String ext){
		DBConnect dbc = new DBConnect();
		dbc.selectSql("SELECT * FROM food_beverage_order" + ext);
		List<FoodBeverageOrder> ao = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				FoodBeverageOrder a = new FoodBeverageOrder();
				a.id = dbc.rs.getInt("id");
				a.fb = new FoodBeverageService().get(dbc.rs.getInt("foot_beverage"));
				a.staff = new StaffService().get(dbc.rs.getInt("staff"));
				a.member = new MemberService().get(dbc.rs.getInt("member"));
				a.sellTime = Utils.s2D(dbc.rs.getString("sell_time"));
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
	
	public FoodBeverageOrder get(int id){
		List<FoodBeverageOrder> ao = this.gets(" WHERE id = " + id);
		if(ao.size() == 1){
			return ao.get(0);
		} else{
			return null;
		}
	}
	
	public int add(FoodBeverageOrder s){
		DBConnect dbc = new DBConnect();
		try{
			dbc.prepareSelect("INSERT INTO `food_beverage_order`(`foot_beverage`,`staff`,`member`,`sell_time`,`quantity`) " +
					"VALUES (?, ?, ?, ?, ?);");
			dbc.pstm.setInt(1, s.fb.id);
			dbc.pstm.setInt(2, s.staff.getId());
			dbc.pstm.setInt(3, s.member.getId());
			dbc.pstm.setString(4,  Utils.d2S(s.getSellTime()));
			dbc.pstm.setFloat(5,  s.getQuantity());
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(FoodBeverageOrder s){
		DBConnect dbc = new DBConnect();
		try{
			dbc.prepareSelect("UPDATE `food_beverage_order` SET `foot_beverage` = ?, `staff` = ?, `member` = ?, `sell_time` = ?," +
					" `quantity` = ? WHERE `id` = ?;");
			dbc.pstm.setInt(1, s.fb.id);
			dbc.pstm.setInt(2, s.staff.getId());
			dbc.pstm.setInt(3, s.member.getId());
			dbc.pstm.setString(4, Utils.d2S(s.getSellTime()));
			dbc.pstm.setFloat(5,  s.getQuantity());
			dbc.pstm.setInt(6, s.id);
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(int id) {
		DBConnect dbc = new DBConnect();
		try{
			dbc.prepareSelect("DELETE FROM `food_beverage_order` WHERE `id` = ?;");
			dbc.pstm.setInt(1, id);
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
