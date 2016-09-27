package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.House;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Access {
    static Logger logger = LoggerFactory.getLogger(Access.class);
    static StringBuilder sql;
    static ResultSet rs;
    public static Connection con;
    static PreparedStatement ps;

    public Access() {           //初始化连接数据库
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/searchhouse", "root", "root");
            con.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isContect() throws ClassNotFoundException {           //测试是否连接
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
    public static boolean shutDown() throws SQLException {      //关闭连接
        con.close();
        return con.isClosed();
    }

    public static void rollback() throws SQLException {         //事物回滚
        con.rollback();
    }
    public static void commit() throws SQLException {           //手动提交
        con.commit();
    }

    //调用私用属性会产生意外
    public static int insert(House house) throws Exception {
        sql = new StringBuilder("INSERT INTO ");
        StringBuilder value = new StringBuilder("VALUES(");
        Class _class = house.getClass();
        sql.append(_class.getSimpleName() + "(");
        Field[] fields = _class.getDeclaredFields();
        for (int index = 0; index < fields.length; index++) {
            sql.append(fields[index].getName());
            value.append("?");
            if (index < (fields.length - 1)) {
                sql.append(",");
                value.append(",");
            }
        }
        sql.append(") ");
        value.append(")");
        sql.append(value);
        System.out.println(sql);

        ps = con.prepareStatement(sql.toString());
        for (int index = 0; index < fields.length; index++) {
//            System.out.println(fields[index].getName()+":"+fields[index].get(house));         //字段值打印
            ps.setObject(index + 1, fields[index].get(house));
        }

        return ps.executeUpdate();
    }

    public static String select(String minprice, String maxprice, String radius, String location) throws SQLException, IllegalAccessException {
        System.out.println("select");

        String[] locations= location.split(",");
        MathContext param_lon=new MathContext(locations[0]);
        MathContext param_lat=new MathContext(locations[1]);

        sql = new StringBuilder("SELECT * FROM House WHERE price<'"+maxprice+"' AND price>'"+minprice+"'");
        ps = con.prepareStatement(sql.toString());
        rs=ps.executeQuery();

        System.out.println(sql);

        List<House> list=new LinkedList<House>();
        House house=null;
        Class _class=house.getClass();
        Field[] fields= _class.getDeclaredFields();

        for(rs.first();!rs.isAfterLast();rs.next()){
            house=new House();
            for(Field field:fields){
                field.set(house,rs.getString(field.getName()));
            }
            list.add(house);
        }
        Gson gson=new Gson();
        System.out.println("gson:"+gson.toJson(list));
        return gson.toJson(list);
    }


}
