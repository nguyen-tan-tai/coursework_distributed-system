package entities;

import java.util.Date;

public class FoodBeverageOrder {
	public int id;
	public FoodBeverage fb;
	public Member member;
	public Staff staff;
	public Date sellTime;
	public float quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public FoodBeverage getFb() {
		return fb;
	}
	public void setFb(FoodBeverage fb) {
		this.fb = fb;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Date getSellTime() {
		return sellTime;
	}
	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
}
