package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.ImageDao;
import model.Condition;
import model.Writing;
@Repository
public class ImageDaoImpl implements ImageDao {
	@Autowired
	private SqlSession session;
	public void updateWriting(Writing writing) {
		session.update(
			"mapper.Imagemapper.updateWritingInfo",writing);
	}
	public void deleteWriting(Writing writing) {
		session.delete(
			"mapper.Imagemapper.deleteWritingInfo",writing);
	}
	public Writing getWritingById(Integer id) {
		return session.selectOne(
			"mapper.Imagemapper.getWritingInfoById",id);
	}
	public void insertWriting(Writing writing) {
		session.insert(
			"mapper.Imagemapper.insertWriting",writing);
	}
	public Integer getMaxWritingId() {
		return session.selectOne(
			"mapper.Imagemapper.getMaxWritingId");
	}
	public List<Writing> getWriting(Condition c) {
		return session.selectList(
			"mapper.Imagemapper.getWritingInfo",c);
	}
	public Integer selectMaxGroupId() {
		return session.selectOne(
			"mapper.Imagemapper.selectMaxGroupId");
	}
	public void updateOrderNoReply(Writing writing) {
		session.update(
			"mapper.Imagemapper.updateOrderNoReply",writing);
	}
	public Integer selectImageCount() {
		return session.selectOne(
				"mapper.Imagemapper.selectImageCount");
	}
	public Integer selectReplyPages(Integer seqno) {
		return session.selectOne(
			"mapper.Imagemapper.selectReplyPages",seqno);
	}
}











