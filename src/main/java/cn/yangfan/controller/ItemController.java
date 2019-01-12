package cn.yangfan.controller;

import java.util.ArrayList;
import java.util.List;

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
	public List<Item> getItemList(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ArrayList<Item> itemList=new ArrayList<Item>();
		itemList=itemService.getItemList();
		return itemList;
	}
}
