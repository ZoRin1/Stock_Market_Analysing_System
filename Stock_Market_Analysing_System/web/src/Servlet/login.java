package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BLSer.LoginBlSer;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginBlSer login;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        login = new BL.login();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset-UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("u");
		String password = request.getParameter("p");
		if(request.getSession(false)!=null){
			if(request.getSession(false).getAttribute("user")!=null){		
				out.write((String)request.getSession(false).getAttribute("user"));
				return;
			}
		}
		
		if(login.login(username, password)){
			request.getSession().setAttribute("user", username);
			out.write("success");
			
			return;
		}
		out.write("fail");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
