package controller;
import dao.Access;
import listener.AppListener;
import org.apache.log4j.Logger;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Search extends HttpServlet {
    Logger logger=Logger.getLogger(Search.class);

    public Search(){
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,String[]>map=request.getParameterMap();
        Set<String> set=map.keySet();
        StringBuilder info=new StringBuilder();
        for(String key:set){
            info.append(key+"="+map.get(key)+",");
        }
        logger.debug("dopost(\n"+info+")");

        String minprice=request.getParameter("minprice");
        String maxprice=request.getParameter("maxprice");
        String radius=request.getParameter("radius");
        String location=request.getParameter("location");

        System.out.println("post:"+"minprice="+minprice+"\tmaxprice="+maxprice+"\tradius="+radius+"\tlocation="+location);

        PrintWriter out = response.getWriter();
        try {
            String json= AppListener.access.select(minprice,maxprice,radius,location);
            System.out.println("back:"+json);
            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
