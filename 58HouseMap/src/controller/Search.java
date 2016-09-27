package controller;
import dao.Access;

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

    public Search(){
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String minprice=request.getParameter("minprice");
        String maxprice=request.getParameter("maxprice");
        String radius=request.getParameter("radius");
        String location=request.getParameter("location");

        System.out.println("post:"+"minprice="+minprice+"\tmaxprice="+maxprice+"\tradius="+radius+"\tlocation="+location);

        PrintWriter out = response.getWriter();
        try {
            String json=Access.select(minprice,maxprice,radius,location);
            System.out.println("back:"+json);
            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
