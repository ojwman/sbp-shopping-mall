package io.ojw.mall.item.domain;

import lombok.Data;

@Data
public class Item {
	private String id;
	private String name;
	private String category;
	private int price;
	private String thumbnail;
}
