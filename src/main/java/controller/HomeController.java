package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.WriteDao;
import model.Bbs;
import model.User;

@Controller
public class HomeController {
	@Autowired
	private WriteDao writeDao;
	@RequestMapping(value="/home/userentry.html",
			method=RequestMethod.GET)
	public ModelAndView entryForm() {
		ModelAndView mav = new ModelAndView(
				"home/template");
		mav.addObject(new User());//객체 주입
		mav.addObject("BODY","userentry.jsp");
		return mav;
	}
	
	@RequestMapping(value="/home/write.html",
			method=RequestMethod.POST)
	public ModelAndView put(@Valid Bbs bbs,
		BindingResult br, HttpSession session) {
		if(br.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					"home/template");
			mav.addObject("BODY","BBSWrite.jsp");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		String id=(String)session.getAttribute(
				"loginUser");
		ModelAndView mav = new ModelAndView(
				"home/template");
		if(id == null) {//로그인 안한 경우	
			mav.addObject("RESULT","nobody");
			mav.addObject(new User());//객체 주입
			mav.addObject("BODY","nologin.jsp");
			return mav;
		}else {//로그인 한 경우
			bbs.setId(id);
			//DB에 삽입
			writeDao.insertBBS(bbs);
			//페이지를 바꾼다.
			return new ModelAndView(
					"redirect:/read/read.html");
		}
	}
	
	@RequestMapping(value="/home/bbs.html",
			method=RequestMethod.GET)
	public ModelAndView bbs() {
		ModelAndView mav = new ModelAndView(
				"home/template");
		mav.addObject(new Bbs());//객체 주입
		mav.addObject("BODY","BBSWrite.jsp");
		return mav;
	}
	@RequestMapping(value="/home/intro.html",
			method=RequestMethod.GET)
	public ModelAndView intro(String BODY) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		mav.addObject("BODY",BODY);
		return mav;
	}
	
	@RequestMapping(value="/home/template.html",
			method=RequestMethod.GET)
	public ModelAndView template() {
		ModelAndView mav = new ModelAndView(
				"home/template");
		return mav;
	}
}










