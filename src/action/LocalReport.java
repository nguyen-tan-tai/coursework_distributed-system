package action;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ReportLocalServices;
import services.StaffService;
import utils.AccessRole;
import utils.Utils;
import dto.ActivityByTypeDto;
import dto.LocalStatisticMembershipDto;
import entities.Staff;

@WebServlet("/localReport.dis")
public class LocalReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("context", request.getRequestURI());
		request.setAttribute("contextPath", request.getContextPath());
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(action == null){
			action = "index";
		}
		if(action.equals("report")){
			Calendar c = Calendar.getInstance();
			if(AccessRole.isAllow(session, 2)){
				String type = request.getParameter("type");
				if(type == null){type = "a-n";}
				String y = request.getParameter("year");
				int year;
				if(y == null){
					year = c.get(Calendar.YEAR);
				} else{
					year = Integer.valueOf(y);
				}
				String m = request.getParameter("month");
				int month;
				if(m == null){
					month = c.get(Calendar.MONTH) + 1;
				} else{
					month = Integer.valueOf(m);
				}
				String w = request.getParameter("week");
				int week;
				if(w == null){
					week= c.get(Calendar.WEEK_OF_MONTH);
				} else{
					week = Integer.valueOf(w);
				}
				Utils.setDto(request, "week", week);
				Utils.setDto(request, "month", month);
				Utils.setDto(request, "year", year);
				
				if(type.equals("a-n") || type.equals("a-t") || type.equals("s-n") || type.equals("s-t") || type.equals("")){
					ReportLocalServices rs = new ReportLocalServices();
					List<ActivityByTypeDto> r = rs.getActivityReportByType(type, year, month, week); 
					Utils.setDto(request, "reports", r);
					Utils.go(request, response, this.getClass().getSimpleName(), "report.jsp");
				} else {
					Utils.addActionMessage(request, ".errorName", "Page not found");
					Utils.addActionMessage(request, ".errorDesc", "The page you request is not available or invalid");
					Utils.go(request, response, "commons/", "error.jsp");
				}
			} else{
				goError(request, response);
			}
		} else if(action.equals("statistic")){
			Calendar c = Calendar.getInstance();
			if(AccessRole.isAllow(session, 2)){
				String type = request.getParameter("type");
				if(type == null){type = "";}
				String y = request.getParameter("year");
				int year = c.get(Calendar.YEAR);
				if(y != null){
					year = Integer.valueOf(y);
				}
				String m = request.getParameter("month");
				int month = c.get(Calendar.MONTH);
				if(m != null){
					month = Integer.valueOf(m);
				}
				String w = request.getParameter("week");
				int week= c.get(Calendar.WEEK_OF_MONTH);
				if(w != null){
					week = Integer.valueOf(w);
				}
				String s = request.getParameter("staff");
				int staff = 0;
				if(s != null){
					staff = Integer.valueOf(s);
				}
				Utils.setDto(request, "week", week);
				Utils.setDto(request, "month", month);
				Utils.setDto(request, "year", year);
				Utils.setDto(request, "staff", staff);
				StaffService staffService = new StaffService();
				List<Staff> ls = staffService.gets("");
				Utils.setDto(request, "staffs", ls);
				if(month >= 0){
					ReportLocalServices rs = new ReportLocalServices();
					List<LocalStatisticMembershipDto> ss = rs.getStatistic(year, month, week, staff, type, ""); 
					Utils.setDto(request, "statistics", ss);
					Utils.checkRole(request, response, 2, this.getClass().getSimpleName(), "statistic.jsp");
				} else {
					Utils.addActionMessage(request, ".errorName", "Page not found");
					Utils.addActionMessage(request, ".errorDesc", "The page you request is not available or invalid");
					Utils.go(request, response, "commons/", "error.jsp");
				}
			} else{
				goError(request, response);
			}
		} else{
			Utils.checkRole(request, response, 2, this.getClass().getSimpleName(), "index.jsp");
		}
	}
	public void goError(HttpServletRequest request, HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath() + "/login.dis");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}