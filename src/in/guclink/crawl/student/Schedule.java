package in.guclink.crawl.student;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlFont;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import in.guclink.crawl.Slot;

public class Schedule {
	private String title;
	private ArrayList<ArrayList<Slot>> slots;
	private static final Pattern freePattern = Pattern.compile("\\Afree\\z", Pattern.CASE_INSENSITIVE);
	private static final Pattern lecturePattern = Pattern.compile("lecture", Pattern.CASE_INSENSITIVE);
	
	
	public Schedule(HtmlPage schedule) {
		title = schedule.getElementById("scdTpLbl").getTextContent().trim();
		slots = new ArrayList<ArrayList<Slot>>();
		parseSlots(schedule);
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
	
	
	private void parseSlots(HtmlPage schedule) {
		DomElement table = schedule.getElementById("scdTbl");
		List<?> rows = table.getByXPath("tbody/tr");
//		Skip row zero as it's for headers
//		Skip last row as it's for display purposes only
		for(int i = 1; i < rows.size() - 1; i++ ) {
			HtmlTableRow row = (HtmlTableRow) rows.get(i);
			ArrayList<Slot> day = new ArrayList<Slot>();
			@SuppressWarnings("unchecked")
			List<HtmlTableDataCell> dataCells = (List<HtmlTableDataCell>) row.getByXPath("td");
//			First cell is day name i.e Saturday
			for(int j = 1; j < dataCells.size(); j++) {
				HtmlTableDataCell cell = dataCells.get(j);
				@SuppressWarnings("unchecked")
				List<HtmlSpan> spans = (List<HtmlSpan>) cell.getByXPath(".//span");
				if(spans.isEmpty()) {
					day.add(makeTutorialSlot(cell));
				} else {
					day.add(makeLectureSlot(cell));
				}
			}
			this.slots.add(day);
		}
	}

	private Slot makeLectureSlot(HtmlTableDataCell cell) {
		Slot slot = new Slot();
		slot.isTutorial = false;
		slot.isLab = false;
		@SuppressWarnings("unchecked")
		List<HtmlSpan> spans = (List<HtmlSpan>) cell.getByXPath(".//span");
		if(spans.isEmpty()) {
			return null;
		}
		HtmlSpan slotSpan = spans.get(0);
		// Determine if Free or Lecture
		
		if(freePattern.matcher(slotSpan.getTextContent().trim()).matches()) {
			slot.isFree = true;
			slot.name = "Free";
			slot.isLecture = false;
		} else {
			slot.isFree = false;
			slot.isLecture = true;
			String [] aroundLecture = lecturePattern.split(slotSpan.getTextContent().trim());
			slot.name = aroundLecture[0].trim();
			if (aroundLecture.length > 1) {
				String groupLocation = aroundLecture[1].trim();
				if (groupLocation.contains(")")) {
					String [] aroundBracket = groupLocation.split("\\)");
					slot.group = aroundBracket[0].trim().substring(1).trim();
					if (aroundBracket.length > 1) {
						slot.location = aroundBracket[1].trim();
					}
				} else {
					slot.location = groupLocation;
				}
			}			
		}
		return slot;
	}

	private Slot makeTutorialSlot(HtmlTableDataCell cell) {
		Slot slot = new Slot();
		slot.isFree = false;
		slot.isLecture = false;
		slot.isTutorial = true;
		slot.isLab = false;
		@SuppressWarnings("unchecked")
		List<HtmlFont> fonts = (List<HtmlFont>) cell.getByXPath(".//font");
		if (fonts.size() >= 1) {
			slot.group = fonts.get(0).getTextContent().trim();
		}
		if (fonts.size() >= 2) {
			slot.location = fonts.get(1).getTextContent().trim();
		}
		if (fonts.size() >= 3) {
			slot.name = fonts.get(2).getTextContent().trim();
			if(slot.name.contains("Lab")) {
				slot.isLab = true;
			}
			slot.name = slot.name.replace("Lab","");
			slot.name = slot.name.replace("Tut","");
			slot.name = slot.name.trim();
		}
		return slot;
	}
	
}
