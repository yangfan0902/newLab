package cn.yangfan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yangfan.service.ItemService;
import pojo.Item;


@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/")
	public ModelAndView  index(){
		ModelAndView model=new ModelAndView("index");
		return model;
	}
	
	@RequestMapping("/item/itemList")
	@ResponseBody
	public List<Item> getItemList(HttpServletResponse response,String username){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ArrayList<Item> itemList=new ArrayList<Item>();
		itemList=itemService.getItemListByUsername(username);
		return itemList;
	}
	
	@RequestMapping("/item/add")
	@ResponseBody
	public Map addItem(Item item){
		Map result=new HashMap();
		try{
			if(item.getId()!=0){
				itemService.updateItem(item);
				result.put("msg", "修改成功");
				result.put("state", "200");
				return result;
			}else{
				itemService.addItem(item);
				result.put("msg", "添加成功");
				result.put("state", "200");
				return result;
				
			}
		}catch(Exception e){
			
			result.put("msg", "操作失败");
			result.put("state", "400");
			return result;
		}
		
		
	}
	
	@RequestMapping("/item/delete")
	@ResponseBody
	public Map deleteItem(int id){
		Map result=new HashMap();
		if(id!=-1){
			itemService.deleteItem(id);
		}
		result.put("state", "200");
		return result;
	}
}
