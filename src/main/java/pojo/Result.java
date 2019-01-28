package pojo;

import java.util.List;

public class Result {
	private String status;
	private List list;
	private int total;
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
}
