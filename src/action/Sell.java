package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.FoodBeverageOrderService;
import services.FoodBeverageService;
import services.MemberService;
import utils.AccessRole;
import utils.Utils;

@WebServlet("/sell.dis")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sell() {
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
		String stime = request.getParameter("stime");
		String quantity = request.getParameter("quantity");
		String sid = request.getParameter("id");
		int id = 0;
		if(sid != null){
			id = Integer.parseInt(sid);
		}
		Utils.setDto(request, "allMembers", new MemberService().gets(""));
		Utils.setDto(request, "allFbs", new FoodBeverageService().gets(""));
		if(action.equals("add")){
			if(AccessRole.isAllow(session, 1)){
				if(task.equals("save")){
					Utils.addActionMessage(request, "info", "save");
					entities.FoodBeverageOrder ao = new entities.FoodBeverageOrder();
					ao.setMember(new MemberService().get(Integer.parseInt(member)));
					ao.setFb(new FoodBeverageService().get(Integer.parseInt(activity)));
					ao.setSellTime(Utils.s2D(stime, "MM/dd/yyyy"));
					ao.setStaff(Utils.getLogin(session));
					ao.setQuantity(Integer.parseInt(quantity));
					FoodBeverageOrderService fbs = new FoodBeverageOrderService();
					Utils.setDto(request, "fbs", fbs.gets(""));
					fbs.add(ao);
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
					entities.FoodBeverageOrder ao = new entities.FoodBeverageOrder();
					ao.setMember(new MemberService().get(Integer.parseInt(member)));
					ao.setFb(new FoodBeverageService().get(Integer.parseInt(activity)));
					ao.setSellTime(Utils.s2D(stime, "MM/dd/yyyy"));
					ao.setStaff(Utils.getLogin(session));
					ao.setQuantity(Integer.parseInt(quantity));
					ao.setId(id);
					FoodBeverageOrderService aos = new FoodBeverageOrderService();
					aos.update(ao);
					Utils.addActionMessage(request, "info", "update");
					Utils.setDto(request, "fbs", aos.gets(""));
					Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
				} else{
					FoodBeverageOrderService aos = new FoodBeverageOrderService();
					Utils.setDto(request, "ao", aos.get(Integer.valueOf(id)));
					Utils.go(request, response, this.getClass().getSimpleName(), "add.jsp");
				}
			} else{
				goError(request, response);
			}
		} else if(action.equals("delete")){
			if(AccessRole.isAllow(session, 1)){
				FoodBeverageOrderService fbs = new FoodBeverageOrderService();
				fbs.delete(id);
				Utils.setDto(request, "fbs", fbs.gets(""));
				Utils.addActionMessage(request, "info", "delete");
				Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
			} else{
				goError(request, response);
			}
		} else {
			if(AccessRole.isAllow(session, 1)){
				FoodBeverageOrderService fbs = new FoodBeverageOrderService();
				Utils.setDto(request, "fbs", fbs.gets(""));
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
