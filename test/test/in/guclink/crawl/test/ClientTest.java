package test.in.guclink.crawl.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import in.guclink.crawl.Credentials;
import in.guclink.crawl.student.Client;

public class ClientTest {
	private static String username, password;
	private static Credentials creds;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		username = System.getenv("GUC_USERNAME");
		password = System.getenv("GUC_PASSWORD");
		creds = new Credentials(username, password);
	}

	@Test
	public void testGetSchedule() {
		Client client = new Client(creds);
		HtmlPage schedule = client.getSchedule();
		assertNotNull(schedule);
	}

}
