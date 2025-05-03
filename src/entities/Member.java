package entities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Member {
	public int id;
	public String code;
	public String fname;
	public String lname;
	public String address;
	public String phone;
	public String email;

	public Member(){
		
	}
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
	
	public Member createFromString(String m){
		Document a = Jsoup.parse(m);
		Member b = new Member();
		b.id = Integer.valueOf(a.getElementById("id").text());
		b.code = a.getElementById("code").text();
		b.address = a.getElementById("address").text();
		b.fname = a.getElementById("fname").text();
		b.lname = a.getElementById("lname").text();
		b.email = a.getElementById("email").text();
		b.phone = a.getElementById("phone").text();
		return b;
	}
	public String toXMLString(){
		String out = "<xml>"
                + "<a id=\"id\">" + this.id + "</a>"
                + "<a id=\"code\">" + this.code + "</a>"
                + "<a id=\"address\">" + this.address + "</a>"
                + "<a id=\"fname\">" + this.fname + "</a>"
                + "<a id=\"lname\">" + this.lname + "</a>"
                + "<a id=\"email\">" + this.email + "</a>"
                + "<a id=\"phone\">" + this.phone + "</a>"
                + "</xml>";
		return out;
	}
	
}
