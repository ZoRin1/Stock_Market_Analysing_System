package DataImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataSer.GetIndustryDataSer;
import PO.StockListPO;

public class GetIndustryDataImp implements GetIndustryDataSer {
	private static String URL="jdbc:mysql://127.0.0.1:3306/websql";
	private static String DRIVER="com.mysql.jdbc.Driver";
	private static String USER="root";
	private static  String PASSWORD="123456";
	private String sql;
	@Override
	public List<StockListPO> getIndustryData(String market, String industry) {
		// TODO Auto-generated method stub
		sql="select * from "+market+" where industry='"+industry+"'";
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

}
