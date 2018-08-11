package net.cec.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection.Method;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

import net.cec.Secret;
import net.cec.entity.Member;

import org.jsoup.Jsoup;

public class PostData {
	public static void main(String[] args) {
		while (true) {

			String url;
			try {
				url = Jsoup.connect("http://httpsns.appspot.com/queue?name=cecurl").ignoreContentType(true).method(Method.GET).execute().body();
						
				if (!url.equals("Queue cecurl is empty")) {
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ngoc\\Downloads\\chromedriver.exe");
					String urlFB = "http://facebook.com";
					String email = Secret.email;
					String password = Secret.password;

					WebDriver driver = new ChromeDriver();
					driver.get(urlFB);
					// driver.manage().window().maximize();
					driver.findElement(By.id("email")).sendKeys(email);
					driver.findElement(By.id("pass")).sendKeys(password + Keys.ENTER);
					driver.get(url);

					String[] hrefSplit;
					String[] idSplit;
					String facebookID;
					String name;

					WebElement webElement = driver.findElement(By.className("userContentWrapper"));
					WebElement webElement1 = webElement.findElement(By.className("fwb"));
					WebElement webElement2 = webElement1.findElement(By.tagName("a"));
					
					name = webElement2.getText();

					String FbHerf = webElement2.getAttribute("href");

					String[] strSplit = FbHerf.split("https://www.facebook.com/", 2);
					String[] checkSplit = strSplit[1].split("profile.php", 2);
					if (checkSplit.length == 1) {
						hrefSplit = strSplit[1].split("\\?fref", 2);
						facebookID = (hrefSplit[0]);
					} else {
						hrefSplit = strSplit[1].split("&", 2);
						idSplit = hrefSplit[0].split("id=");
						facebookID = (idSplit[1]);
					}
					System.out.println("Facebook ID: " + facebookID);
					
					
//					driver.get("wwww.facebook.");
					
//					ObjectifyService.init();
//					ObjectifyService.register(Member.class);
						
				
					
					
					Member member = new Member(facebookID);
					member.setName(name);
					member.setStatus(1);

					ObjectifyService.init();
					ObjectifyService.register(Member.class);
					Key<Member> key = ObjectifyService.run(new Work<Key<Member>>() {

						@Override
						public Key<Member> run() {
							return ObjectifyService.ofy().save().entity(member).now();
						}

					});
					System.out.println("Key: "+key.getName());
				}

			} catch (IOException e) {
				System.out.println("Connect http://httpsns.appspot.com/queue? error.");

			}
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
