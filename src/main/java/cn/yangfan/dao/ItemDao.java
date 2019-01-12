package cn.yangfan.dao;

import java.util.ArrayList;

import pojo.Item;

public interface ItemDao {
	public void addItem(Item item);
	public void deleteItem(String id);
	public void updateItem(Item item);
	public Item getItem(String id);
	public ArrayList<Item> getItemList();
}
