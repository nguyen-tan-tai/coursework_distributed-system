package services;

import utils.DBConnect;

public class InfoService {
	public String gets(String key){
		DBConnect dbc = new DBConnect();
		dbc.selectSql("SELECT * FROM info WHERE `key` = '" + key + "';");
		String out = "";
		try {
			while(dbc.rs.next()){
				out = dbc.rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return out;
	}
}
