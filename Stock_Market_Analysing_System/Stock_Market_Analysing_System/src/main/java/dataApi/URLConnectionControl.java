package dataApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionControl {
	private HttpURLConnection connection;
	public URLConnectionControl(String URL) throws Exception{
			URL url = new URL(URL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-Auth-Code", "25751c90873199f655032c8b6acd6275");
			connection.connect();
		
	}

	public String getResult() throws Exception{
		String result="";
		result = getInput(connection.getInputStream());
		return result;
	}
	public String getInput(InputStream input) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String str = "";
		String result = "";
		StringBuffer sb = new StringBuffer();

			while((str = reader.readLine())!=null){
				sb.append(str);
			}
			reader.close();
			result = sb.toString();
//			System.out.println(result);

		return result;
	}
	protected void finalize(){
		InputStream is;
		try {
			is = connection.getInputStream();
			  is.close();
			  connection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	}
}
