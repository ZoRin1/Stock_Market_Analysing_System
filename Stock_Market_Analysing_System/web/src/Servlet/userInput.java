package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLSer.LoginBlSer;

/**
 * Servlet implementation class userInput
 */
@WebServlet("/userInput")
public class userInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginBlSer login;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userInput() {
        super();
        // TODO Auto-generated constructor stub
        login = new BL.login();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		if(session==null){
			return;
		}
		
		String username = (String)session.getAttribute("user");
		
		if(username==null){
			return;
		}
		
		String code = request.getParameter("gupiaocode");
		System.out.println(code);
		String money = request.getParameter("touzi");
		System.out.println(money);
		
		if(login.check(username, code)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			String now = df.format(new Date());
			double res = Double.parseDouble(money);
			
			login.raise(username, code, now, res);
			out.write("<script>alert(\"更新成功！\");</script>");
			response.setHeader("refresh", "0,user.html?name="+username);
		}else{
			out.write("<script>alert(\"非自选股！\");</script>");
			response.setHeader("refresh", "0,user.html?name="+username);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
