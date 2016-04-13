package in.guclink.crawl.student;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import in.guclink.crawl.Credentials;

public class ClientTest {
	private static String username, password;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		username = System.getenv("GUC_USERNAME");
		password = System.getenv("GUC_PASSWORD");
	}

	@Test
	public void testGetSchedule() {
		Credentials creds = new Credentials(username, password);
		Client client = new Client(creds);
		
	}

}
