package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.ReadDao;
import model.Bbs;
import model.Condition;
@Repository
public class ReadDaoImpl implements ReadDao {
	@Autowired
	private SqlSession session;
	public List<Bbs> readBbs(Condition c) {
		return session.selectList(
				"mapper.Mymapper.getBBS",c);
	}
	public Integer getBbsCount() {
		return session.selectOne(
				"mapper.Mymapper.getBBSCount");
	}
	public Bbs getBbsDetail(Integer id) {
		return session.selectOne(
				"mapper.Mymapper.readBBS",id);
	}
}






