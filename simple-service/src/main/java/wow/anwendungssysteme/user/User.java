package wow.anwendungssysteme.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	public String name;
	public int id;
	
	public User() {
		
	}
	
	public User(String name, int id) {
		this.name = name;
		this.id= id;
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof User) return ((User) o).id == id;
		else return false;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString(){
		return "Id: " + this.id  + " name: " + this.name;
	}
}
