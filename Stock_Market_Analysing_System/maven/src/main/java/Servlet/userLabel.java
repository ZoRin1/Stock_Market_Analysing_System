package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import BLSer.LoginBlSer;
import PO.IndexPO;

/**
 * Servlet implementation class userLabel
 */
@WebServlet("/userLabel")
public class userLabel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginBlSer login;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userLabel() {
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
		
		double[] result = login.getOneStock(username);
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("yitou", result[0]);
			obj.put("total", result[1]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.write(obj.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
