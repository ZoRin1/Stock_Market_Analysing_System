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

import BL.login;

/**
 * Servlet implementation class zixuangu
 */
@WebServlet("/zixuangu")
public class zixuangu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public zixuangu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		synchronized (this) {
			String code = request.getParameter("code");
			String type=request.getParameter("type");
			HttpSession session = request.getSession(false);
			String string="";
			switch (type) {
			case "dakai":
				if(session==null||session.getAttribute("user")==null){
//					System.out.println("asdas");
					string="请先登录";

				}
				else {
					login login=new login();
					if (login.check((String)session.getAttribute("user"), code)) {
						string="-移出自选股";
					}else {
						string="+加入自选股";
					}
				}
				break;
			case "dianji":
				if(session==null||session.getAttribute("user")==null){
//					System.out.println("asdas");
					string="login";
				}
				else {
					login login=new login();
					if (login.check((String)session.getAttribute("user"), code)) {
						login.delete((String)session.getAttribute("user"), code);
						string="+加入自选股";
					}else {
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String datestring = dateFormat.format(now);
						login.raise((String)session.getAttribute("user"), code,datestring, 0);
						string="-移出自选股";
					}
				}
				break;
			default:
				break;
			}
			PrintWriter pw = response.getWriter();
			pw.write(string);
			pw.close();
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
