package net.cec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.cec.Secret;

public class GetNewPost {
	static List<String> listURL = new ArrayList<>();
	
	public static void main(String[] args) {
		
		while(true){
		
		
		String url = "http://facebook.com";
		String email = Secret.email;
		String password = Secret.password;

		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password + Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, 500);

		url = "https://www.facebook.com/groups/1784461175160264/?sorting_setting=CHRONOLOGICAL";

		driver.get(url);



		List<WebElement> elements = driver.findElements(By.className("_5pcq"));

		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();

			String link = webElement.getAttribute("href");
			if (link.startsWith("https://www.facebook.com/groups/cec.edu.vn/permalink/")) {
				
				if (!listURL.contains(link)) {
					listURL.add(link);
					try {
						Jsoup.connect("http://httpsns.appspot.com/queue?name=cecurl")
						.ignoreContentType(true)
						.timeout(60 * 1000)
						.method(Method.POST)
						.ignoreHttpErrors(true)
						.requestBody(link)
						.execute();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}else {
					System.out.println("Count: " +listURL.size());
				}
				
			}
		}

//		System.out.println("link: " + listPermalinks.size());
		
			try {
				Thread.sleep(1000*60*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
