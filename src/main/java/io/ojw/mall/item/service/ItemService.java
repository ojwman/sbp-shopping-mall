package io.ojw.mall.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ojw.mall.item.domain.Item;
import io.ojw.mall.item.mapper.ItemMapper;

@Service
public class ItemService {
	@Autowired
	private ItemMapper itemMapper;
	
	public List<Item> getItemByCategory(String category, int limitFrom, int limitCount) {
		List<Item> list = itemMapper.getItemByCategory(category, limitFrom, limitCount);
		
		return list;
	}

	public Integer getMaxId() {
		int maxId = itemMapper.getMaxId();
		
		return maxId;
	}

	public List<Item> getItemByKeyword(int from_id, String[] aKeyword) {
		List<Item> list = itemMapper.getItemByKeyword(from_id, aKeyword);
		
		return list;
	}
}
