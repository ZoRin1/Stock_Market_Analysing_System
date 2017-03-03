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

import BL.getStockInfo;
import DataImp.GetMarketInfoDataImp;
import PO.MarketonePO;
import PO.StockListPO;

/**
 * Servlet implementation class getStockList
 * 用于NowList界面
 * 得到Stock的实时信息
 */
@WebServlet("/getStockList")
public class getStockList extends HttpServlet {
	
	private getStockInfo getStock;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getStockList() {
        super();
        getStock = new getStockInfo();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

//		System.out.println(code);
		synchronized (this) {
			String code = request.getParameter("code");
			List<StockListPO> list = getNowStock(code);
			JSONArray objArray = new JSONArray();
			int length = list.size();
			for(int a = 0;a<length;a++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("股票代码", list.get(a).getCode());
					obj.put("股票名称", list.get(a).getName());
					obj.put("行业名称", list.get(a).getIndustry());
					obj.put("开盘价", list.get(a).getOpen());
					obj.put("昨收", list.get(a).getPre_close());
					obj.put("当前价格", list.get(a).getPrice());
					obj.put("今日最高价", list.get(a).getHigh());
					obj.put("今日最低价", list.get(a).getLow());
					obj.put("竞买价", list.get(a).getB1_p());
					obj.put("竞卖价", list.get(a).getA1_p());
					obj.put("成交量", list.get(a).getVolume());
					obj.put("成交金额", list.get(a).getAccount());
					obj.put("更新时间", list.get(a).getTime());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objArray.put(obj);
			}
			PrintWriter pw = response.getWriter();
			pw.write(objArray.toString());
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private List<StockListPO> getNowStock(String code){
		List<StockListPO> stock = getStock.getStockList(code);
//		int length = stock.size();
//		System.out.println(length);
//		for(int i = 0;i<length;i++){
//			System.out.println(stock.get(i).toString());
//		}
		return stock;
	}

}
