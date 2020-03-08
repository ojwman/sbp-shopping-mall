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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	Logger logger = LoggerFactory.getLogger("io.ojw.mall.item.contorller.ItemController");
	
	@Autowired
	private ItemService itemService;
	
	@ApiOperation(value = "카테고리를 통한 아이템 조회(페이징)")
	@ApiImplicitParams(
			{@ApiImplicitParam(name = "category", value = "카테고리")
			,@ApiImplicitParam(name = "limitFrom", value = "페이징 시작")
			,@ApiImplicitParam(name = "limitCount", value = "페이징 갯수")
			}
	)
	@RequestMapping(value = "/category/{category}/limitFrom/{limitFrom}/limitCount/{limitCount}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItemByCategory(@PathVariable String category,
														@PathVariable int limitFrom,
														@PathVariable int limitCount
			) {
		// get
		List<Item> list = itemService.getItemByCategory(category, limitFrom, limitCount);
		
		// return
		HttpStatus httpStatus = HttpStatus.OK;
		if (list == null || list.size() == 0) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(list, httpStatus);
	}
}
