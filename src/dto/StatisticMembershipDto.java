package dto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class StatisticMembershipDto{
	public String mcode;
	public String mname;
	public String a;
	public float q;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public float p;
	public float total;
	public int year;
	public int month;
	public int week;
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public float getQ() {
		return q;
	}
	public void setQ(float q) {
		this.q = q;
	}
	public float getP() {
		return p;
	}
	public void setP(float p) {
		this.p = p;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public StatisticMembershipDto createFromString(String m){
		Document a = Jsoup.parse(m);
		StatisticMembershipDto s = new StatisticMembershipDto();
		s.mcode = a.getElementById("mcode").text();
		s.mname = a.getElementById("mname").text();
		s.q = Float.parseFloat(a.getElementById("q").text());
		s.p = Float.parseFloat(a.getElementById("p").text());
		s.total = Float.parseFloat(a.getElementById("total").text());
		s.year = Integer.parseInt(a.getElementById("year").text());
		s.month = Integer.parseInt(a.getElementById("month").text());
		s.week = Integer.parseInt(a.getElementById("week").text());
		return s;
	}
	public String toXMLString(){
		String out = "<xml>"
                + "<a id=\"mcode\">" + this.mcode + "</a>"
                + "<a id=\"mname\">" + this.mname + "</a>"
                + "<a id=\"q\">" + this.q + "</a>"
                + "<a id=\"p\">" + this.p + "</a>"
                + "<a id=\"total\">" + this.total + "</a>"
                + "<a id=\"year\">" + this.year + "</a>"
                + "<a id=\"month\">" + this.month + "</a>"
                + "<a id=\"week\">" + this.week + "</a>"
                + "</xml>";
		return out;
	}
}
