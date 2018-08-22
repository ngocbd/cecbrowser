package net.cec.test;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.cec.Secret;

public class LoginTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String url = "http://facebook.com";
		String email = Secret.email;
		String password = Secret.password;

		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password + Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, 500);
		assertEquals("https://www.facebook.com/", driver.getCurrentUrl());
		
		
	}
	public static void main(String[] args) {
		String s= "https://www.facebook.com/media/set/?set\\u003dpcb.2129681200638258\\u0026type\\u003d1";
		System.out.println(StringEscapeUtils.unescapeEcmaScript(s));
		
	}

}
