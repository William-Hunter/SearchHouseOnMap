package spider;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.Access;

public class Spider {
    static Logger logger = LoggerFactory.getLogger(Spider.class);
    static int listNumber;
    static int pageNumber;

    Spider() {
        listNumber = 0;
        pageNumber = 0;
    }

    public boolean menu(String homeUrl) throws Exception {
        String href = homeUrl;
        do {
            href = this.list(href);
        } while (href != null);
        return true;
    }

    public String list(String listUrl) throws Exception {
        listNumber++;
        System.out.println("list" + listNumber + ":" + listUrl);
        Document doc = Jsoup.connect(listUrl).timeout(300000).get();// 设置了连接最大超出时间
        Elements es_list = doc.select("ul.list a");
        if (es_list == null) {
            return null;
        }
        for (Element link : es_list) {
            String pageUrl = link.absUrl("href");
            page(pageUrl);
        }
        Element nextLink = doc.select("a.next").first();
        String result = null;
        if (nextLink != null) {
            result = nextLink.absUrl("href");
        } else {
            result = null;
        }
        return result;
    }

    public void page(String pageUrl) throws Exception {
        pageNumber++;
        System.out.println("page" + pageNumber + ":" + pageUrl);
        Element element = null;
        Elements elements = null;
        House house = new House();
        Document doc = null;

        boolean timeout=false;
        do {
            try {
                doc = Jsoup.connect(pageUrl).timeout(300000).get();// 设置了连接最大超出时间
            } catch (SocketTimeoutException|ConnectException e) {
                timeout=true;
            }
            element = doc.select("h2").first();
        } while (element == null||timeout);
        house.setTitle(element.text());

        element = doc.select("span.price").first();
        house.setPrice(element.text());

        element = doc.select("span.unit").first();
        house.setUnit(element.text());

        elements = doc.select("ul.tags-list>li");
        StringBuilder tags = new StringBuilder();
        for (Element tip : elements) {
            tags.append(tip.text() + ",");
        }
        house.setTags(tags.toString());

        elements = doc.select("ul#pic-list img");
        StringBuilder imglist = new StringBuilder();
        for (Element pic : elements) {
            imglist.append(pic.absUrl("lazy_src") + ",");
        }
        house.setImgs(imglist.toString());

        element = doc.select("span.tips").first();
        house.setTime(element.text());

        elements = doc.select("ul.icon-list li");
        StringBuilder lifearound = new StringBuilder();
        for (Element setup : elements) {
            lifearound.append(setup.text() + ",");
        }
        house.setLifearound(lifearound.toString());

        element = doc.select("script[type=\"text/javascript\"]").first();
        String script = element.toString();
        String lon = script.split("lon")[1];
        lon = lon.split(";")[0];
        lon = lon.replace("=", "");
        lon = lon.replace("'", "");
        lon = lon.trim();
        house.setLon(lon);
        String lat = script.split("lat")[1];
        lat = lat.split(";")[0];
        lat = lat.replace("=", "");
        lat = lat.replace("'", "");
        lat = lat.trim();
        house.setLat(lat);

        element = doc.select("div.phonenum").first();
        house.setPhone(element.text());

        elements = doc.select("ul.house-info-list>li");
        StringBuilder houseInfo = new StringBuilder();
        for (Element li : elements) {
            String key = li.select("i.tips").first().text();
            String value = li.select("span").first().text();
            houseInfo.append(key + "=" + value + ",");
        }
        house.setHouseInfo(houseInfo.toString());

        try {
            if (0 < Access.insert(house)) {
                Access.commit();
                System.out.println("租房信息保存完毕");
            }
        } catch (SQLException e) {
            Access.rollback();
        }

        Thread.sleep(1000);          //线程睡眠一秒，降低访问频率

    }

}
