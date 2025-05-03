package services;

import entities.Staff;

public class LoginService {

	public Staff login(String username, String password){
		if(username == null){
			return null;
		}
		return localAuth(username, password);
	}

	public Staff localAuth(String username, String password){
		StaffService ss = new StaffService();
		return ss.authenticate(username, password);
	}

	public Staff remoteAuth(String username, String password){
		Staff staff = null;
		try{
			StaffService ss = new StaffService();
			return ss.remoteAuthenticate(username, password);
		} catch(Exception e){
			e.printStackTrace();
		}
		return staff;
	}
}
