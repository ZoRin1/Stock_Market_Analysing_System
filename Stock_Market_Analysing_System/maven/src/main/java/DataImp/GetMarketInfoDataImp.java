package DataImp;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataSer.GetMarketInfoDataSer;
import PO.MarketlistPO;
import PO.MarketonePO;
import PO.StockOnePO;

public class GetMarketInfoDataImp implements GetMarketInfoDataSer {
	private static String URL="jdbc:mysql://127.0.0.1:3306/websql";
	private static String DRIVER="com.mysql.jdbc.Driver";
	private static String USER="root";
	private static  String PASSWORD="123456";
	private String sql;
	@Override
	public List<MarketlistPO> getMarketList() {
		// TODO Auto-generated method stub
		sql="select * from marketlist";
		List<MarketlistPO> marketlist=new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				MarketlistPO marketlistPO=new MarketlistPO(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5),  resultSet.getDouble(6),  resultSet.getDouble(7),  resultSet.getDouble(8),  resultSet.getDouble(9), resultSet.getString(10));
				marketlist.add(marketlistPO);
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketlist;
	}

	@Override
	public List<MarketonePO> getMarketone(String code, String start, String end) {
		// TODO Auto-generated method stub
		String marketname = "";
		switch (code) {
		case "000001":
			marketname="sh";
			break;
		case "399001":
			marketname="sz";
			break;
		case "399300":
			marketname="hs300";
			break;
		case "000016":
			marketname="sz50";
			break;
		case "399005":
			marketname="zxb";
			break;
		case "399006":
			marketname="cyb";
			break;
		}
		sql="select * from "+marketname+" where date between '"+start+"' and '"+end+"' order by date asc";
		List<MarketonePO> marketonelist=new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				marketonelist.add(new MarketonePO(resultSet.getString(1), resultSet.getDouble(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getDouble(10), resultSet.getDouble(11), resultSet.getDouble(12), resultSet.getDouble(13), resultSet.getDouble(14)));
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketonelist;
	}

	@Override
	public MarketlistPO getNewMarketList(String code) {
		// TODO Auto-generated method stub
		sql="select * from marketlist where code='"+code+"'";
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				MarketlistPO marketlistPO= new MarketlistPO(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5),  resultSet.getDouble(6),  resultSet.getDouble(7),  resultSet.getDouble(8),  resultSet.getDouble(9), resultSet.getString(10));
				connection.close();
				return marketlistPO;
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
