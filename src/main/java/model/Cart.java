package model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;

@Service
public class Cart {
	@Autowired
	private CartDao cartDao;
	public List<CartItem> getCart(String id){
		return cartDao.selectCart(id);
	}//계정으로 장바구니를 검색
	public LinkedList<String> codeList =
			new LinkedList<String>();//상품번호 목록
	public LinkedList<Integer> numList =
			new LinkedList<Integer>();//상품갯수 목록
	public void setCodeList(int index, String code) {
		this.codeList.add(index, code);
	}
	public void setNumList(int index, Integer num) {
		this.numList.add(index, num);
	}
	public void modifyItem(String code,Integer num,
			String id) {
		for(int cnt=0; cnt<codeList.size(); cnt++) {
			if(codeList.get(cnt).equals(code)) {
				numList.set(cnt, num);//갯수 변경
				updateCart(code,num,id);//DB에서 변경
				return;//메서드 종료
			}//이미 장바구니에 있는 상품인지 검사
		}
	}//장바구니 변경
	public void addCart(String code, int num,String id) {
		for(int i=0; i<codeList.size(); i++) {
			if(codeList.get(i).equals(code)) {
				System.out.println("numList.get("+i+"):"+numList.get(i));
				System.out.println("파라미터 num:"+num);
				System.out.println("기존+num:"+numList.get(i)+num);
				numList.set(i, numList.get(i)+num);//갯수변경
				updateCart(code,numList.get(i),id);
				return;//메서드 종료
			}//이미 장바구니에 있는 상품인지 검사
		}
		codeList.add(code); numList.add(num);//장바구니에추가
		insertCart(code,num,id);//DB에 삽입
	}//장바구니에 담는 메서드
	public void deleteItem(String code, String id) {
		for(int cnt=0; cnt<codeList.size(); cnt++) {
			if(codeList.get(cnt).equals(code)) {
				codeList.remove(cnt);//장바구니에서 해당 상품코드 삭제
				numList.remove(cnt);//장바구니에서 해당 상품갯수 삭제
				CartItem item=new CartItem();
				item.setId(id); item.setCode(code);
				cartDao.deleteCart(item);//DB에서 삭제
				System.out.println("삭제됨:"+code+","+id);
				return;//메서드 종료
			}//이미 장바구니에 있는 상품인지 검사
		}
	}//장바구니에서 삭제
	
	private void insertCart(String code,int num,
			String id) {
		Integer seqno=cartDao.selectMaxCart();//일련번호
		if(seqno == null) seqno = 1;
		else seqno= seqno + 1;
		CartItem item = new CartItem();
		item.setSeqno(seqno); item.setId(id);
		item.setCode(code); item.setNum(num);
		System.out.println("Cart 클래스:insertCart()");
		cartDao.insertCart(item);
	}//장바구니에 상품 삽입
	private void updateCart(String code,int num,
			String id) {
		System.out.println(
			"Cart클래스,updateCart():code:"+code+",num:"+num);
		CartItem item = new CartItem();
		item.setCode(code); item.setNum(num);
		item.setId(id);
		cartDao.updateCart(item);
	}//장바구니에 상품정보 변경
}












