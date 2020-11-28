package controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.LoginDao;
import model.Cart;
import model.CartItem;
import model.User;

@Controller
public class LoginController {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private Cart cart;
	
	@RequestMapping(value="/login/login.html")
	public ModelAndView tempLogin(String writeForm) {
		ModelAndView mav = null;
		if(writeForm != null) {//이미지 게시글 로그인
			mav = new ModelAndView("home/writeLogin");
		}else {//일반 로그인
			mav = new ModelAndView("home/login");
		}
		mav.addObject(new User());//객체 주입
		return mav;
	}
	@RequestMapping(value="/login/writeTemplate.html",
			method=RequestMethod.POST)
	public ModelAndView writeLogin(@Valid User user,
			BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		if(br.hasErrors()) {
			mav.addObject("HEADER","login.jsp");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		String password = loginDao.getPassword(
				user.getId());
		if(password == null || 
				!user.getPassword().equals(password)) {
			mav.addObject("BODY","loginResult.jsp");
		}else {//로그인 성공
			session.setAttribute("loginUser", 
					user.getId());
			///////cart테이블에서 계정으로 검색////
			List<CartItem> cartList = cart.getCart(
					user.getId());//계정으로 장바구니검색
				if(cartList != null) {//장바구니가 있는경우
					Iterator it=cartList.iterator();
					int i = 0;
					while(it.hasNext()) {
						CartItem ci=(CartItem)it.next();
						this.cart.setCodeList(i,ci.getCode());
						this.cart.setNumList(i,ci.getNum());
						i++;
					}
					session.setAttribute("CART", this.cart);
				}
			//////////////////////////////////////
			mav.addObject("imageLogin","SUCCESS");
			mav.addObject("BODY","loginResult.jsp");
		}
		return mav;
	}
	
	@RequestMapping(value="/login/template.html",
			method=RequestMethod.POST)
	public ModelAndView login(@Valid User user,
		BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		if(br.hasErrors()) {
			mav.addObject("HEADER","login.jsp");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		String password = loginDao.getPassword(
				user.getId());
		if(password == null || 
				!user.getPassword().equals(password)) {
			mav.addObject("BODY","loginResult.jsp");
		}else {//로그인 성공
			session.setAttribute("loginUser", 
					user.getId());
			///////cart테이블에서 계정으로 검색////
			List<CartItem> cartList = cart.getCart(
					user.getId());//계정으로 장바구니검색
				if(cartList != null) {//장바구니가 있는경우
					Iterator it=cartList.iterator();
					int i = 0;
					while(it.hasNext()) {
						CartItem ci=(CartItem)it.next();
						this.cart.setCodeList(i,ci.getCode());
						this.cart.setNumList(i,ci.getNum());
						i++;
					}
					session.setAttribute("CART", this.cart);
				}
			//////////////////////////////////////
			mav.addObject("BODY","loginResult.jsp");
		}
		return mav;
	}
}








