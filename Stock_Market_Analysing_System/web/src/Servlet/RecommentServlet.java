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

import BL.predict;
import PO.ChangePO;
import PO.DeviationPO;
import PO.StablePO;

/**
 * Servlet implementation class RecommentServlet
 */
@WebServlet("/RecommentServlet")
public class RecommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private predict predict;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommentServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.predict=new predict();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		JSONArray objArray = new JSONArray();
		List<DeviationPO> dlist;
		List<StablePO> slist;
		List<ChangePO> clist;
			switch(type){
			case "table1":
				dlist=predict.IncreaseMore(code);
				for(int i=0;i<5;i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code", dlist.get(i).getCode());
						obj.put("name", dlist.get(i).getName());
						obj.put("industry", dlist.get(i).getIndustry());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				break;
			case "table2":
				slist=predict.Increase(code);
				for(int i=0;i<5;i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code", slist.get(i).getCode());
						obj.put("name", slist.get(i).getName());
						obj.put("industry", slist.get(i).getIndustry());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				break;
			case "table3":
				clist=predict.ChangeMax(code);
				for(int i=0;i<5;i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code", clist.get(i).getCode());
						obj.put("name", clist.get(i).getName());
						obj.put("industry", clist.get(i).getIndustry());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}			
				break;
			case "chart1":
				dlist=predict.IncreaseMore(code);
				for(int i=0;i<dlist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code", dlist.get(i).getCode());
						obj.put("name", dlist.get(i).getName());
						obj.put("industry", dlist.get(i).getIndustry());
						obj.put("number", dlist.get(i).getDeviation());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				break;
			case "chart2":
				slist=predict.Increase(code);
				for(int i=0;i<slist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code", slist.get(i).getCode());
						obj.put("name", slist.get(i).getName());
						obj.put("industry", slist.get(i).getIndustry());
						obj.put("number", slist.get(i).getInpossi());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				break;
			case "chart3":
				clist=predict.ChangeMax(code);
				for(int i=0;i<clist.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code", clist.get(i).getCode());
						obj.put("name", clist.get(i).getName());
						obj.put("industry", clist.get(i).getIndustry());
						obj.put("number", clist.get(i).getChange());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				break;
			default:
				System.out.println("cuowu");
				break;
		}
		PrintWriter pw = response.getWriter();
		pw.write(objArray.toString());
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
