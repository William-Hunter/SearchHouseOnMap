package controller;
import bean.House;
import com.google.gson.Gson;
import listener.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Search extends HttpServlet {
    Logger logger= LoggerFactory.getLogger(Search.class);
    static double ANGLE=111.111;

    public Search(){
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");

        Map<String,String[]>map=request.getParameterMap();
        Set<String> set=map.keySet();
        StringBuilder info=new StringBuilder();
        for(String key:set){
            info.append(key+"="+request.getParameter(key)+"\t");
        }
        logger.debug("dopost("+info+")");

        String minprice=request.getParameter("minprice");
        String maxprice=request.getParameter("maxprice");
        double lat=Double.valueOf(request.getParameter("lat"));
        double lon=Double.valueOf(request.getParameter("lon"));
        double range=rangeToAngle(request.getParameter("radius"));

        List<House> list=null;
        try {
            list= DataSource.access.select(minprice,maxprice);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<House> shitlist =new ArrayList<House>();
        if(list!=null){
            for(House e:list){
                double Lat=Double.valueOf(e.getLat());          //获取当前房屋记录的座标
                double Lon=Double.valueOf(e.getLon());
                if(Math.pow(Lat-lat,2)+Math.pow(Lon-lon,2)>Math.pow(range,2)){           //计算这个座标是否在半径范围之内
                    shitlist.add(e);
                }
            }
            list.removeAll(shitlist);
        }
        Gson gson=new Gson();                  //转化成json
        PrintWriter out = response.getWriter();
        logger.debug("json:"+gson.toJson(list));
        out.write(gson.toJson(list));

    }

    double rangeToAngle(String range){
        return Double.valueOf(range)/ANGLE;
    }


}
