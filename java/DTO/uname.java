package DTO;

public class uname {
String name=null;


	private uname() {
		
	}
	private static uname instance=new uname();
	
	public static uname getInstance() {
		return instance;
	}
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}
