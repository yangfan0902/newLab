package cn.yangfan.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import pojo.Item;

public interface ItemDao {
	public void addItem(Item item);
	public void deleteItem(int id);
	public void updateItem(Item item);
	public Item getItem(int id);
	public ArrayList<Item> getItemList();
	public ArrayList<Item> getItemListByUsername(@Param("username") String username,@Param("length") int length,@Param("start") int start);
	public ArrayList<Item> getItemList2(@Param("username") String username,@Param("length") int length,@Param("start") int start);
	public int getTotal();
	public int getTotalByUsername(String username);
	public ArrayList<Item> getMyWeekItem(@Param("username") String username,@Param("length") int length,@Param("start") int start,@Param("startTime") String startTime,@Param("endTime") String endTime);
	public int getMyWeekItemCount(@Param("username") String username,@Param("startTime") String startTime,@Param("endTime") String endTime);
	public ArrayList<Item> getWeekLabItem(@Param("length") int length,@Param("start") int start,@Param("startTime") String startTime,@Param("endTime") String endTime);
	public int getWeekLabItemCount(@Param("startTime") String startTime,@Param("endTime") String endTime);
	public ArrayList<Item> getLabItemHistory(@Param("length") int length,@Param("start") int start);
	public int getLabItemHistoryCount();
}
