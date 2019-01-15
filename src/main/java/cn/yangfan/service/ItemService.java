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

}
