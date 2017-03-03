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

import BL.getIndustry;
import BLSer.IndustryBlSer;
import PO.industryPO;

/**
 * Servlet implementation class hyAnalyzeSer
 */
@WebServlet("/hyAnalyzeSer")
public class hyAnalyzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndustryBlSer industry;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hyAnalyzeServlet() {
        super();
        // TODO Auto-generated constructor stub
        industry = new getIndustry();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		synchronized(this){
			String code = request.getParameter("code");
			String type=request.getParameter("t");
			List<industryPO> list = industry.getIndustryList(code);
			JSONArray objArray = new JSONArray();
			if (type.equals("chart")) {
				for(int i=0;i<list.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("value", list.get(i).getVolume());
						obj.put("name", list.get(i).getName());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
			}
			else{
				for(int i=0;i<list.size();i++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("name", list.get(i).getName());
						obj.put("zhangdie", list.get(i).getP_change());
						obj.put("lingzhang", list.get(i).getMaxName());
						obj.put("shangzhang", list.get(i).getInnum());
						obj.put("bubian", list.get(i).getEqnum());
						obj.put("xiadie", list.get(i).getOunum());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}		
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
		doGet(request, response);
	}

}
