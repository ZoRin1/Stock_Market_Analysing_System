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

import BL.getIndex;
import BL.getStockInfo;
import BL.get_p_change;
import BL.predict;
import PO.AtrPO;
import PO.StockListPO;
import PO.StockOnePO;
import PO.kdjPO;
import PO.macdPO;
import PO.pchangePO;

/**
 * Servlet implementation class oneStock
 */
@WebServlet("/oneStock")
public class oneStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private getStockInfo getStockInfo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public oneStock() {
        super();
         this.getStockInfo=new getStockInfo();
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
		List<StockOnePO> list=getStockInfo.getStockOne(code);
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
			case "zeng":
				get_p_change get_p_change=new get_p_change();
				List<pchangePO> pchangelist=get_p_change.getpchange(code);
				for(int i=0;i<pchangelist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", pchangelist.get(i).getDate());
						obj.put("stock", pchangelist.get(i).getStock());
						obj.put("market", pchangelist.get(i).getMarket());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
				break;
			case "atr":
				getIndex getIndexatr=new getIndex();
				List<AtrPO> atrlist= getIndexatr.getATR(code);
				for(int i=0;i<atrlist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", atrlist.get(i).getDate());
						obj.put("atr", atrlist.get(i).getAtr());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
				break;
			case "macd":
				getIndex getIndexmacd=new getIndex();
				List<macdPO> macdlist= getIndexmacd.getMACD(code);
				for(int i=0;i<macdlist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", macdlist.get(i).getDate());
						obj.put("dif", macdlist.get(i).getDIF());
						obj.put("dea", macdlist.get(i).getDEA());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
				break;
			case "kdj":
				getIndex getIndexkdj=new getIndex();
				List<kdjPO> kdjlist= getIndexkdj.getKDJ(code);
				for(int i=0;i<kdjlist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("date", kdjlist.get(i).getDate());
						obj.put("k", kdjlist.get(i).getK());
						obj.put("d", kdjlist.get(i).getD());
						obj.put("j", kdjlist.get(i).getJ());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				string=objArray.toString();
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
