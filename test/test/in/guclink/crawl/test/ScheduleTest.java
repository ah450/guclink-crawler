package test.in.guclink.crawl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import in.guclink.crawl.Credentials;
import in.guclink.crawl.Slot;
import in.guclink.crawl.student.Client;
import in.guclink.crawl.student.Schedule;

public class ScheduleTest {
	private static Client client;
	private Schedule schedule;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = System.getenv("GUC_USERNAME");
		String password = System.getenv("GUC_PASSWORD");
		Credentials creds = new Credentials(username, password);
		client = new Client(creds);
	}
	@Before
	public void setUp() throws Exception {
		schedule = new Schedule(client.getSchedule());
	}

	@Test
	public void testTitle() {
		assertEquals(schedule.getTitle(), "16-4477 - Ahmed Hisham Nasreldin Ismail");
	}
	
	@Test
	public void testGucID() {
		assertNotNull(schedule.getGucID());
		assertEquals(schedule.getGucID(), "16-4477");
	}
	
	@Test
	public void testStudentName() {
		assertNotNull(schedule.getStudentName());
		assertEquals(schedule.getStudentName(), "Ahmed Hisham Nasreldin Ismail");
	}
	
	@Test
	public void testSlots() {
//		TODO: Mock the schedule, it's literally a matter of time before this case is obsolete
		assertFalse(schedule.getSlots().isEmpty());
//		There are six days
		assertEquals(schedule.getSlots().size(), 6);
//		Saturday tests
		ArrayList<Slot> sat = schedule.getSlots().get(0);
		assertEquals(sat.size(), 5);
		assertTrue(sat.get(0).isFree);
		assertTrue(sat.get(2).isLecture);
		assertEquals(sat.get(2).name, "MATH 203");
		assertTrue(sat.get(3).isFree);
//		Monday Tests
		ArrayList<Slot> mon = schedule.getSlots().get(2);
		assertEquals(mon.size(), 5);
		assertTrue(mon.get(0).isTutorial);
		assertEquals(mon.get(0).name, "MATH 203");
//		Tuesday Tests
		ArrayList<Slot> tues = schedule.getSlots().get(3);
		assertEquals(tues.size(), 5);
		assertTrue(tues.get(4).isLab);
		
	}

}
