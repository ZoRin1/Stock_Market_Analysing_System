package DataImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataSer.LoginDataSer;

public class LoginDataImp implements LoginDataSer {
	private static String URL="jdbc:mysql://127.0.0.1:3306/websql";
	private static String DRIVER="com.mysql.jdbc.Driver";
	private static String USER="root";
	private static  String PASSWORD="123456";
	private String sql;
	@Override
	public boolean signup(String cid, String password) {
		// TODO Auto-generated method stub
		sql="select cid from customers where cid='"+cid+"'";
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				connection.close();
				return false;
			}
			else {
				sql="insert into customers(cid,password) values('"+cid+"','"+password+"')";
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.execute();
				connection.close();
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean login(String cid, String password) {
		// TODO Auto-generated method stub
		sql="select cid from customers where cid='"+cid+"' and password='"+password+"'" ;
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				connection.close();
				return true;
			}
			else {
				connection.close();
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public void raise(String cid, String sid, String date, double money, double price) {
		// TODO Auto-generated method stub
		sql="select cid from customersown where cid='"+cid+"' and sid='"+sid+"'";
				try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				sql="update customersown set date='"+date+"',money="+money+",price="+price+" where cid='"+cid+"' and sid='"+sid+"'";
			}
			else {
				sql="insert into customersown (cid,sid,date,money,price) values ('"+cid+"','"+sid+"','"+date+"',"+money+","+price+")";
			}
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.close();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void changePass(String cid, String password) {
		// TODO Auto-generated method stub
		sql="update customers set password='"+password+"' where cid='"+cid+"'";
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.close();		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getOwnStock(String cid) {
		// TODO Auto-generated method stub
		sql="select sid from customersown where cid='"+cid+"'" ;
		List<String> strings=new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();			
			while (resultSet.next()) {
				strings.add(resultSet.getString(1));				
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strings;
	}

	@Override
	public String[] getOwnSet(String cid, String sid) {
		// TODO Auto-generated method stub
		sql="select date,money,price from customersown where cid='"+cid+"' and sid='"+sid+"'";
		String[] strings=new String[3];
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();		
			resultSet.next();
			strings[0]=resultSet.getString(1);
			strings[1]=Double.toString(resultSet.getDouble(2));
			strings[2]=Double.toString(resultSet.getDouble(3));
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strings;
	}

	@Override
	public void delete(String cid, String sid) {
		// TODO Auto-generated method stub
		sql="delete from customersown where cid='"+cid+"' and sid='"+sid+"'";
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
