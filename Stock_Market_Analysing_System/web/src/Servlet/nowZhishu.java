package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BL.getMarketInfo;
import BLSer.getMarketInfoSer;
import PO.MarketlistPO;

/**
 * Servlet implementation class nowZhishu
 */
@WebServlet("/nowZhishu")
public class nowZhishu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private getMarketInfoSer nowMarket;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nowZhishu() {
        super();
        // TODO Auto-generated constructor stub
        nowMarket = new getMarketInfo();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		
		List<MarketlistPO> list;
		JSONArray objArray = new JSONArray();
		
//		String code = request.getParameter("code");
		
		list = nowMarket.getMarketList();
		for(int i =0;i<list.size();i++){
			JSONObject obj = new JSONObject();
			try {
				obj.put("指数代码", list.get(i).getCode());
				obj.put("指数名称", list.get(i).getName());
				obj.put("开盘点位", list.get(i).getOpen());
				obj.put("昨收点位", list.get(i).getPre_close());
				obj.put("当前点位", list.get(i).getPrice());
				obj.put("最高点位", list.get(i).getHigh());
				obj.put("最低点位", list.get(i).getLow());
				obj.put("成交量", list.get(i).getVolume());
				obj.put("成交金额", list.get(i).getAmount());
				obj.put("更新时间", list.get(i).getTime());
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
