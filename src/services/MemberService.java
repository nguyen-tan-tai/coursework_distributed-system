package services;

import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import entities.Member;

public class MemberService {
	public DBConnect dbc;
	public MemberService(DBConnect c){
		this.dbc = c;
	}
	public MemberService(){
		this.dbc = new DBConnect();
	}

	public List<Member> gets(String ext){
		dbc.selectSql("SELECT * FROM members" + ext);
		List<Member> members = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				Member m = new Member();
				m.setId(dbc.rs.getInt("id"));
				m.setFname(dbc.rs.getString("fname"));
				m.setLname(dbc.rs.getString("lname"));
				m.setAddress(dbc.rs.getString("address"));
				m.setPhone(dbc.rs.getString("phone"));
				m.setEmail(dbc.rs.getString("email"));
				m.setCode(dbc.rs.getString("code"));
				members.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return members;
	}

	public Member get(int id){
		List<Member> members = this.gets(" WHERE id = " + id);
		if(members.size() == 1){
			return members.get(0);
		} else{
			return null;
		}
	}

	public int add(Member m){
		try{
			dbc.prepareSelect("INSERT INTO `members`(`fname`,`lname`,`code`,`address`,`phone`,`email`) VALUES (?, ?, ?, ?, ?, ?);");
			dbc.pstm.setString(1, m.getFname());
			dbc.pstm.setString(2, m.getLname());
			dbc.pstm.setString(3, m.getCode());
			dbc.pstm.setString(4, m.getAddress());
			dbc.pstm.setString(5, m.getPhone());
			dbc.pstm.setString(6, m.getEmail());
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Member m){
		try{
			dbc.prepareSelect("UPDATE `members` SET `fname` = ?, `lname` = ?, `code` = ?, `address` = ?,`phone` = ?, `email` = ? WHERE `id` = ?;");
			System.out.println("UPDATE `members` SET `fname` = ?, `lname` = ?, `code` = ?, `address` = ?,`phone` = ?, `email` = ? WHERE `id` = ?;");
			dbc.pstm.setString(1, m.getFname());
			dbc.pstm.setString(2, m.getLname());
			dbc.pstm.setString(3, m.getCode());
			dbc.pstm.setString(4, m.getAddress());
			dbc.pstm.setString(5, m.getPhone());
			dbc.pstm.setString(6, m.getEmail());
			dbc.pstm.setInt(7, m.getId());
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(int id){
		try{
			dbc.prepareSelect("DELETE FROM `members` WHERE `id` = ?;");
			dbc.pstm.setInt(1, id);
			return dbc.pstm.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public Member localCheck(String code) {
		List<Member> lm = this.gets(" WHERE `code` = '" + code + "';");
		if(lm.size() == 1){
			return lm.get(0);
		} else{
			return null;
		}
	}

	public Member check(String code){
		InfoService is = new InfoService();
		String center = is.gets("center");
		if(code.substring(0, 7).equals(center)){//Same as local
			Member m = localCheck(code);
			if(m == null){
				m = new Member();
				m.setCode("not-found");
				m.setAddress("User not found in local database");
			}
			return m;
		} else{
			return remoteCheck(code, code.substring(0, 7));
		}
	}

	public Member remoteCheck(String mcode, String ccode){
		Member m = new Member();
		try{
			String x = "";
			return new Member().createFromString(x);
		} catch(Exception e){
			e.printStackTrace();
		}
		return m;
	}
}
