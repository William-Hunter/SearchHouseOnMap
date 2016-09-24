package spider;

import java.io.IOException;
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
	static int number;

	Spider() {
		number = 0;
	}

	public boolean menu(String homeUrl) throws Exception{
		System.out.println("menu begin");
		String href=homeUrl;
		do{
			href=this.list(href);
		}while(href!=null);
		System.out.println("menu end");
		return true;
	}

	public String list(String listUrl) throws Exception {
//		System.out.println("list begin");
		number++;
		System.out.println(number+":"+listUrl);
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
		String result=null;
		if (nextLink != null) {
			result=nextLink.absUrl("href");
		} else {
			result=null;
		}
//		System.out.println("list end");
		return result;
	}

	public void page(String pageUrl) throws Exception {
//		System.out.println("house bengin");		
//		System.out.println(number +":"+ pageUrl);
		Element element = null;
		Elements elements = null;
		House house = new House();
		Document doc = null;

		do {
			doc = Jsoup.connect(pageUrl).timeout(300000).get();// 设置了连接最大超出时间
			element = doc.select("h2").first();
		} while (element == null);
		// element = doc.select("h2").first();
		house.setTitle(element.text());

		element = doc.select("span.price").first();
		house.setPrice(element.text());

		element = doc.select("span.unit").first();
		house.setUnit(element.text());

		elements = doc.select("ul.tags-list>li");
		StringBuilder tags=new StringBuilder();
		for (Element tip : elements) {
			tags.append(tip.text()+",");
		}
		house.setTags(tags.toString());

		elements = doc.select("ul#pic-list img");
		StringBuilder imglist=new StringBuilder();
		for (Element pic : elements) {
			imglist.append(pic.absUrl("lazy_src")+",");
		}
		house.setImgs(imglist.toString());

		element = doc.select("span.tips").first();
		house.setTime(element.text());

		element = doc.select("p#desc").first();
		house.setDescription(element.text());

		elements = doc.select("ul.icon-list li");
		StringBuilder lifearound=new StringBuilder();
		for (Element setup : elements) {
			lifearound.append(setup.text()+",");
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
		StringBuilder houseInfo=new StringBuilder();
		for (Element li : elements) {
			String key = li.select("i.tips").first().text();
			String value = li.select("span").first().text();
			houseInfo.append(key+"="+value+",");
		}
		house.setHouseInfo(houseInfo.toString());

		if(0<Access.insert(house)){
            System.out.println("房子插入成功");
        }
	}

}
