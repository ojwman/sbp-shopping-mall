package io.ojw.mall;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class ApachCommonLangTest {
	@Test
	void testStringUtils() {
//		String s = "aaa,bbb,ccc";
		String s = null;
//		String[] ss = s.split(",");
//		System.out.println(Arrays.toString(ss));
		String[] ss2 = StringUtils.split(s);
		System.out.println(Arrays.toString(ss2));
		
		String sBlank = " ";
		System.out.println(StringUtils.isEmpty(sBlank));
		System.out.println(StringUtils.isBlank(sBlank));
	}
	
//	@Test
	void testRSUUtils() {
		System.out.println(RandomStringUtils.randomAlphabetic(5));	//only string
		System.out.println(RandomStringUtils.randomNumeric(5));	//only number
		System.out.println(RandomStringUtils.randomAlphanumeric(5));	//string and number
	}
	
//	@Test
	void testNumberUtils() {
		int[] nums = {1, 3, 2, 8, 6};
		int min = NumberUtils.min(nums);
		int max = NumberUtils.max(nums);
		System.out.println(min);
		System.out.println(max);
		
		String numstr = "1234";
		if (StringUtils.isNumeric(numstr)) {
			System.out.println(NumberUtils.createNumber(numstr));
		}
		String nullstr = null;
		System.out.println(NumberUtils.toInt(nullstr));		
		System.out.println(NumberUtils.toInt(nullstr, 1));		
	}
	
//	@Test
	void testDateUtils() {
		Date today = new Date();
		Date yesterday = DateUtils.addDays(today, -1);
		Date tomorrow = DateUtils.addDays(today, 1);
		Date nextYear = DateUtils.addYears(today, 1);
		
		System.out.println(yesterday);
		System.out.println(tomorrow);
		System.out.println(nextYear);
		
		System.out.println(DateUtils.isSameDay(today, new Date()));
	}
	
//	@Test
	@Ignore
	void testArrayUtils() {
		// toString
		String[] aStr = {"1", "2", "3"};
		String[][] aaStr = {
				{"1", "11"}, {"2", "22"}, {"3", "33"}
		};
		System.out.println(Arrays.toString(aStr));
		System.out.println(Arrays.toString(aaStr));
		System.out.println(ArrayUtils.toString(aStr));
		System.out.println(ArrayUtils.toString(aaStr));
		
		// toMap
		String[][] person = {
				{"ojw", "man"}, {"lsm", "girl"}
		};
		Map<Object, Object> map = ArrayUtils.toMap(person);
		System.out.println("ojw is " + map.get("ojw"));
		System.out.println("lsm is " + map.get("lsm"));
		
		// revers and contains
		int[] nums = {1, 3, 2, 8, 6};
		System.out.println(Arrays.toString(nums));
		Arrays.sort(nums);
		ArrayUtils.reverse(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(ArrayUtils.contains(nums, 1));
		System.out.println(ArrayUtils.contains(nums, 9));
	}

}
