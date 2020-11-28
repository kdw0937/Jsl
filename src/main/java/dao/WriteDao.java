package dao;

import model.Bbs;

public interface WriteDao {
	Integer getMaxId();
	void insertBBS(Bbs bbs);
}
