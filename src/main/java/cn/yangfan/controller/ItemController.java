package cn.yangfan.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yangfan.service.ItemService;
import pojo.Item;
import pojo.Result;
import utils.TimeUtil;


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
	public Result getItemList(HttpServletResponse response,String username,String length,String start,String type){
		Result result=new Result();
		response.setHeader("Access-Control-Allow-Origin", "*");
		ArrayList<Item> itemList=new ArrayList<Item>();
		
		//我的本周订单
		if("thisWeek".equals(type)){
			String startTime=TimeUtil.getStartTime();
			String endTime=TimeUtil.getEndTime();
			itemList=itemService.getMyWeekItem(username,Integer.parseInt(length),Integer.parseInt(start),startTime,endTime);
			int total=itemService.getMyWeekItemCount(username,startTime,endTime);
			result.setList(itemList);
			result.setTotal(total);
			return result;
		}else if("allItem".equals(type)){
			itemList=itemService.getItemListByUsername(username,Integer.parseInt(length),Integer.parseInt(start));
			int total=itemService.getTotalByUsername(username);
			result.setList(itemList);
			result.setTotal(total);
			return result;
			
		}else if("labItem".equals(type)){
			String startTime=TimeUtil.getStartTime();
			String endTime=TimeUtil.getEndTime();
			itemList=itemService.getWeekLabItem(Integer.parseInt(length),Integer.parseInt(start),startTime,endTime);
			int total=itemService.getWeekLabItemCount(startTime,endTime);
			result.setList(itemList);
			result.setTotal(total);
			return result;
		}else if("labItemHistory".equals(type)){
			
			itemList=itemService.getLabItemHistory(Integer.parseInt(length),Integer.parseInt(start));
			int total=itemService.getLabItemHistoryCount();
			result.setList(itemList);
			result.setTotal(total);
			return result;
		}
		
		return null;
		
		
	}
	
	@RequestMapping("/item/itemList2")
	@ResponseBody
	public Result getItemList2(HttpServletResponse response,String username,String length,String start){
		Result result=new Result();
		response.setHeader("Access-Control-Allow-Origin", "*");
		ArrayList<Item> itemList=new ArrayList<Item>();
		itemList=itemService.getItemListByUsername2(username,Integer.parseInt(length),Integer.parseInt(start));
		int total=itemService.getTotal();
		result.setList(itemList);
		result.setTotal(total);
		return result;
	}
	
	@RequestMapping("/item/add")
	@ResponseBody
	public Map addItem(Item item){
		Map result=new HashMap();
		try{
			String username=item.getP_name();
			if(username.equals("null")){
				result.put("msg", "请重新登录");
				result.put("state", "500");
				return result;
			}
			if(item.getId()!=0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				item.setCreateTime(sdf.format(new Date()));
				itemService.updateItem(item);
				result.put("msg", "修改成功");
				result.put("state", "200");
				return result;
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				item.setCreateTime(sdf.format(new Date()));
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
	
	@RequestMapping("/item/index")
	@ResponseBody
	public Result getItems(){
		Result result=new Result();
		List list=itemService.getItemList();
		result.setList(list);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping("/item/id")
	@ResponseBody
	public Result checkItems(HttpServletRequest request){
		String type=request.getParameter("type");
		String[] ids=request.getParameterValues("id[]");
		
		if("agree".equals(type)){
			for(String id:ids){
				Item item=itemService.getItem(Integer.parseInt(id));
				item.setP_check(1);
				itemService.updateItem(item);
			}		
		}
		if("disagree".equals(type)){
			for(String id:ids){
				Item item=itemService.getItem(Integer.parseInt(id));
				item.setP_check(2);
				itemService.updateItem(item);
			}	
		}
		Result result=new Result();
		result.setStatus("0");
		return result;
	}
}
