package io.ojw.mall.item.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ojw.mall.item.domain.Item;
import io.ojw.mall.item.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	Logger logger = LoggerFactory.getLogger("io.ojw.mall.item.contorller.ItemController");
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/category/{category}/limitFrom/{limitFrom}/limitTo/{limitTo}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItemByCategory(@PathVariable String category,
														@PathVariable int limitFrom,
														@PathVariable int limitTo
			) {
		// get
		List<Item> list = itemService.getItemByCategory(category, limitFrom, limitTo);
		
		// return
		HttpStatus httpStatus = HttpStatus.OK;
		if (list == null || list.size() == 0) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(list, httpStatus);
	}
}