package dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spider.House;

public class Access {	
		static Logger logger = LoggerFactory.getLogger(Access.class);
		static String sql;
		static ResultSet rs;
		static Connection con;
		static PreparedStatement ps;

		public Access() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/searchhouse", "root", "root");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		public static boolean isContect() throws ClassNotFoundException {
			try {
				if (!con.isClosed()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		//调用私用属性会产生意外
		public static boolean insert(House house) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
			StringBuilder sql=new StringBuilder("INSERT INTO ");
			StringBuilder value=new StringBuilder("value(");
			
//			"INSERT INTO House(...) value(...)"
			
			Class _class=house.getClass();
//			_class.getTypeName();
			sql.append(_class.getSimpleName()+"(");
			Field[] fields=_class.getDeclaredFields();
			for(Field field:fields){
				sql.append(","+field.getName());	
				value.append(",'?'");		
			}
			
			
			sql.append(") ");
			value.append(")");
			
			sql.append(value);
			System.out.println(sql);
			

			
			
			return false;
			
		}
		
		
		

		public static void show(ResultSet resultSet) throws SQLException {
			System.out.println("The result is:\n-------------------------");
			resultSet.first();
			do {
				System.out.println("id:" + resultSet.getInt("id") + "\tname:" + resultSet.getString("name"));
			} while (resultSet.next());
			System.out.println();
		}

		public static ResultSet select(int key) throws ClassNotFoundException, SQLException {
			sql = "SELECT * FROM one WHERE id= ? "; // 表名不能作为参数
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			return ps.executeQuery();
		}


}
