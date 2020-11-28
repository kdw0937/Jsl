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

import dao.CartDao;
import dao.LoginDao;
import model.Cart;
import model.CartItem;
import model.User;

@Controller
public class CartController {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private Cart cart;
	@Autowired
	private LoginDao loginDao;
	
	@RequestMapping(value="/cart/modify.html",
			method=RequestMethod.POST)
	public ModelAndView modify(String CODE,
		Integer NUMBER, String BTN, HttpSession session) {
		System.out.println("버튼:"+BTN);
		String id = (String)session.getAttribute(
				"loginUser");
		if(BTN.equals("수정")) {
			cart.modifyItem(CODE, NUMBER, id);
		}else if(BTN.equals("삭제")) {
			cart.deleteItem(CODE, id);
		}
		return new ModelAndView(
				"redirect:/cart/show.html");
	}
	
	@RequestMapping(value="/cart/show.html",
			method=RequestMethod.GET)
	public ModelAndView showCart(HttpSession session) {
		ModelAndView mav = new ModelAndView(
				"home/template");
		String id=(String)session.getAttribute(
				"loginUser");
		if(id == null) {
			
			return mav;//로그인 안한 경우
		}
		List<CartItem> cartList = cart.getCart(id);
		int totalAmount = 0;//장바구니 총액
		if(cartList.size() > 0) {//장바구니가 존재하는경우
			Iterator it = cartList.iterator();
			while(it.hasNext()) {
				CartItem cartItem=(CartItem)it.next();
				int total=cartItem.getPrice() * 
							cartItem.getNum();
				totalAmount=totalAmount + total;
			}
			mav.addObject("SIZE","YES");
		}else {//장바구니가 존재하지 않는 경우
			mav.addObject("SIZE","NO");
		}
		mav.addObject("totalAmount",totalAmount);
		mav.addObject("BODY","cartListView.jsp");
		mav.addObject("CART_LIST",cartList);
		return mav;
	}
	
	@RequestMapping(value="/cart/login.html",
			method=RequestMethod.POST)
	public ModelAndView doLogin(@Valid User user,
			BindingResult br, HttpSession session) {
		if(br.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					"home/cartLogin");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		ModelAndView mav =new ModelAndView(
				"home/loginResult");
		String password = loginDao.getPassword(
				user.getId());
		if( !user.getPassword().equals(password)) {
			mav.addObject("cartLogin","YES");
			return mav;
		}else {
			mav.addObject("cartLogin","SUCCESS");
			session.setAttribute("loginUser", 
					user.getId());
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
		}
		System.out.println("로그인 codeList.size:"+cart.codeList.size());
		System.out.println("로그인 numList.size:"+cart.numList.size());
		return mav;
	}
	
	@RequestMapping(value="/cart/login.html",
			method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView(
				"home/cartLogin");
		mav.addObject("RESULT","nocart");
		mav.addObject(new User());
		return mav;
	}
	@RequestMapping(value="/cart/addCart.html")
	public ModelAndView addCart(String CODE,
			HttpSession session) {
		System.out.println("addCart mapping");
		String id=
			(String)session.getAttribute("loginUser");
		if(id == null) {//로그인을 안 한 경우
			ModelAndView mav = new ModelAndView(
				"redirect:/cart/login.html");
			mav.addObject("RESULT","nologin");
			return mav;
		}
		Cart cart=(Cart)session.getAttribute("CART");
		if(cart == null) cart = this.cart;
		cart.addCart(CODE, 1, id);//카트에 저장
		session.setAttribute("CART", cart);
		ModelAndView mav = new ModelAndView(
				"home/addCartResult");
		mav.addObject("ITEM_NUM",1);
		return mav;
	}

}















