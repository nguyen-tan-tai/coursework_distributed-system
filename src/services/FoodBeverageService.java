package services;

import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import entities.FoodBeverage;

public class FoodBeverageService {
	public List<FoodBeverage> gets(String ext){
		DBConnect dbc = new DBConnect();
		dbc.selectSql("SELECT * FROM food_beverage" + ext);
		List<FoodBeverage> acts = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				FoodBeverage a = new FoodBeverage();
				a.id = dbc.rs.getInt("id");
				a.name = dbc.rs.getString("name");
				a.type = dbc.rs.getString("type");
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
	
	public FoodBeverage get(int id){
		List<FoodBeverage> acts = this.gets(" WHERE id = " + id);
		if(acts.size() == 1){
			return acts.get(0);
		} else{
			return null;
		}
	}

}
