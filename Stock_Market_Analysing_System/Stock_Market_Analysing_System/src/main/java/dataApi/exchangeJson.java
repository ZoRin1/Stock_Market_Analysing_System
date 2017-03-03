package dataApi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class exchangeJson {
	/**
	 * 
	 * @param	jsonStr: json数据，股票列表和对应的访问url
	 * @return	url list数组
	 */
	public  List<String> exJson(String jsonStr){
		String getGuLink = "";
		List<String> result = new ArrayList<String>();
		try{
			JSONObject jsonobj = new JSONObject(jsonStr);
			String data = jsonobj.getString("data");
			JSONArray jsonarray = new JSONArray(data);
			System.out.println(jsonarray.length());
			for(int i = 0 ; i < jsonarray.length();i++){
				JSONObject json = jsonarray.getJSONObject(i);
				String link = json.getString("link");
				String name = json.getString("name");
//				System.out.println("link = "+link+",name = "+name);
				getGuLink = link;
				result.add(getGuLink);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	} 
	/**
	 * 
	 * @param jsonStr：json数据
	 * @param fieldsCommend：指定数据字段
	 * @return 返回指定股票代码的股票交易数据
	 */
	public List<String> arrJson(String jsonStr,String fieldsCommend){
		String getGuLink = "";
		List<String> result = new ArrayList<String>();
		String[] temp = fieldsCommend.split("\\+");
		try{
			JSONObject jsonobj = new JSONObject(jsonStr);
			String data  = jsonobj.getString("data");
			JSONObject jsonobj1 = new JSONObject(data);
			String data2 = jsonobj1.getString("trading_info");
			JSONArray jsonarray = new JSONArray(data2);
			for(int i = 0 ; i < jsonarray.length();i++){
				JSONObject json = jsonarray.getJSONObject(i);
				for(int j = 0 ; j<temp.length;j++){
					String tempStr = json.getString(temp[j]);
					getGuLink = getGuLink+tempStr+",";
//					+temp[j]+" ="
				}
				//最后得到一个date
				String tempStr = json.getString("date");
				getGuLink =tempStr +"," + getGuLink;
				//最后得到一个date
				result.add(getGuLink);
				getGuLink = "";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
