package DTO;

public class ByeDTO {

	private String name;
	private String add;
	private String add2;
	private String tel;
	private String x;
	private String y;
	private String stat;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	@Override
	public String toString() {
		return "ByeDTO [name=" + name + ", add=" + add + ", add2=" + add2 + ", tel=" + tel + ", x=" + x + ", y=" + y
				+ ", stat=" + stat + "]";
	}
	
}
