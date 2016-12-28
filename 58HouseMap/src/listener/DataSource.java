package listener;

import dao.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class DataSource implements ServletContextListener {
	Logger logger= LoggerFactory.getLogger(DataSource.class);

	public static Access access;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			access=new Access();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(access.isContect()){
				logger.info("数据库已经连接");
            }else{
				logger.info("数据库连接失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			if(access.shutDown()){
				logger.info("已经断开与数据库的连接");
            }else{
				logger.info("数据库断开失败");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
