package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BL.getStockInfo;
import BL.predict;
import PO.StockListPO;

/**
 * Servlet implementation class oneStockOther
 */
@WebServlet("/oneStockOther")
public class oneStockOther extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public oneStockOther() {
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
		getStockInfo getStockInfo=new getStockInfo();
		synchronized(this){
			JSONArray objArray = new JSONArray();
			String string="";
			switch (type) {
			case "wudang":
				StockListPO spo=getStockInfo.getNewStockList(code);
				JSONObject obj = new JSONObject();
				try {
					obj.put("b1",spo.getB1_p());
					obj.put("b2",spo.getB2_p());
					obj.put("b3",spo.getB3_p());
					obj.put("b4",spo.getB4_p());
					obj.put("b5",spo.getB5_p());
					obj.put("a1",spo.getA1_p());
					obj.put("a2",spo.getA2_p());
					obj.put("a3",spo.getA3_p());
					obj.put("a4",spo.getA4_p());
					obj.put("a5",spo.getA5_p());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objArray.put(obj);
				string=objArray.toString();
				break;
			case "shishi":
				StockListPO sslist=getStockInfo.getNewStockList(code);
				JSONObject ob = new JSONObject();
				try {
					ob.put("code",sslist.getCode());
					ob.put("name",sslist.getName());
					ob.put("industry",sslist.getIndustry());
					ob.put("open",sslist.getOpen());
					ob.put("pre_close",sslist.getPre_close());
					ob.put("price",sslist.getPrice());
					ob.put("high",sslist.getHigh());
					ob.put("low",sslist.getLow());
					ob.put("volume",sslist.getVolume());
					ob.put("account",sslist.getAccount());
					ob.put("time",sslist.getTime());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objArray.put(ob);
				string=objArray.toString();
				break;
			case "qianjing":
				predict predict=new predict();
				string=predict.predict(code);
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
