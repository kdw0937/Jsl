package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.CartDao;
import model.CartItem;
@Repository
public class CartDaoImpl implements CartDao {
	@Autowired
	private SqlSession session;
	public void insertCart(CartItem cartItem) {
		session.insert(
			"mapper.Cartmapper.insertCart",cartItem);
	}
	public void deleteCart(CartItem cartItem) {
		session.delete(
			"mapper.Cartmapper.deleteCart",cartItem);
	}
	public void updateCart(CartItem cartItem) {
		session.update(
			"mapper.Cartmapper.updateCart",cartItem);
	}
	public Integer selectMaxCart() {
		return session.selectOne(
				"mapper.Cartmapper.selectMaxCart");
	}
	public List<CartItem> selectCart(String id) {
		return session.selectList(
			"mapper.Cartmapper.selectCart",id);
	}
}





