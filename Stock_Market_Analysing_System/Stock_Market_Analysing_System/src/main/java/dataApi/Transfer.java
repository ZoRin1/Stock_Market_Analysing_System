package dataApi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import dataService.TransferDataSer;

public class Transfer implements TransferDataSer{
	BufferedReader reader1 = null;
	BufferedReader reader2 = null;
	
//	public Transfer() {
//		super();
//		try {
//			reader1=new BufferedReader(new FileReader(
//					"src/main/java/dataApi/沪市.txt"));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	
//		try {
//			reader2=new BufferedReader(new FileReader(
//					"src/main/java/dataApi/深市.txt"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@SuppressWarnings("resource")
	@Override
	public String transfer1(String name) {
		// TODO Auto-generated method stub
		String tempString="";
		String result="";
		try {
			reader1=new BufferedReader(new InputStreamReader(new FileInputStream("text/沪市.txt"),"UTF-8"));
			reader2=new BufferedReader(new InputStreamReader(new FileInputStream("text/深市.txt"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while((tempString = reader1.readLine()) != null){
				String[] str1=tempString.split(" ");
				if(str1[1].equals("600000")&&name.equals("浦发银行")){
					result="sh"+str1[1];
					break;
				}
				if(name.equals(str1[0])){
					result="sh"+str1[1];
					break;
				}
			}
			while((tempString = reader2.readLine()) != null){
				String[] str1=tempString.split(" ");
				if(str1[1].equals("000005")&&name.equals("世纪星源")){
					result="sz"+str1[1];
					break;
				}
				if(str1[0].equals(name)){
					result="sz"+str1[1];
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				reader1.close();
				reader2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


	@Override
	public String transfer2(String code) {
		// TODO Auto-generated method stub
		String tempString="";
		String result="";
		
		String str1=code.substring(0, 2);
		String code1=code.substring(2);
		try {
			reader1=new BufferedReader(new InputStreamReader(new FileInputStream("text/沪市.txt"),"UTF-8"));
			reader2=new BufferedReader(new InputStreamReader(new FileInputStream("text/深市.txt"),"UTF-8"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(str1.equals("sh")){
			try {
				while((tempString = reader1.readLine()) != null){
					String[] list=tempString.split(" ");
					if(list[1].equals(code1)){
						result=list[0];
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally{
				try {
					reader1.close();
					reader2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			try {
				while((tempString = reader2.readLine()) != null){
					String[] list=tempString.split(" ");
					if(list[1].equals(code1)){
						result=list[0];
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally{
				try {
					reader1.close();
					reader2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}


	/* (non-Javadoc)
	 * @see dataService.TransferDataSer#transfer3(java.lang.String)
	 */
	@Override
	public String transfer3(String code) {
		// TODO Auto-generated method stub
		String tempString="";
		String result="";
		
		String str1=code.substring(0, 2);
		String code1=code.substring(2);
		try {
			reader1=new BufferedReader(new InputStreamReader(new FileInputStream("text/沪市.txt"),"UTF-8"));
			reader2=new BufferedReader(new InputStreamReader(new FileInputStream("text/深市.txt"),"UTF-8"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(str1.equals("sh")){
			try {
				while((tempString = reader1.readLine()) != null){
					String[] list=tempString.split(" ");
					if(list[1].equals(code1)){
						result=list[2];
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally{
				try {
					reader1.close();
					reader2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			try {
				while((tempString = reader2.readLine()) != null){
					String[] list=tempString.split(" ");
					if(list[1].equals(code1)){
						result=list[2];
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally{
				try {
					reader1.close();
					reader2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	
}
