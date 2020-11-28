package dao;

import model.User;

public interface LoginDao {
	void entryUser(User user);
	User getUser(String id);
	String getPassword(String id);
	Integer getIdCount(String id);
}
