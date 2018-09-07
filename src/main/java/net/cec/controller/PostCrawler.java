package net.cec.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fcs.Utils;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import net.cec.Secret;
import net.cec.entity.Member;

public class PostCrawler extends Thread {
	static Logger logger = Logger.getLogger(PostCrawler.class.getName());

	public static void main(String[] args) {
		String urlFB = "http://facebook.com";
		String email = Secret.email;
		String password = Secret.password;
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);

		driver.get(urlFB);
		// driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password + Keys.ENTER);
		ObjectifyService.init();
		ObjectifyService.register(Member.class);
		ObjectifyService.begin();
		while (true) {

			String url;
			try {
				url = Jsoup.connect("http://httpsns.appspot.com/queue?name=cecurl").ignoreContentType(true)
						.method(Method.GET).execute().body();
				logger.warning(url);
				if (!url.trim().equals("Queue cecurl is empty")) {
					logger.warning("get url :" + url);
					driver.get(url);

					try {
						WebElement userContentWrapper = driver.findElement(By.className("userContentWrapper"));

						WebElement userProfileElement = userContentWrapper.findElement(By.cssSelector(".fwb a"));

						String name = userProfileElement.getText();

						String facebookID = Utils.splitQuery(userProfileElement.getAttribute("ajaxify"))
								.get("member_id");

						System.out.println(facebookID);

						Key<Member> key = Key.create(Member.class, facebookID);

						Member member = ofy().load().key(key).now();
						if (member == null) {
							member = new Member(facebookID);
							member.setName(name);

							ofy().save().entities(member);

						}

						String postID = url.replaceAll("https://www.facebook.com/groups/cec.edu.vn/permalink/", "")
								.replaceAll("/", "");

						Jsoup.connect("https://crazy-english-community.appspot.com/api/submit/post?id=" + postID
								+ "&posterid=" + facebookID).ignoreContentType(true).method(Method.GET).execute()
								.body();

					} catch (Exception e) {
						e.printStackTrace();
						logger.warning(e.getMessage());
						continue;

					}
					finally
					{
						continue;
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
				continue;

			}
			try {
				Thread.sleep(10000);
				Jsoup.connect("https://cec.net.vn/cron/crawl/links").get();
				logger.warning("Sleep..");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			finally
			{
				continue;
			}

		}

	}

}
