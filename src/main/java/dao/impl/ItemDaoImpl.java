package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.ItemDao;
import model.Condition;
import model.Item;
@Repository
public class ItemDaoImpl implements ItemDao {
	@Autowired
	private SqlSession session;
	public void putItem(Item item) {
		session.insert("mapper.Itemmapper.putItem",item);
	}
	public List<Item> getItems(Condition c) {
		return session.selectList(
				"mapper.Itemmapper.getItemList",c);
	}
	public Integer getItemCount() {
		return session.selectOne(
				"mapper.Itemmapper.getItemCount");
	}
	public Item getItem(String id) {
		return session.selectOne(
				"mapper.Itemmapper.getItem",id);
	}

}


