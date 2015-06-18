package wow.anwendungssysteme.user;

import java.util.Collection;
import java.util.LinkedList;

import wow.anwendungssysteme.auction.AuctionRESTRuntimeException;

public class UserManager {

	Collection<User> users = new LinkedList<>();
	private static UserManager instance = new UserManager();
	public static UserManager getInstance() {
		return instance;
	}
	
	private UserManager() {
		users.add(new User("Informatiker", 42));
		users.add(new User("Leonidas", 300));
		users.add(new User("Wilde", 13));
		users.add(new User("Blackjack", 21));
		users.add(new User("Gott", -1));
	}
	
	public User getUser(int id) {
		for (User u: users) {
			if (u.id == id) return u;
		}
		return null;
	}
	
	public boolean deleteUser(int id) {
		return users.remove(getUser(id));
	}
	
	public void addUser(User user) {
		if (users.contains(user)) throw new AuctionRESTRuntimeException("That user already existis");
		else users.add(user);
	}
	public User[] getUsers() {
		User[] result = new User[users.size()];
		int i=0;
		for (User u:users) {
			result[i] = u;
			i++;
		}
		return result;
	}
}
