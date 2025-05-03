package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ActivityOrderService;
import services.ActivityService;
import services.MemberService;
import utils.AccessRole;
import utils.Utils;

@WebServlet("/activityOrder.dis")
public class ActivityOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActivityOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		String task = request.getParameter("task");
		if(task == null){
			task = "";
		}
		if(action == null){
			action = "list";
		}
		String member = request.getParameter("member");
		String activity = request.getParameter("activity");
		String btime = request.getParameter("btime");
		String stime = request.getParameter("stime");
		String etime = request.getParameter("etime");
		String quantity = request.getParameter("quantity");
		String sid = request.getParameter("id");
		int id = 0;
		if(sid != null){
			id = Integer.parseInt(sid);
		}

		
		Utils.setDto(request, "allMembers", new MemberService().gets(""));
		Utils.setDto(request, "allActivities", new ActivityService().gets(""));

		if(action.equals("add")){
			if(AccessRole.isAllow(session, 1)){
				if(task.equals("save")){
					Utils.addActionMessage(request, "info", "save");
					entities.ActivityOrder ao = new entities.ActivityOrder();
					ao.setMember(new MemberService().get(Integer.parseInt(member)));
					ao.setActivity(new ActivityService().get(Integer.parseInt(activity)));
					ao.setBookTime(Utils.s2D(btime, "MM/dd/yyyy"));
					ao.setStartTime(Utils.s2D(stime, "MM/dd/yyyy"));
					ao.setEndTime(Utils.s2D(etime, "MM/dd/yyyy"));
					ao.setStaff(Utils.getLogin(session));
					ao.setQuantity(Integer.parseInt(quantity));
					ActivityOrderService aos = new ActivityOrderService();
					aos.add(ao);
					Utils.setDto(request, "aos", aos.gets(""));
					Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
				} else{
					Utils.go(request, response, this.getClass().getSimpleName(), "add.jsp");
				}
			} else{
				goError(request, response);
			}
		} else if(action.equals("edit")){
			if(AccessRole.isAllow(session, 1)){
				if(task.equals("update")){
					entities.ActivityOrder ao = new entities.ActivityOrder();
					ao.setMember(new MemberService().get(Integer.parseInt(member)));
					ao.setActivity(new ActivityService().get(Integer.parseInt(activity)));
					ao.setBookTime(Utils.s2D(btime, "MM/dd/yyyy"));
					ao.setStartTime(Utils.s2D(stime, "MM/dd/yyyy"));
					ao.setEndTime(Utils.s2D(etime, "MM/dd/yyyy"));
					ao.setStaff(Utils.getLogin(session));
					ao.setQuantity(Integer.parseInt(quantity));
					ao.setId(id);
					ActivityOrderService aos = new ActivityOrderService();
					aos.update(ao);
					Utils.addActionMessage(request, "info", "update");
					Utils.setDto(request, "aos", aos.gets(""));
					Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
				} else{
					ActivityOrderService aos = new ActivityOrderService();
					Utils.setDto(request, "ao", aos.get(Integer.valueOf(id)));
					Utils.go(request, response, this.getClass().getSimpleName(), "add.jsp");
				}
			} else{
				goError(request, response);
			}
		} else if(action.equals("delete")){
			if(AccessRole.isAllow(session, 1)){
				ActivityOrderService aos = new ActivityOrderService();
				aos.delete(id);
				Utils.setDto(request, "aos", aos.gets(""));
				Utils.addActionMessage(request, "info", "delete");
				Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
			} else{
				goError(request, response);
			}
		} else {
			if(AccessRole.isAllow(session, 1)){
				ActivityOrderService aos = new ActivityOrderService();
				Utils.setDto(request, "aos", aos.gets(""));
				Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
			} else{
				goError(request, response);
			}
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
