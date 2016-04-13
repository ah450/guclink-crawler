package test.in.guclink.crawl.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import in.guclink.crawl.Credentials;
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

}
