package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.Position;
import utils.DBConnect;
import entities.Staff;

public class StaffService {
	public DBConnect dbc;
	public StaffService(DBConnect c){
		this.dbc = c;
	}
	public StaffService(){
		this.dbc = new DBConnect();
	}
	public List<Staff> gets(String ext){
		dbc.selectSql("SELECT * FROM staffs" + ext);
		List<Staff> staffs = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				Staff s = new Staff();
				s.setId(dbc.rs.getInt("id"));
				s.setFname(dbc.rs.getString("fname"));
				s.setLname(dbc.rs.getString("lname"));
				s.setPassword(dbc.rs.getString("password"));
				s.setAddress(dbc.rs.getString("address"));
				s.setPhone(dbc.rs.getString("phone"));
				s.setCode(dbc.rs.getString("code"));
				s.setPosition(dbc.rs.getInt("position"));
				s.setPositionLabel(Position.getPosition(s.getPosition()).getLabel());
				staffs.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return staffs;
	}

	public Staff get(int id){
		List<Staff> staffs = this.gets(" WHERE id = " + id);
		if(staffs.size() == 1){
			return staffs.get(0);
		} else{
			return null;
		}
	}

	public Staff authenticate(String u, String p){
		List<Staff> staffs = this.gets(" WHERE code = '" + u + "' AND password = '" + p + "'");
		if(staffs.size() == 1){
			return staffs.get(0);
		} else{
			return null;
		}
	}

	public Staff remoteAuthenticate(String u, String p){
		try{
			return new Staff().createFromString("");
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public int add(Staff s){
		try{
			dbc.prepareSelect("INSERT INTO `staffs`(`fname`,`lname`,`password`,`address`,`phone`,`email`,`code`,`position`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			dbc.pstm.setString(1, s.getFname());
			dbc.pstm.setString(2, s.getLname());
			dbc.pstm.setString(3, s.getPassword());
			dbc.pstm.setString(4, s.getAddress());
			dbc.pstm.setString(5, s.getPhone());
			dbc.pstm.setString(6, s.getEmail());
			dbc.pstm.setString(7, s.getCode());
			dbc.pstm.setInt(9, s.getPosition());
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Staff s){
		try{
			dbc.prepareSelect("UPDATE `staffs` SET `fname` = ?, `lname` = ?, `password` = ?, `address` = ?,`phone` = ?, `email` = ?, `code` = ?, `position` = ?");
			dbc.pstm.setString(1, s.getFname());
			dbc.pstm.setString(2, s.getLname());
			dbc.pstm.setString(3, s.getPassword());
			dbc.pstm.setString(4, s.getAddress());
			dbc.pstm.setString(5, s.getPhone());
			dbc.pstm.setString(6, s.getEmail());
			dbc.pstm.setString(7, s.getCode());
			dbc.pstm.setInt(8, s.getPosition());
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}
