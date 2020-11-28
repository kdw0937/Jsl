package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.ItemDao;
import dao.LoginDao;
import model.Item;

@Controller
public class IdcheckController {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private ItemDao itemDao;
	@RequestMapping(value="/idcheck/codecheck.html")
	public ModelAndView codecheck(String CODE) {
		ModelAndView mav = new ModelAndView(
				"home/codeCheck");
		Item item = itemDao.getItem(CODE);
		if(item == null) {//중복된 코드가 없는 경우
			mav.addObject("DUP","NO");
		}else {//중복된 코드가 있는 경우
			mav.addObject("DUP","YES");
		}
		mav.addObject("CODE",CODE);
		return mav;
	}
	@RequestMapping(value="/idcheck/idcheck.html",
			method=RequestMethod.GET)
	public ModelAndView idcheck(String ID) {
		ModelAndView mav = new ModelAndView(
				"home/idCheck");
		Integer cnt = loginDao.getIdCount(ID);
		if(cnt > 0) {//중복된 ID가 있는 경우
			mav.addObject("DUP","YES");
		}else {//중복된 ID가 없는 경우
			mav.addObject("DUP","NO");
		}
		mav.addObject("ID",ID);
		return mav;
	}
}












