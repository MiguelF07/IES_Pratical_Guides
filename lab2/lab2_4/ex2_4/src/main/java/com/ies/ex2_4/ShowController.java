package com.ies.ex2_4;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	public static ShowList showList = new ShowList();

	// @GetMapping("/greeting")
	// public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	// 	return new Greeting(counter.incrementAndGet(), String.format(template, name));
	// }

	@GetMapping("/quote")
	public Map<String,String> quote(@RequestParam(value = "show",defaultValue = "")String showID) {
		if (showID.equals(""))
		{
			int id = showList.randomShow();
			String[] showQ = showList.randomQuote(id);
			Map<String,String> data = new LinkedHashMap<>();
			data.put("id",""+id);
			data.put("show",showQ[0]);
			data.put("quote",showQ[1]);
			return data;
		}
		String[] showQ = showList.randomQuote(Integer.parseInt(showID));
		Map<String,String> data = new LinkedHashMap<>();
		data.put("id",showID);
		data.put("show",showQ[0]);
		data.put("quote",showQ[1]);
		return data;
	}

	@GetMapping("/shows")
	public List<Map<String,String>> show(){
		List<Map<String,String>> out= new ArrayList<>();
		
		for(Show s : showList.shows)
		{
			Map<String,String> data = new LinkedHashMap<>();
			data.put("id",""+s.getId());
			data.put("show",s.getNome());
			out.add(data);
		}
		return out;
	}
}