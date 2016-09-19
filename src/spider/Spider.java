package spider;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Spider {
	static Logger logger = LoggerFactory.getLogger(Spider.class);
	static List<House> list;
	
	Spider(){
		list=new LinkedList<House>();
	}
	
	
	public static void list(String listUrl) throws IOException{
		System.out.println("list:"+listUrl);		
		Document doc=Jsoup.connect(listUrl).timeout(300000).get();//设置了连接最大超出时间
		Elements es_list=doc.select("ul.list a");
		for(Element link:es_list){
			String pageUrl=link.absUrl("href");			
			page(pageUrl);
		}
		
	}
	
	public static void page(String pageUrl) throws IOException{
		System.out.println("house:"+pageUrl);
		Element element=null;
		Elements elements=null;
		
		House house=new House();
		Document doc=Jsoup.connect(pageUrl).timeout(300000).get();//设置了连接最大超出时间
		
		element=doc.select("h2").first();
		house.setTitle(element.text());
//		System.out.println("title:"+title.text());
		
		element=doc.select("span.price").first();
		house.setPrice(element.text());
		
		element=doc.select("span.unit").first();
		house.setUnit(element.text());
		
		elements=doc.select("ul.tags-list>li");
		List<String> tips=new LinkedList<String>(); 
		for(Element tip: elements){
			tips.add(tip.text());
		}
		house.setTip(tips);
		
		
		Elements elements=doc.select("#pic-list img");
		List<String> imglist=new LinkedList<String>();
		for(Element pic:elements){
//			System.out.println("picture:"+pic.absUrl("lazy_src"));
			imglist.add(pic.absUrl("lazy_src"));
		}		
		house.setImg(imglist);
		
		
		
		
		
		System.out.println("-------------------------------------------------");
	}
	
	
	
	
}
