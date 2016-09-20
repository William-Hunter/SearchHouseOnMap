package spider;

import java.io.IOException;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Spider spider=new Spider();
//		spider.menu("http://bj.58.com/pinpaigongyu/");
		
		spider.page("http://bj.58.com/pinpaigongyu/27005923305662x.shtml?iuType=p_1&PGTID=0d3111f6-0000-181f-a85d-65f13e3d89b4&ClickID=1");

//		List<House> list=Spider.list;
		System.out.println("END");
	}

}
