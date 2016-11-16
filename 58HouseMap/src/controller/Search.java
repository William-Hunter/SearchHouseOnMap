package controller;
import bean.House;
import com.google.gson.Gson;
import listener.AppListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.text.normalizer.UTF16;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Search extends HttpServlet {
    Logger logger= LoggerFactory.getLogger(Search.class);
    static BigDecimal ANGLE=new BigDecimal(111.111);

    public Search(){
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        BigDecimal lat=new BigDecimal(request.getParameter("lat"));
        BigDecimal lon=new BigDecimal(request.getParameter("lon"));
        BigDecimal range=rangeToAngle(request.getParameter("radius"));

        try {
            List<House> list= AppListener.access.select(minprice,maxprice);
            for(House e:list){
                BigDecimal Lat=new BigDecimal(e.getLat());          //获取当前房屋记录的座标
                BigDecimal Lon=new BigDecimal(e.getLon());
                //计算这个座标是否在半径范围之内

            }

            Gson gson=new Gson();                  //转化成json
            PrintWriter out = response.getWriter();
//            logger.debug("json:"+json);
//            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BigDecimal rangeToAngle(String range){
         return new BigDecimal(range).divide(ANGLE);
    }


}
