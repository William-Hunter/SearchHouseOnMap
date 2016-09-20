package spider;

import java.util.List;
import java.util.Map;

public class House {
	private String title;
	private String price;
	private String unit;
	private List<String> tags;
	private List<String> imgs;
	private String date;
	private String descript;
	private String lat;
	private String lon;
	private List<String> lifearound;
	private String phone;
	private Map<String,String> houseInfo;
	
	public Map<String, String> getHouseInfo() {
		return houseInfo;
	}
	public void setHouseInfo(Map<String, String> houseInfo) {
		this.houseInfo = houseInfo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public List<String> getLifearound() {
		return lifearound;
	}
	public void setLifearound(List<String> lifearound) {
		this.lifearound = lifearound;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	
}
