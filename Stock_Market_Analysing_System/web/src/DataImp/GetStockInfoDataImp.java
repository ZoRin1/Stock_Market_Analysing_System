package DataImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataSer.GetStockInfoDataSer;
import PO.MarketlistPO;
import PO.MarketonePO;
import PO.StockListPO;
import PO.StockOnePO;

public class GetStockInfoDataImp implements GetStockInfoDataSer {
	private static String URL="jdbc:mysql://127.0.0.1:3306/websql";
	private static String DRIVER="com.mysql.jdbc.Driver";
	private static String USER="root";
	private static  String PASSWORD="123456";
	private String sql;
	@Override
	public List<StockListPO> getStockList(String code) {
		// TODO Auto-generated method stub
		sql="select * from "+code;
		List<StockListPO> stockList=new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				StockListPO stock=new StockListPO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5),  resultSet.getDouble(6),  resultSet.getDouble(7),  resultSet.getDouble(8),  resultSet.getDouble(9),resultSet.getDouble(10),resultSet.getDouble(11),resultSet.getDouble(12),resultSet.getDouble(13),resultSet.getDouble(14),resultSet.getDouble(15),resultSet.getDouble(16),resultSet.getDouble(17),resultSet.getDouble(18),resultSet.getDouble(19),resultSet.getDouble(20), resultSet.getString(21));
				stockList.add(stock);	
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockList;
	}

	@Override
	public List<StockOnePO> getStockOne(String code, String start, String end) {
		// TODO Auto-generated method stub
		sql="select * from s"+code+" where date between '"+start+"' and '"+end+"'  order by date asc";
		List<StockOnePO> stockOne=new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				stockOne.add(new StockOnePO(resultSet.getString(1), resultSet.getDouble(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getDouble(10), resultSet.getDouble(11), resultSet.getDouble(12), resultSet.getDouble(13), resultSet.getDouble(14), resultSet.getDouble(15)));
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockOne;
	}

	@Override
	public StockListPO getNewStockList(String code) {
		// TODO Auto-generated method stub
		String str="";
		switch (code.charAt(0)) {
		case '6':
			str="sha";
			break;
		case '9':
			str="shb";
			break;
		case '0':
			str="sza";
			break;
		case '2':
			str="szb";
			break;
		default:
			break;
		}
		sql="select * from "+str+" where code='"+code+"'";
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				StockListPO stock=new StockListPO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5),  resultSet.getDouble(6),  resultSet.getDouble(7),  resultSet.getDouble(8),  resultSet.getDouble(9),resultSet.getDouble(10),resultSet.getDouble(11),resultSet.getDouble(12),resultSet.getDouble(13),resultSet.getDouble(14),resultSet.getDouble(15),resultSet.getDouble(16),resultSet.getDouble(17),resultSet.getDouble(18),resultSet.getDouble(19),resultSet.getDouble(20), resultSet.getString(21));
				connection.close();
				return stock;
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
