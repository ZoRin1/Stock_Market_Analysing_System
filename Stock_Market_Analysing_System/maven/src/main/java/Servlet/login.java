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
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(request.getSession(false)!=null){
			if(request.getSession(false).getAttribute("user")!=null){
				out.print("<h3>帐号 "+request.getSession(false).getAttribute("user")+" 已经登录，请退出后重新登录</h3><br>");
				out.print("<a href='http://localhost:8080/web/logout'>退出登录</a>");
				out.print("<a href='http://localhost:8080/web/home.html'>返回首页</a>");
				return;
			}
		}
		
		if(login.login(username, password)){
			request.getSession().setAttribute("user", username);
			out.print("<script>alert(\"登陆成功\");</script>");
//			out.print("<h3>登陆成功 ,三秒后自动跳转到首页</h3><br>");
//			out.println("<a href='http://localhost:8080/web/home.html'>回到首页</a>");
			response.setHeader("refresh" , "0; url=’home.html‘");
			return;
		}
		
		out.print("<h3>帐号或密码错误，请检查后重新登录</h3><br>");
		out.print("<a href='http://localhost:8080/web/login.html'>重新登陆</a>");
		out.print("<a href='http://localhost:8080/web/home.html'>返回首页</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
