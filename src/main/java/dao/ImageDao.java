package dao;

import java.util.List;

import model.Condition;
import model.Writing;

public interface ImageDao {
	void updateWriting(Writing writing);
	void deleteWriting(Writing writing);
	Writing getWritingById(Integer id);//글번호로 검색
	void insertWriting(Writing writing);//이미지 글 삽입
	Integer getMaxWritingId();//최대 글번호 검색
	List<Writing> getWriting(Condition c);//글 목록 검색
	Integer selectMaxGroupId();//답글을 위한 그룹번호검색
	void updateOrderNoReply(Writing writing);//답글을 위한 순서번호 변경
	
	Integer selectImageCount();//이미지 게시글 갯수
	Integer selectReplyPages(Integer seqno);
	//글번호로 일련번호 찾음 ->일련번호로 페이지 찾음
}








