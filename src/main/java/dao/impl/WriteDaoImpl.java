package dao.impl;

import java.util.Calendar;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.WriteDao;
import model.Bbs;
@Repository
public class WriteDaoImpl implements WriteDao {
	@Autowired
	private SqlSession session;
	public Integer getMaxId() {
		return session.selectOne("mapper.Mymapper.getMax");
	}
	public void insertBBS(Bbs bbs) {
		Integer seqno = this.getMaxId();
		if(seqno == null) seqno = 0;
		bbs.setSeqno(seqno + 1);
		Calendar today = Calendar.getInstance();
		int year=today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		String bbsDate=year+"/"+month+"/"+date;
		bbs.setBbs_date(bbsDate);
		session.insert("mapper.Mymapper.putBBS",bbs);
	}

}










