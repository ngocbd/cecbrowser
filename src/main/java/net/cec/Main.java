package net.cec;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.googlecode.objectify.*;

public class Main {

	public static void main(String[] args) {
//		 System.setProperty("webdriver.chrome.driver",
//		            "E:\\Users\\Daika\\eclipse\\project\\cecbrowser\\src\\chromedriver.exe");
//	    String url = "http://facebook.com";
//	    String email = Secret.email;
//	    String password = Secret.password;
//	    
//	    WebDriver driver = new ChromeDriver(); 
//	    driver.get(url);
//	    driver.manage().window().maximize();
//	    driver.findElement(By.id("email")).sendKeys(email);
//	    driver.findElement(By.id("pass")).sendKeys(password + Keys.ENTER);
//	    WebDriverWait wait = new WebDriverWait(driver, 500);
//
//	    url = "https://www.facebook.com/groups/1784461175160264/?sorting_setting=CHRONOLOGICAL";
//	    
//	    driver.get(url);
//	    
//	    LinkedBlockingQueue<String> listPermalinks = new LinkedBlockingQueue<String>();
//	    
////	    try {
////			wait.wait();
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	    
//	    List<WebElement> elements =  driver.findElements(By.className("_5pcq"));
//	    
//	    for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
//			WebElement webElement = (WebElement) iterator.next();
//			
//			String link = webElement.getAttribute("href");
//			if(link.startsWith("https://www.facebook.com/groups/cec.edu.vn/permalink/"))
//			{
//				listPermalinks.add(link);
//			}
//			
//			
//			
//		}
//	    
//	    
//	    while(listPermalinks.size()>0)
//	    {
//	    	
//	    	String link = listPermalinks.poll();
//	    	
//	    	System.out.println(link);
//	    	driver.get(link);
//	    	try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }
//	   Member m = new Member();
//	   m.setUsername("aaaa");
//	   m.setPassword("bbbb");
//	   ObjectifyService.init();
//	   ObjectifyService.register(Member.class);
//	   
//	   Key<Member> key =  ObjectifyService.run(new Work<Key<Member>>() {
//
//			@Override
//			public Key<Member> run() {
//				
//				return  ObjectifyService.ofy().save().entity(m).now();
//			}
//            
//           
//        });
//	   System.out.println(key.getName());
	    //timestampContent
//		
//		String topicName = "projects/crazy-english-community/topics/message";
//		ProjectTopicName topic = ProjectTopicName.of("crazy-english-community", topicName);
//		
//		List<ApiFuture<String>> messageIdFutures = new ArrayList<>();
//		 Publisher publisher = null;
//	    try {
//	      // Create a publisher instance with default settings bound to the topic
//	      try {
//			publisher = Publisher.newBuilder(topicName).build();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	      List<String> messages = Arrays.asList("first message", "second message");
//
//	      // schedule publishing one message at a time : messages get automatically batched
//	      for (String message : messages) {
//	        ByteString data = ByteString.copyFromUtf8(message);
//	        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
//
//	        // Once published, returns a server-assigned message id (unique within the topic)
//	        ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
//	        messageIdFutures.add(messageIdFuture);
//	      }
//	    } finally {
//	      // wait on any pending publish requests.
//	      List<String> messageIds ;
//		try {
//			messageIds = ApiFutures.allAsList(messageIdFutures).get();
//			 for (String messageId : messageIds) {
//			        System.out.println("published with message ID: " + messageId);
//			      }
//
//			      if (publisher != null) {
//			        // When finished with the publisher, shutdown to free up resources.
//			        try {
//						publisher.shutdown();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			      }
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	     
//	    }
		
		
		
	    
	    
	}

}
