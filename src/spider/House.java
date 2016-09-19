package spider;

import java.util.List;

public class House {
	private String title;
	private String price;
	private String unit;
	private List<String> tip;
	private List<String> img;
	private String date;
	private String descript;
	private List<String> setup;
	private String lat;
	private String lon;
	private List<String> lifearound;
	
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
	public List<String> getTip() {
		return tip;
	}
	public void setTip(List<String> tip) {
		this.tip = tip;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	public List<String> getSetup() {
		return setup;
	}
	public void setSetup(List<String> setup) {
		this.setup = setup;
	}
	public List<String> getLifearound() {
		return lifearound;
	}
	public void setLifearound(List<String> lifearound) {
		this.lifearound = lifearound;
	}
}
