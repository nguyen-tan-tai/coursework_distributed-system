package services;

import java.util.ArrayList;
import java.util.List;
import utils.DBConnect;
import dto.StatisticMembershipDto;

public class ReportNationalServices {
	public DBConnect dbc;

	public ReportNationalServices(DBConnect c) {
		this.dbc = c;
	}

	public ReportNationalServices() {
		this.dbc = new DBConnect();
	}

	public List<StatisticMembershipDto> getReport(String type, int year, int month, int week) {
		String query = "";
		String a1 = "", a2 = "", a3 = "";
		if(year > 0){
			a1 = " AND YEAR(ao.book_time) = " + year;
		}
		if(month > 0){
			a2 = " AND MONTH(ao.book_time) = " + month;
		}
		if(week > 0){
			a3 = " AND ((WEEK(ao.book_time,5) - WEEK(DATE_SUB(ao.book_time, INTERVAL DAYOFMONTH(ao.book_time)-1 DAY),5)+1) = " + week + ")";
		}

		if (type.equals("activity")) {
			query = "SELECT m.code AS mcode, CONCAT(m.fname, ' ', m.lname) AS mname, a.aname AS a, " +
					"SUM(ao.quantity) AS q, a.price AS p, SUM(ao.quantity) * a.price AS total " +
					"FROM activity_order ao, activities a, members m, staffs s " +
					"WHERE ao.activity = a.id AND ao.member = m.id AND ao.staff = s.id" + a1 + a2 + a3 + " GROUP BY m.id;";
		} else {
			query = "SELECT m.code AS mcode, CONCAT(m.fname, ' ', m.lname) AS mname, f.name AS a, " +
					"SUM(fo.quantity) AS q, f.price AS p, SUM(fo.quantity) * f.price AS total " +
					"FROM food_beverage_order fo, food_berverage f, members m, staffs s " +
					"WHERE fo.activity = f.id AND fo.member = m.id AND fo.staff = s.id" + a1 + a2 + a3 + " GROUP BY m.id;";
		}
		dbc.selectSql(query);
		List<StatisticMembershipDto> ao = new ArrayList<>();
		try {
			while (dbc.rs.next()) {
				StatisticMembershipDto r = new StatisticMembershipDto();
				r.mcode = dbc.rs.getString("mcode");
				r.mname = dbc.rs.getString("mname");
				r.a = dbc.rs.getString("a");
				r.q = dbc.rs.getFloat("q");
				r.p = dbc.rs.getFloat("p");
				r.total = dbc.rs.getFloat("total");
				r.year = year;
				r.month = month;
				r.week = week;
				ao.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closeAll();
		}
		return ao;
	}
}
