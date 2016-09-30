package controller;
import listener.AppListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.text.normalizer.UTF16;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Search extends HttpServlet {
    Logger logger= LoggerFactory.getLogger(Search.class);

    public Search(){
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){


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
        String radius=request.getParameter("radius");
        String location=request.getParameter("location");

        PrintWriter out = response.getWriter();
        try {
            String json= AppListener.access.select(minprice,maxprice,radius,location);
            logger.debug("json:"+json);
            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
