package in.guclink.crawl.student;

import java.util.ArrayList;
import java.util.Map;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import in.guclink.crawl.SlotDay;

public class Schedule {
	private String title;
	private Map<SlotDay, ArrayList<String>> slots;
	
	
	public Schedule(HtmlPage schedule) {
		title = schedule.getElementById("scdTpLbl").getTextContent().trim();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGucID() {
		return title.split(" ")[0].trim();
	}
	
	public String getStudentName() {
		String [] array = title.split("-", 3);
		if (array.length >= 3) {
			return array[2].trim();
		} else {
			return null;
		}
	}
	
	
	
}
