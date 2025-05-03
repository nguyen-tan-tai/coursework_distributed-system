package entities;

import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dto.Position;

public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public String code;
	public String fname;
	public String password;
	public String lname;
	public String address;
	public String phone;
	public String email;
	public int position;
	public String positionLabel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getPositionLabel() {
		return positionLabel;
	}
	public void setPositionLabel(String positionLabel) {
		this.positionLabel = positionLabel;
	}
	
	public Staff createFromString(String m){
		Document a = Jsoup.parse(m);
		if(a.getElementById("valid").text().equals("null")){
			return null;
		} else{
			Staff b = new Staff();
			b.id = Integer.valueOf(a.getElementById("id").text());
			b.code = a.getElementById("code").text();
			b.address = a.getElementById("address").text();
			b.fname = a.getElementById("fname").text();
			b.lname = a.getElementById("lname").text();
			b.email = a.getElementById("email").text();
			b.phone = a.getElementById("phone").text();
			b.password = a.getElementById("password").text();
			b.position = Integer.valueOf(a.getElementById("position").text());
			b.setPositionLabel(Position.getPosition(b.getPosition()).getLabel());
			return b;
		}
	}
	public String toString(){
		String out = "<xml>"
                + "<a id=\"valid\"></a>"
                + "<a id=\"id\">" + this.id + "</a>"
                + "<a id=\"code\">" + this.code + "</a>"
                + "<a id=\"address\">" + this.address + "</a>"
                + "<a id=\"fname\">" + this.fname + "</a>"
                + "<a id=\"lname\">" + this.lname + "</a>"
                + "<a id=\"email\">" + this.email + "</a>"
                + "<a id=\"phone\">" + this.phone + "</a>"
                + "<a id=\"password\">" + this.password + "</a>"
                + "<a id=\"position\">" + this.position + "</a>"
                + "<a id=\"positionLabel\">" + this.positionLabel + "</a>"
                + "</xml>";
		return out;
	}
	
}
