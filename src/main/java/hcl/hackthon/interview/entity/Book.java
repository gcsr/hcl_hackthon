package hcl.hackthon.interview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbnNo;;
	private int qty;
	public Long getIsbnNo() {
		return isbnNo;
	}
	public void setIsbnNo(Long isbnNo) {
		this.isbnNo = isbnNo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	

}
