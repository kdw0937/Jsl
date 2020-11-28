package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.ImageDao;
import dao.ItemDao;
import dao.ReadDao;
import model.Bbs;
import model.Condition;
import model.Item;
import model.Writing;

@Controller
public class ReadController {
	@Autowired
	private ReadDao readDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ImageDao imageDao;
	@RequestMapping(value="/read/readImage.html")
	public ModelAndView readImage(Integer id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = imageDao.getWritingById(id);
		mav.addObject("writing",writing);
		mav.addObject("BODY","read_image.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/readItem.html",
			method=RequestMethod.GET)
	public ModelAndView detail(String CODE) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		Item item = itemDao.getItem(CODE);
		mav.addObject("ITEM",item);
		mav.addObject("BODY","itemDetail.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/product.html",
			method=RequestMethod.GET)
	public ModelAndView itemList(Integer pageNo) {
		ModelAndView mav=new ModelAndView(
				"home/template");
		Integer cnt = itemDao.getItemCount();
		if(cnt == null) cnt = 0;
		int startRow=0; int endRow=0; int pageCnt=0;
		int currentPage = 0;
		if(pageNo == null) currentPage = 1;
		else currentPage = pageNo;
		if(cnt > 0) {
			pageCnt = cnt / 5;
			if(cnt % 5 > 0) pageCnt++;
			startRow = (currentPage -1) * 5 + 1;
			endRow = currentPage * 5;
			if(endRow > cnt) endRow = cnt;
		}
		Condition c = new Condition();
		c.setStartRow(startRow); c.setEndRow(endRow);
		List<Item> itemList = itemDao.getItems(c);
		mav.addObject("ITEM_LIST",itemList);
		mav.addObject("COUNT",pageCnt);
		mav.addObject("BODY","itemList.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/readDetail.html",
			method=RequestMethod.GET)
	public ModelAndView detail(Integer SEQNO) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		Bbs bbs = readDao.getBbsDetail(SEQNO);
		mav.addObject("BBS_ITEM",bbs);//검색결과 저장
		mav.addObject("BODY","bbsItemView.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/read.html",
			method=RequestMethod.GET)
	public ModelAndView readBbs(Integer pageNo) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		Integer cnt = readDao.getBbsCount();//글갯수검색
		if(cnt == null) cnt = 0;
		int startRow=0; int endRow=0; int pageCnt=0;
		int currentPage =0;
		if(pageNo == null) currentPage = 1;
		else currentPage = pageNo;
		if(cnt > 0) {//글갯수가 0보다 큰경우,페이지수계산
			pageCnt = cnt / 5;
			if(cnt % 5 > 0) pageCnt++;
			startRow=(currentPage-1)*5 + 1;
			endRow=currentPage * 5;
			if(endRow > cnt) endRow = cnt;
		}
		Condition c = new Condition();//조건검색 객체생성
		c.setStartRow(startRow);c.setEndRow(endRow);
		List<Bbs> bbsList = readDao.readBbs(c);
		//DB에서 게시글 검색
		mav.addObject("BBS_LIST",bbsList);//검색결과저장
		mav.addObject("COUNT",pageCnt);//페이지 수 저장
		mav.addObject("BODY","bbsListView.jsp");
		//검색 결과를 뷰로 전달
		return mav;
	}
}












