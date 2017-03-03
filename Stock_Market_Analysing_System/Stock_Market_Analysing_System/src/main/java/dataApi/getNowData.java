/**
 * 
 */
package dataApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.NowDapanPo;
import PO.NowStockPo;

/**
 * @author wang
 *
 */
public class getNowData {
	public  String DEF_CHATSET = "UTF-8";
    public int DEF_CONN_TIMEOUT = 30000;
    public  int DEF_READ_TIMEOUT = 30000;
    public String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    String url ="http://web.juhe.cn:8080/finance/stock/hs";//请求接口地址
    //配置您申请的KEY
    public String APPKEY ="1b9fd1c962226d4da30a181c6dc14dba";
    public  NowStockPo getResult(String code) throws Exception{
        String result =null;
        Map params = new HashMap();//请求参数
            params.put("gid",code);//股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009
            params.put("key",APPKEY);//APP Key

            result =net(url, params, "GET");
            JSONObject object =new JSONObject(result);
            if(object.getInt("error_code")==0){
            	JSONArray resultJsonArray =new JSONArray(object.getString("result"));
            	JSONObject resultJsonObject=resultJsonArray.getJSONObject(0);
            	JSONObject dataJsonObject=new JSONObject(resultJsonObject.getString("data"));
            	JSONObject dapandataJsonObject=new JSONObject(resultJsonObject.getString("dapandata"));
                NowStockPo nowStockPo =new NowStockPo(dataJsonObject.getString("date"), dataJsonObject.getString("time"), dataJsonObject.getString("gid"), dataJsonObject.getString("increPer"), dataJsonObject.getString("increase"), dataJsonObject.getString("name"), dataJsonObject.getString("todayStartPri"), dataJsonObject.getString("yestodEndPri"), dataJsonObject.getString("nowPri"), dataJsonObject.getString("todayMax"), dataJsonObject.getString("todayMin"), dataJsonObject.getString("competitivePri"), dataJsonObject.getString("reservePri"), dataJsonObject.getString("traNumber"), dataJsonObject.getString("traAmount"), dapandataJsonObject.getString("rate"), dapandataJsonObject.getString("dot"), dapandataJsonObject.getString("nowPic"));
                return nowStockPo;
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }

		return null;
    }
    public  NowDapanPo getShangzhengResult() throws Exception{
        String result =null;
        Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//APP Key
            params.put("type",0);
            result =net(url, params, "GET");
            JSONObject object =new JSONObject(result);
            if(object.getInt("error_code")==0){
                JSONObject resultJsonObject =new JSONObject(object.getString("result"));
                NowDapanPo nowDapanPo=new NowDapanPo(resultJsonObject.getString("name"), resultJsonObject.getString("time"), resultJsonObject.getString("increPer"), resultJsonObject.getString("increase"), resultJsonObject.getString("openPri"), resultJsonObject.getString("yesPri"), resultJsonObject.getString("nowpri"), resultJsonObject.getString("highPri"), resultJsonObject.getString("lowpri"), resultJsonObject.getString("dealNum"), resultJsonObject.getString("dealPri"));
                return nowDapanPo;
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
		return null;
    }
    public  NowDapanPo getShenzhengResult() throws Exception{
        String result =null;
        Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//APP Key
            params.put("type",1);
            result =net(url, params, "GET");
            JSONObject object =new JSONObject(result);
            if(object.getInt("error_code")==0){
            	JSONObject resultJsonObject =new JSONObject(object.getString("result"));
                NowDapanPo nowDapanPo=new NowDapanPo(resultJsonObject.getString("name"), resultJsonObject.getString("time"), resultJsonObject.getString("increPer"), resultJsonObject.getString("increase"), resultJsonObject.getString("openPri"), resultJsonObject.getString("yesPri"), resultJsonObject.getString("nowpri"), resultJsonObject.getString("highPri"), resultJsonObject.getString("lowpri"), resultJsonObject.getString("dealNum"), resultJsonObject.getString("dealPri"));
                return nowDapanPo;
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
       
		return null;
    }
    public  String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public  String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
