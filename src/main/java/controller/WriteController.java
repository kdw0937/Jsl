package controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.ImageDao;
import model.Condition;
import model.Writing;

@Controller
public class WriteController {
	@Autowired
	private ImageDao imageDao;
	@RequestMapping(value="/write/writeReplyForm.html",
			method=RequestMethod.GET)
	public ModelAndView replyForm(Integer id,
			Integer parent_id, Integer group_id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = imageDao.getWritingById(id);//원글검색
		writing.setContent(null);//답글 작성을 위해서 비운다.
		writing.setWriter_name(null);//답글 작성을 위해서 비운다.
		writing.setEmail(null);//답글 작성을 위해서 비운다.
		mav.addObject("title","RE]"+writing.getTitle());
		mav.addObject("writing",writing);
		mav.addObject("BODY","writeForm.jsp?group_id="+
				group_id+"&parent_id="+parent_id);
		return mav;
	}
	
	@RequestMapping(value="/write/updateDo.html",
			method=RequestMethod.POST)
	public ModelAndView modifyDo(Writing writing, 
			HttpSession session) {
		Writing old = imageDao.getWritingById(
				writing.getWriting_id());//글번호로 검색
		ModelAndView mav = new ModelAndView("home/template");
		if(writing.getPassword().equals(old.getPassword())) {
			MultipartFile multiFile = writing.getImage();
			String fileName = multiFile.getOriginalFilename();
			if(fileName.equals("")) {//이미지가 선택 안된경우
				writing.setImage_name(old.getImage_name());
			}else {//이미지가 선택된 경우, 즉, 이미지 변경
				String path=null; OutputStream os = null;
				fileName = multiFile.getOriginalFilename();
				ServletContext ctx = session.getServletContext();
				path=ctx.getRealPath("/upload/"+fileName);
				try {
					os=new FileOutputStream(path);
					BufferedInputStream bis = 
						new BufferedInputStream(
							multiFile.getInputStream());
					byte[] buffer = new byte[8160];
					int read = 0;
					while((read=bis.read(buffer))>0) {
						os.write(buffer,0,read);
					}
					if(os != null) os.close();
				}catch(Exception e) {e.printStackTrace();}
				writing.setImage_name(fileName);//새이미지 이름설정
			}
			imageDao.updateWriting(writing);//DB에서 update
			mav.addObject("BODY","update_result_ok.jsp?id="+
					writing.getWriting_id());
		}//암호가 일치하는 경우
		else {//암호가 일치하지 않는 경우
			mav.addObject("BODY","update_result.jsp?id="+
					writing.getWriting_id());
		}
		return mav;
	}
	
	@RequestMapping(value="/write/writeModify.html",
			method=RequestMethod.GET)
	public ModelAndView modify(Integer id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = imageDao.getWritingById(id);
		mav.addObject(writing);//객체 주입
		mav.addObject("BODY","update_form.jsp");
		return mav;
	}
	
	@RequestMapping(value="/write/deleteDo.html",
			method=RequestMethod.POST)
	public ModelAndView deleteDo(Writing writing) {
		Writing old=imageDao.getWritingById(
				writing.getWriting_id());//글번호로 검색
		ModelAndView mav = new ModelAndView("home/template");
		if(writing.getPassword().equals(old.getPassword())) {
			imageDao.deleteWriting(writing);//DB에서 삭제
			mav.addObject("BODY","delete_result.jsp");
		}//저장된 암호와 입력된 암호를 비교, 같은 경우
		else {//암호가 다른 경우
			mav.addObject("BODY","delete_result.jsp?id="+
					writing.getWriting_id());
		}
		return mav;
	}
	
	@RequestMapping(value="/write/writeDelete.html",
			method=RequestMethod.GET)
	public ModelAndView deleteForm(Integer id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = imageDao.getWritingById(id);
		mav.addObject(writing);
		mav.addObject("BODY","delete_form.jsp");
		return mav;
	}
	
	@RequestMapping(value="/write/writeList.html")
	public ModelAndView writeList(Integer SEQNO,
			Integer PAGE_NUM) {
		if(PAGE_NUM == null) PAGE_NUM = 1;
		ModelAndView mav = new ModelAndView(
				"home/template");
		if(SEQNO != null) {
			int rownum=imageDao.selectReplyPages(SEQNO);
			int page = rownum / 5;
			if(rownum % 5 !=0) page++;//rownum:일련번호
			PAGE_NUM = page;
		}//글 번호로 일련번호를 찾고,일련번호로 페이지찾음 
		int currentPage = PAGE_NUM;
		int totalPageCount = 0;
		int startRow = 0; int endRow = 0;
		int count=imageDao.selectImageCount();//이미지게시글의 갯수를 검색
		if(count > 0) {
			totalPageCount = count / 5;
			if(count % 5 > 0) totalPageCount++;
			startRow = (currentPage - 1)*5 +1;
			endRow = currentPage * 5;
			if(endRow > count) endRow = count;
		}
		Condition c = new Condition();
		c.setStartRow(startRow);c.setEndRow(endRow);
		List<Writing> writeList = imageDao.getWriting(c);
		mav.addObject("LIST",writeList);
		mav.addObject("count",count);
		mav.addObject("startRow",startRow);
		mav.addObject("endRow",endRow);
		mav.addObject("pageCount",totalPageCount);
		mav.addObject("currentPage",currentPage);
		mav.addObject("BODY","list_view.jsp");
		return mav;
	}
	
	@RequestMapping(value="/write/write.html",
			method=RequestMethod.POST)
	public ModelAndView write(@Valid Writing writing,
			BindingResult br, HttpSession session,
			Integer parent_id, Integer order_no,
			Integer group_id) {
		if(br.hasErrors()) {//폼에 오류가 있는 경우
			ModelAndView mav = new ModelAndView(
					"home/template");
			mav.addObject("BODY","writeForm.jsp");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		MultipartFile multiFile = writing.getImage();
		String fileName = null; String path = null;
		OutputStream os = null;
		if(multiFile != null) {//업로드 파일이 있는 경우
			fileName=multiFile.getOriginalFilename();
			ServletContext ctx = 
					session.getServletContext();
			path = ctx.getRealPath("/upload/"+fileName);
			System.out.println("업로드 위치:"+path);
			try {
				os=new FileOutputStream(path);
				BufferedInputStream bis = 
					new BufferedInputStream(
						multiFile.getInputStream());
				byte[] buffer = new byte[8196];//6K
				int read = 0;
				while((read = bis.read(buffer))>0) {
					os.write(buffer,0,read);
				}
				if(os != null) os.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			writing.setImage_name(fileName);//파일이름설정
		}//end of if
		//DB에 삽입
		Integer maxId=imageDao.getMaxWritingId();
		if(maxId == null) maxId = 0;
		if(writing.getParent_id()== null) {//원글인경우
			writing.setParent_id(0);
			writing.setOrder_no(0);
			Integer gId=imageDao.selectMaxGroupId();
			if(gId == null) gId = 0;
			writing.setGroup_id(gId + 1);
		}else {//답글인 경우
			writing.setParent_id(parent_id);
			writing.setGroup_id(group_id);
			writing.setOrder_no(order_no);
			imageDao.updateOrderNoReply(writing);
		}
		writing.setWriting_id(maxId + 1);//글번호
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		String registerDate=year+"/"+month+"/"+date;
		writing.setRegister_date(registerDate);//작성일
		imageDao.insertWriting(writing);//DB에 삽입
		ModelAndView mav = new ModelAndView(
				"home/template");
		mav.addObject("BODY",
			"writeResult.jsp?SEQNO="+(maxId + 1));
		return mav;
	}
	
	@RequestMapping(value="/write/writeForm.html",
			method=RequestMethod.GET)
	public ModelAndView writeForm(HttpSession session) {
		String id = (String)session.getAttribute(
				"loginUser");
		ModelAndView mav = new ModelAndView(
				"home/template");
		if(id == null) {//로그인 안한경우
			mav.addObject("BODY","writeFormLogin.jsp");
		}else {//로그인 한 경우
			mav.addObject("BODY","writeForm.jsp");
			mav.addObject(new Writing());
		}
		return mav;
	}
}









