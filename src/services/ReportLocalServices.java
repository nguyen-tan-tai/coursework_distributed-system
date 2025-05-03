package services;

import java.util.ArrayList;
import java.util.List;

import utils.DBConnect;
import dto.ActivityByTypeDto;
import dto.LocalStatisticMembershipDto;

public class ReportLocalServices {
	public List<ActivityByTypeDto> getActivityReportByType(String type, int year, int month, int week) {
		String a1 = "", a2 = "", a3 = "";
		String s1 = "", s2 = "", s3 = "";
		
		if(year > 0){
			a1 = " AND YEAR(i.book_time) = " + year;
			s1 = " AND YEAR(i.sell_time) = " + year;
		}
		if(month > 0){
			a2 = " AND MONTH(i.book_time) = " + week;
			s2 = " AND MONTH(i.sell_time) = " + week;
		}
		if(week > 0){
			a3 = " AND ((WEEK(i.book_time,5) - WEEK(DATE_SUB(i.book_time, INTERVAL DAYOFMONTH(i.book_time)-1 DAY),5)+1) = " + week + ")";
			s3 = " AND ((WEEK(i.sell_time,5) - WEEK(DATE_SUB(i.sell_time, INTERVAL DAYOFMONTH(i.sell_time)-1 DAY),5)+1) = " + week + ")";
		}
		String a = "SELECT a.aname AS n, a.atype AS t, SUM(i.quantity) AS q, a.price AS p, SUM(i.quantity) * a.price AS m " +
				"FROM activity_order i, activities a, members m, staffs s " +
				"WHERE i.activity = a.id AND i.member = m.id AND i.staff = s.id";
		String s = "SELECT f.name AS n, f.type AS t, SUM(i.quantity) AS q, f.price AS p, SUM(i.quantity) * f.price AS m " +
				"FROM food_beverage_order i, food_beverage f, members m, staffs s " +
				"WHERE i.foot_beverage = f.id AND i.member = m.id AND i.staff = s.id";
		String query = "";
		if(type.equals("a-n")){
			query = a + a1 + a2 + a3 + " GROUP BY a.aname";
		} else if(type.equals("a-t")){
			query = a + a1 + a2 + a3 + " GROUP BY a.atype";	
		} else if(type.equals("s-n")){
			query = s + s1 + s2 + s3 + " GROUP BY f.name";	
		} else if(type.equals("s-t")){
			query = s + s1 + s2 + s3 + " GROUP BY f.type";	
		}
		System.out.println(query);
		DBConnect dbc = new DBConnect();
		dbc.selectSql(query);
		List<ActivityByTypeDto> ao = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				ActivityByTypeDto r = new ActivityByTypeDto();
				r.n = dbc.rs.getString("n");
				r.t = dbc.rs.getString("t");
				r.q = dbc.rs.getFloat("q");
				r.p = dbc.rs.getFloat("p");
				r.m = dbc.rs.getFloat("m");
				ao.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return ao;
	}
	
	public List<LocalStatisticMembershipDto> getStatistic(int year, int month, int week, int staff, String type, String group){
		String a1 = "", a2 = "", a3 = "";
		String s1 = "", s2 = "", s3 = "", s = "";
		
		if(year > 0){
			a1 = " AND YEAR(ao.book_time) = " + year;
			s1 = " AND YEAR(fo.sell_time) = " + year;
		}
		if(month > 0){
			a2 = " AND MONTH(ao.book_time) = " + month;
			s2 = " AND MONTH(fo.sell_time) = " + week;
		}
		if(week > 0){
			a3 = " AND ((WEEK(ao.book_time,5) - WEEK(DATE_SUB(ao.book_time, INTERVAL DAYOFMONTH(ao.book_time)-1 DAY),5)+1) = " + week + ")";
			s3 = " AND ((WEEK(fo.sell_time,5) - WEEK(DATE_SUB(fo.sell_time, INTERVAL DAYOFMONTH(fo.sell_time)-1 DAY),5)+1) = " + week + ")";
		}
		if(staff > 0){
			s = " AND s.id = " + staff;
		}
		
		String q1 = "SELECT CONCAT(s.fname, ' ', s.lname) AS n, 'Activity' AS t, a.aname AS an, MONTH(ao.book_time) as tm, SUM(ao.quantity) AS q, a.price AS p, SUM(ao.quantity) * a.price AS total " +
				"FROM activity_order ao, activities a, members m, staffs s " +
				"WHERE ao.activity = a.id AND ao.member = m.id AND ao.staff = s.id" + s + a1 + a2 + a3 + " GROUP BY m.id, a.aname";
		String q2 = "SELECT CONCAT(s.fname, ' ', s.lname) AS n, 'Sale' AS t, f.name AS an, MONTH(fo.sell_time) as tm, SUM(fo.quantity) AS q, f.price AS p, SUM(fo.quantity) * f.price AS total " +
				"FROM food_beverage_order fo, food_beverage f, members m, staffs s " +
				"WHERE fo.foot_beverage = f.id AND fo.member = m.id AND fo.staff = s.id" + s + s1 + s2  + s3 + " GROUP BY m.id, f.name";
		String q4 = " ORDER BY n, an";
		String query = "";
		if(type.equals("1")){
			query = "(" + q1 + ")" + q4;
		} else if(type.equals("2")){
			query = "(" + q2 + ")" + q4;
		} else{
			query = "(" + q1 + ") UNION (" + q2 + ")" + q4;
		}
		
		DBConnect dbc = new DBConnect();
		dbc.selectSql(query);
		List<LocalStatisticMembershipDto> ao = new ArrayList<>();
		try {
			while(dbc.rs.next()){
				LocalStatisticMembershipDto r = new LocalStatisticMembershipDto();
				r.n = dbc.rs.getString("n");
				r.t = dbc.rs.getString("t");
				r.month = dbc.rs.getInt("tm");
				r.year = year;
				r.an = dbc.rs.getString("an");
				r.q = dbc.rs.getFloat("q");
				r.p = dbc.rs.getFloat("p");
				r.total = dbc.rs.getFloat("total");
				ao.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbc.closeAll();
		}
		return ao;
	}
}
