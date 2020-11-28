package dao;

import java.util.List;

import model.CartItem;

public interface CartDao {
	void insertCart(CartItem cartItem);
	void deleteCart(CartItem cartItem);
	void updateCart(CartItem cartItem);
	Integer selectMaxCart();
	List<CartItem> selectCart(String id);
}









