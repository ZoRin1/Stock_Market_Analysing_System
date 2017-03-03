package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BLSer.LoginBlSer;

/**
 * Servlet implementation class userHy
 */
@WebServlet("/userHy")
public class userHy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginBlSer login;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userHy() {
        super();
        // TODO Auto-generated constructor stub
        login =new BL.login();
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
		
		String name = (String)session.getAttribute("user");
		
		if(name==null){
			return;
		}
		
		double[] result = login.getIndustry(name);
		JSONArray  objArray = new JSONArray();
		JSONObject obj = new JSONObject();
		
			try {
				obj.put("value", result[0]);
				obj.put("name", "金属材料");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[1]);
				obj.put("name", "机械仪表");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[2]);
				obj.put("name", "电子");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[3]);
				obj.put("name", "综合类");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[4]);
				obj.put("name", "文化传播");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[5]);
				obj.put("name", "生物医药");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[6]);
				obj.put("name", "石油化工");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[7]);
				obj.put("name", "纺织服装");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[8]);
				obj.put("name", "造纸印刷");
				objArray.put(obj);
				obj = new JSONObject();
				obj.put("value", result[9]);
				obj.put("name", "食品饮料");
				objArray.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		out.write(objArray.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
