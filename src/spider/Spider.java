package spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Spider {
	static Logger logger = LoggerFactory.getLogger(Spider.class);
	
	
	public static void list(String listUrl) throws IOException{
		logger.info("pageUrl:"+listUrl);		
		Document doc=Jsoup.connect(listUrl).timeout(300000).get();//设置了连接最大超出时间
		Elements es_list=doc.select("ul.list a");
		for(Element link:es_list){
			String pageUrl=link.absUrl("href");
			logger.info(pageUrl+"\n------------------------------------------------");
			page(pageUrl);
		}
		
	}
	
	public static void page(String pageUrl){
		
		
	}
	
	
	
	
}
