package cn.yangfan.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yangfan.dao.ItemDao;
import pojo.Item;

@Service
public class ItemService {
	@Autowired
	private ItemDao itemDao;
	
	public void addItem(Item item){
		itemDao.addItem(item);
	}
	
	public void deleteItem(int id){
		itemDao.deleteItem(id);
	}
	
	public void updateItem(Item item){
		itemDao.updateItem(item);
	}
	
	public Item getItem(int id){
		return itemDao.getItem(id);
	}

	public ArrayList<Item> getItemList() {
		
		return itemDao.getItemList();
	}

	public ArrayList<Item> getItemListByUsername(String username, int length, int start) {
		return itemDao.getItemListByUsername(username,length,start);
	}

	public ArrayList<Item> getItemListByUsername2(String username, int length, int start) {
		// TODO Auto-generated method stub
		return itemDao.getItemList2(username,length,start);
	}

	public int getTotal() {
		// TODO Auto-generated method stub
		return itemDao.getTotal();
	}

	public int getTotalByUsername(String username) {
		// TODO Auto-generated method stub
		return itemDao.getTotalByUsername(username);
	}

}
