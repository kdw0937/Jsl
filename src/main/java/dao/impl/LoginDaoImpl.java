package dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.LoginDao;
import model.User;
@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private SqlSession session;
	public void entryUser(User user) {
		session.insert(
				"mapper.Usermapper.createUser",user);
	}
	public User getUser(String id) {
		return session.selectOne(
				"mapper.Usermapper.selectMember",id);
	}
	public String getPassword(String id) {
		return session.selectOne(
				"mapper.Usermapper.getPassword",id);
	}
	public Integer getIdCount(String id) {
		return session.selectOne(
				"mapper.Usermapper.getIdCount",id);
	}
}






