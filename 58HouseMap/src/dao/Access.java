package dao;

import bean.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Access {
    Logger logger = LoggerFactory.getLogger(Access.class);
    static StringBuilder sql;
    static ResultSet rs;
    public static Connection con;
    static PreparedStatement ps;
//    static DataSource ds;

    public Access() throws ClassNotFoundException, IOException, SQLException {           //初始化连接数据库
//        Context context = new InitialContext();
//        context = (Context) context.lookup("java:/comp/env");
//        ds = (DataSource) context.lookup("jdbc/MysqlTest");
//        con = ds.getConnection();
        getConnection();

    }

    public static void getConnection() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties prop = new Properties();
        InputStream in =Access.class.getClassLoader().getResourceAsStream("db.properties");
        prop.load(in);                                                         //将属性文件流装载到Properties对象中
        con = DriverManager.getConnection(prop.getProperty("mysql.url"), prop.getProperty("mysql.user"),
                                          prop.getProperty("mysql.passwd"));
    }


    public boolean isContect() throws SQLException {           //测试是否连接
            return !con.isClosed()?true:false;
    }

    static boolean keepConnection() throws SQLException {
        try{
            ps=con.prepareStatement("SELECT 1;");
            rs = ps.executeQuery();
        }catch (SQLException e){
            getConnection();
            con.setAutoCommit(false);
            e.printStackTrace();
        }finally {
            return !con.isClosed();
        }
    }

    public boolean shutDown() throws SQLException {      //关闭连接
        con.close();
        return con.isClosed();
    }

    public void rollback() throws SQLException {         //事物回滚
        con.rollback();
    }

    public void commit() throws SQLException {           //手动提交
        con.commit();
    }

    //调用私用属性会产生意外
    public int insert(House house) throws Exception {
        keepConnection();
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
//            logger.debug(fields[index].getName()+":"+fields[index].get(house));         //字段值打印
            ps.setObject(index + 1, fields[index].get(house));
        }
        return ps.executeUpdate();
    }

    /**
     * 获取合适价位的房子信息，组织成一个list
     */
    public List<House> select(String minprice, String maxprice) throws SQLException, IllegalAccessException {
        keepConnection();
        logger.info("-----------------------------------------------");
        logger.info(this.getClass().getName() + ".select()");
        sql = new StringBuilder("SELECT ids,title,price,imgs,lat,lon,time,phone,unit,houseInfo,URL FROM House WHERE price<="
                                        + maxprice + " AND price>=" + minprice + ";");
        logger.debug("SQL:" + sql.toString());
        ps = con.prepareStatement(sql.toString());
        rs = ps.executeQuery();                                  //得到结果集

        House house = new House();                               //实例一个house对象，获取其中的属性\

        Class _class = house.getClass();
        Field[] fields = _class.getDeclaredFields();

        List<House> list = new LinkedList<House>();
        try {
            for (rs.first(); !rs.isAfterLast(); rs.next()) {        //将房子信息添加到list
                house = new House();
                for (Field field : fields) {
                    try {
                        rs.getString(field.getName());
                    } catch (SQLException e) {
                        continue;
                    }
                    field.set(house, rs.getString(field.getName()));
                }
                list.add(house);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        }

        logger.info("-----------------------------------------------");
        return list;
    }


}
