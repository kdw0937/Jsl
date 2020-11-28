package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.ItemDao;
import model.Item;
import model.User;

@Controller
public class ProductController {
	@Autowired
	private ItemDao itemDao;
	@RequestMapping(value="/product/entry.html",
			method=RequestMethod.POST)
	public ModelAndView entry(@Valid Item item,
			BindingResult br, HttpSession session) {
		ModelAndView mav=new ModelAndView(
					"home/template");
		if(br.hasErrors()) {
			mav.getModel().putAll(br.getModel());
			mav.addObject("BODY","itemInput.jsp");
			return mav;
		}
		String id=(String)session.getAttribute(
				"loginUser");
		if(id == null) {
			mav.addObject("BODY","nologin.jsp");
			mav.addObject("ITEM","nobody");
			mav.addObject(new User());
			return mav;
		}else {
			itemDao.putItem(item);//DB에 삽입
			return new ModelAndView(
				"redirect:/read/product.html");
		}
	}
	
	@RequestMapping(value="/product/open.html",
			method=RequestMethod.GET)
	public ModelAndView open() {
		ModelAndView mav = new ModelAndView(
				"home/template");
		mav.addObject(new Item());
		mav.addObject("BODY","itemInput.jsp");
		return mav;
	}
}











