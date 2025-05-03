package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Member;

import services.MemberService;
import utils.AccessRole;
import utils.Utils;

@WebServlet("/membership.dis")
public class Membership extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Membership() {
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
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String code = request.getParameter("code");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String sid = request.getParameter("id");
		int id = 0;
		if(sid != null){
			id = Integer.parseInt(sid);
		}
		if(action.equals("check")){
			if(AccessRole.isAllow(session, 1)){
				if(task.equals("check")){
					MemberService ms = new MemberService();
					Member m = ms.check(code);
					if(m.code.equals("not-found")){
						Utils.addActionMessage(request, "msg", m.address);
						m = null;
					}
					Utils.setDto(request, "member", m);
					Utils.go(request, response, this.getClass().getSimpleName(), "result.jsp");
				} else{
					Utils.go(request, response, this.getClass().getSimpleName(), "check.jsp");
				}
			} else{
				goError(request, response);
			}
		} else if(action.equals("add")){
			if(AccessRole.isAllow(session, 1)){
				if(task.equals("save")){
					Member m = new Member();
					m.setFname(fname);
					m.setLname(lname);
					m.setCode(code);
					m.setAddress(address);
					m.setPhone(phone);
					m.setEmail(email);
					MemberService ms = new MemberService();
					ms.add(m);
					Utils.addActionMessage(request, "info", "save");
					Utils.setDto(request, "members", ms.gets(""));
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
					Member m = new Member();
					m.setFname(fname);
					m.setLname(lname);
					m.setCode(code);
					m.setAddress(address);
					m.setPhone(phone);
					m.setEmail(email);
					m.setId(id);
					MemberService ms = new MemberService();
					ms.update(m);
					Utils.addActionMessage(request, "info", "update");
					Utils.setDto(request, "members", ms.gets(""));
					Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
				} else{
					MemberService ms = new MemberService();
					Utils.setDto(request, "member", ms.get(Integer.valueOf(id)));
					Utils.go(request, response, this.getClass().getSimpleName(), "add.jsp");
				}
			} else{
				goError(request, response);
			}
		} else if(action.equals("delete")){
			if(AccessRole.isAllow(session, 1)){
				Utils.addActionMessage(request, "info", "delete");
				MemberService ms = new MemberService();
				Utils.setDto(request, "members", ms.gets(""));
				Utils.go(request, response, this.getClass().getSimpleName(), "index.jsp");
			} else{
				goError(request, response);
			}
		} else {
			if(AccessRole.isAllow(session, 1)){
				MemberService ms = new MemberService();
				Utils.setDto(request, "members", ms.gets(""));
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
