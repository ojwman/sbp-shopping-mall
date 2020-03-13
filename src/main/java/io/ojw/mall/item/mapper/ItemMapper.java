package io.ojw.mall.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.ojw.mall.item.domain.Item;

public interface ItemMapper {
	public List<Item> getItemByCategory(@Param("category") String category, 
										@Param("limitFrom") int limitFrom,
										@Param("limitCount") int limitCount);

	public Integer getMaxId();

	public List<Item> getItemByKeyword(int from_id, String[] aKeyword);
}
