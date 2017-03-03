/**
 * 
 */
package dataApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.sun.javafx.css.CssError.StringParsingError;

import dataService.RaiseDataSer;

/**
 * @author lenovo
 *
 */
public class RaiseStock implements RaiseDataSer{

	/* (non-Javadoc)
	 * @see dataService.RaiseDataSer#raise(java.lang.String)
	 */
	@Override
	public void raise(String code) {
		// TODO Auto-generated method stub

		OutputStreamWriter  writer=null;
		try {
			writer=new OutputStreamWriter(new FileOutputStream("text/自选股.txt",true),"UTF-8") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transfer trans=new Transfer();
		String str=trans.transfer2(code);
		String str2=trans.transfer3(code);
		//System.out.println(str.length());
		try {
			String str1=str+" "+code+" "+str2+"\n";
			writer.write(str1.toCharArray());
			//.write(str+" "+code+"/n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean checkExist(String code){
		String tempString="";
		BufferedReader reader = null;
		boolean result=false;
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/自选股.txt"),"UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while((tempString = reader.readLine()) != null){
				String[] split=tempString.split(" ");
				if(split.length<2){
					break;
				}
				if(split[1].equals(code)){
					result=true;
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;	
	}

	/* (non-Javadoc)
	 * @see dataService.RaiseDataSer#delete(java.lang.String)
	 */
	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/自选股.txt"),"UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileOutputStream writer=null;
		try {
			writer=new FileOutputStream("text/自选股.txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader reader1=null;
		try {
			reader1=new BufferedReader(new InputStreamReader(new FileInputStream("text/自选股1.txt"),"UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OutputStreamWriter writer1=null;
		try {
			writer1=new OutputStreamWriter(new FileOutputStream("text/自选股1.txt",true), "UTF-8") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(checkExist(code)){
			String tempString="";
			try {
				while((tempString = reader.readLine()) != null){
					String[] split=tempString.split(" ");
					if(!split[1].equals(code)){
						String tempString1=tempString+"\n";
						writer1.write(tempString1.toCharArray());
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FileWriter writer2=null;
			try {
				writer2 = new FileWriter("text/自选股.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				writer2.write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while((tempString = reader1.readLine()) != null){
					String tempString1=tempString+"\n";
					writer.write(tempString1.getBytes());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FileWriter writer3=null;
			try {
				writer3 = new FileWriter("text/自选股1.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer3.write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					writer3.close();
					writer2.close();
					reader.close();
					reader1.close();
					writer.close();
					writer1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
}
