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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BLSer.LoginBlSer;
import PO.IndexPO;
import PO.RaisePO;

/**
 * Servlet implementation class userZixuan
 */
@WebServlet("/userZixuan")
public class userZixuan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginBlSer login;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userZixuan() {
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
		List<RaisePO> list;
		JSONArray objArray = new JSONArray();
		
		if(session==null){
			System.out.println("session获取失败a");
			return;
		}
		
		String username = (String)session.getAttribute("user");
		
		if(username==null){
			return;
		}
		
		list = login.getOwnStock(username);
		
		for(int i = 0 ; i<list.size();i++){
			JSONObject obj = new JSONObject();
			try {
				obj.put("股票代码", list.get(i).getCode());
				obj.put("股票名称", list.get(i).getName());
				obj.put("行业名称", list.get(i).getIndustry());
				obj.put("开盘价", list.get(i).getOpen());
				obj.put("昨收", list.get(i).getPreclose());
				obj.put("当前价格", list.get(i).getPrice());
				obj.put("涨跌幅", list.get(i).getChange());
				obj.put("当前持有", list.get(i).getNow());
				obj.put("初始投入", list.get(i).getInit());
				obj.put("初始投资设定日期", list.get(i).getDate());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objArray.put(obj);
		}
		
		out.write(objArray.toString());
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
