package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.LoginService;
import utils.Utils;
import entities.Staff;

@WebServlet("/login.dis")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
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
		String action = request.getParameter("submit");
		request.setAttribute("context", request.getRequestURI());
		
		if (action == null) {
			action = "index";
		}
		if (action.toLowerCase().equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginService loginService = new LoginService();
			Staff staff = loginService.login(username, password);
			if(staff == null){
				Utils.addActionMessage(request, "errorName", "Login failed");
				Utils.addActionMessage(request, "errorDesc", "Wrong username and password combination");
				Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
			} else{
				Utils.setLogin(request, staff);
				response.sendRedirect(request.getContextPath() + "/index.dis");				
			}
		} else if (action.toLowerCase().equals("logout")) {
			Utils.logout(request, response);
		} else{
			Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
		}
	}
}
