package net.cec.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fcs.Utils;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import net.cec.Secret;
import net.cec.entity.Member;

public class GroupWatcher  extends Thread{

	static Logger logger = Logger.getLogger(GroupWatcher.class.getName());
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
				url = Jsoup.connect("http://httpsns.appspot.com/queue?name=cecuser").ignoreContentType(true)
						.method(Method.GET).execute().body();
				logger.warning(url);
				if (!url.trim().equals("Queue cecuser is empty")) {
					logger.warning("get url :"+url);
					driver.get(url);
					
					
					
					

				}
				
			} catch (IOException e) {
				e.printStackTrace();

			} 
			try {
				Thread.sleep(10000);
				logger.warning("Sleep..");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
