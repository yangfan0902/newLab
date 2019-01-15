package cn.yangfan.dao;

import java.util.ArrayList;

import pojo.Item;

public interface ItemDao {
	public void addItem(Item item);
	public void deleteItem(int id);
	public void updateItem(Item item);
	public Item getItem(int id);
	public ArrayList<Item> getItemList();
}
