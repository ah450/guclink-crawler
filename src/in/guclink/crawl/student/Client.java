package in.guclink.crawl.student;


import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import in.guclink.crawl.Credentials;

public class Client {
	private WebClient webClient;
	public Client(Credentials creds) {
		webClient = new WebClient(BrowserVersion.CHROME);
		WebClientOptions opts = webClient.getOptions();
		opts.setUseInsecureSSL(true);
		opts.setRedirectEnabled(true);		
		DefaultCredentialsProvider credProv = (DefaultCredentialsProvider)  webClient.getCredentialsProvider();
		credProv.addCredentials(creds.getUsername(), creds.getPassword());
		credProv.addNTLMCredentials(creds.getUsername(), creds.getPassword(), null, -1, "guclink", null);
	}
	
	
	public HtmlPage getSchedule() {
		try {
			HtmlPage currentPage = webClient.getPage("http://student.guc.edu.eg/Web/Student/Schedule/GroupSchedule.aspx");
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
