package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.LoginDao;
import model.User;

@Controller
public class EntryController {
	@Autowired
	private LoginDao loginDao;
	@RequestMapping(value="/entry/entry.html",
			method=RequestMethod.POST)
	public ModelAndView entry(User user,
			HttpSession session) {
		loginDao.entryUser(user);//DB에 삽입
		ModelAndView mav = new ModelAndView(
				"home/template");
		session.setAttribute("loginUser", user.getId());
		mav.addObject("BODY","loginResult.jsp");
		return mav;
	}
}








