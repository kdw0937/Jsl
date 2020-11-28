package dao;

import java.util.List;

import model.Bbs;
import model.Condition;

public interface ReadDao {
	List<Bbs> readBbs(Condition c);//게시글 검색
	Integer getBbsCount();//게시글 수 검색
	Bbs getBbsDetail(Integer id);//특정 게시글 검색
}











