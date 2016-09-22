package spider;

import java.io.IOException;

import dao.Access;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		Spider spider=new Spider();
		if(!spider.access.isContect()){
			System.out.println("Database Contect fail");
			return ;
		}
//		spider.menu("http://bj.58.com/pinpaigongyu/");
		
//		spider.page("http://bj.58.com/pinpaigongyu/27005923305662x.shtml?iuType=p_1&PGTID=0d3111f6-0000-181f-a85d-65f13e3d89b4&ClickID=1");

		
//		;
		
		System.out.println("END"+spider.access.add());
	}

}
