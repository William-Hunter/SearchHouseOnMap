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
		public static int insert(House house) throws Exception{
			
			StringBuilder sql=new StringBuilder("INSERT INTO ");
			StringBuilder value=new StringBuilder("VALUES(");			
			Class _class=house.getClass();
			sql.append(_class.getSimpleName()+"(");
			Field[] fields=_class.getDeclaredFields();
			Field field=null;
			for(int index=0;index<fields.length;index++){
				sql.append(fields[index].getName());
				value.append("?");
				System.out.println(fields[index].getName());				
				if(index<(fields.length-1)){
					sql.append(",");
					value.append(",");
				}
			}			
			sql.append(") ");
			value.append(")");
			sql.append(value);
			System.out.println(sql);
			
			ps = con.prepareStatement(sql.toString());	
			for(int index=0;index<fields.length;index++){
				System.out.println(fields[index].get(house));
				ps.setObject(index+1, fields[index].get(house));
			}
		
			return ps.executeUpdate();	
		}
		
		

		public static int add() throws ClassNotFoundException, SQLException {
			String sql = "INSERT INTO House(title) VALUES('888')"; // 表名不能作为参数
			ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			return ps.executeUpdate();
		}


}
