package in.guclink.crawl.student;


import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import in.guclink.crawl.Credentials;

public class Client {
	private WebClient webClient;
	public Client(Credentials creds) {
		webClient = new WebClient(BrowserVersion.CHROME);
		DefaultCredentialsProvider credProv = (DefaultCredentialsProvider)  webClient.getCredentialsProvider();
		credProv.addCredentials(creds.getUsername(), creds.getPassword(), "guc.edu.eg", -1, null);
	}
	
	
	public HtmlPage getSchedule() {
		try {
			HtmlPage currentPage = webClient.getPage("http://student.guc.edu.eg");
			return currentPage;
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
