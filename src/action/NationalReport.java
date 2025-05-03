package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dto.StatisticMembershipDto;

import services.CenterService;
import services.InfoService;
import utils.AccessRole;
import utils.Utils;

@WebServlet("/nationalReport.dis")
public class NationalReport extends HttpServlet {
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
		String action = request.getParameter("action");

		if(AccessRole.isAllow(request.getSession(), 3)){
			if(action == null){
				action = "index";
			}
			if(action.equals("report")){
				Calendar c = Calendar.getInstance();
				String type = request.getParameter("type");
				if(type == null){type = "";}
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

				if(type.equals("activity") || type.equals("sale") || type.equals("")){
					String result = "";
					Document d = Jsoup.parse(result);
					Elements es = d.getElementsByTag("xmllist").get(0).getElementsByTag("xml");
					List<dto.StatisticMembershipDto>  l = new ArrayList<>();
					StatisticMembershipDto sm = new StatisticMembershipDto();
					for(Element e : es){
						l.add(sm.createFromString(e.html()));
					}
					Utils.setDto(request, "reports", l);
					Utils.go(request, response, this.getClass().getSimpleName(), "report.jsp");
				} else {
					Utils.addActionMessage(request, ".errorName", "Page not found");
					Utils.addActionMessage(request, ".errorDesc", "The page you request is not available or invalid");
					Utils.go(request, response, "commons/", "error.jsp");
				}
			} else if(action.equals("statistic")){

			} else{
				CenterService cs = new CenterService();
				Utils.setDto(request, "centers", cs.gets(""));
				Utils.setDto(request, "center", new InfoService().gets("center"));
				if(cs.gets("").size() > 1000){

				} else{
					Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
				}
			}
		} else {
			Utils.addActionMessage(request, ".errorName", "Page not found");
			Utils.addActionMessage(request, ".errorDesc", "The page you request is not available or invalid");
			Utils.go(request, response, "commons/", "error.jsp");
		}
	}
}