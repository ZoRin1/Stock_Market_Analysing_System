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
import PO.MarketlistPO;
import PO.MarketonePO;
import PO.StockListPO;

/**
 * Servlet implementation class oneMarket
 */
@WebServlet("/oneMarket")
public class oneMarket extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private getMarketInfo getMarketInfo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public oneMarket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("code");
		String type=request.getParameter("type");
		getMarketInfo=new getMarketInfo();
		List<MarketonePO> list=getMarketInfo.getMarketone(code);
		synchronized(this){
			JSONArray objArray = new JSONArray();
			String string="";
			switch (type) {
			case "k":
				for(int i=0;i<list.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", list.get(i).getDate());
						obj.put("open", list.get(i).getOpen());
						obj.put("close", list.get(i).getClose());
						obj.put("high", list.get(i).getHigh());
						obj.put("low", list.get(i).getLow());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
				break;
			case "cheng":
				for(int i=0;i<list.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", list.get(i).getDate());
						obj.put("volume", list.get(i).getVolume());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
				break;
			case "san":
				for(int i=0;i<list.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", list.get(i).getDate());
						obj.put("open", list.get(i).getOpen());
						obj.put("close", list.get(i).getClose());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
				break;
			case "shishi":
				MarketlistPO mpo=getMarketInfo.getNewMarketList(code);
				JSONObject ob = new JSONObject();
				try {
					ob.put("code",mpo.getCode());
					ob.put("name",mpo.getName());

					ob.put("open",mpo.getOpen());
					ob.put("pre_close",mpo.getPre_close());
					ob.put("price",mpo.getPrice());
					ob.put("high",mpo.getHigh());
					ob.put("low",mpo.getLow());
					ob.put("volume",mpo.getVolume());
					ob.put("account",mpo.getAmount());
					ob.put("time",mpo.getTime());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objArray.put(ob);
				string=objArray.toString();
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
