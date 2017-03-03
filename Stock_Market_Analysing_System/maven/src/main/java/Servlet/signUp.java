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
 * Servlet implementation class signUp
 */
@WebServlet("/signUp")
public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginBlSer signUp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUp() {
        super();
        // TODO Auto-generated constructor stub
        signUp = new BL.login();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("user");
//		System.out.println(username);
		String password = request.getParameter("passwd");
//		System.out.println(password);
		String password2 = request.getParameter("passwd2");
//		System.out.println(password2);
		if(!password.equals(password2)){
			response.sendRedirect("login.html");
			out.write("<script>alert(\"密码输入错误！\");</script>");
			return;
		}
		
		if(signUp.signup(username, password)){
			request.getSession().setAttribute("user", username);
			response.sendRedirect("home.html");
			out.write(1);
			return;
		}
		
		out.write(0);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
