package net.cec.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.fcs.Utils;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import net.cec.Secret;
import net.cec.entities.MemberPost;
import net.cec.entities.MemberVideo;
import net.cec.entity.Member;

public class GroupVideoWatcher extends Thread {

	static Logger logger = Logger.getLogger(GroupVideoWatcher.class.getName());

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

		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password + Keys.ENTER);
		ObjectifyService.init();
		ObjectifyService.register(Member.class);
		ObjectifyService.register(MemberVideo.class);
		ObjectifyService.register(MemberPost.class);
		ObjectifyService.begin();

		while (1 == 1) {
			try {
				crawlVideo(driver);
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch(UnhandledAlertException uae)
				{
					driver.switchTo().alert().accept();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				

			}
		}
	}

	public static void messengerNextLesson(WebDriver driver, String posterId, String content) {
		driver.get("https://www.messenger.com/t/" + posterId);
		try {
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#u_0_1 button")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.switchTo().activeElement().sendKeys(content + "\n");

	}

	public static void commentVideo(WebDriver driver, MemberVideo mv, MemberPost mp) {

		String[] commentRepo = {
				"Bài nói của bạn rất hay %s. Mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s bài nói của bạn rất hay. Mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s bài nói của bạn rất tuyệt vời. Mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s mình tự hào về bạn. Mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s ước gì mình nói được như bạn bạn. Mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"Mình tự hào về bạn. %s mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s thần tượng của mình đây rồi, mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s quá hay mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s Quá tuyệt mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"Quá tuyệt %s mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s cố gắng lên, mình tự hào về bạn!!!  mình đã đăng bài viết của bạn tại https://cec.net.vn/p/%s/",
				"%s phát âm của bạn thật tuyệt vời . Mình đã đăng bài viết của bạn tại: https://cec.net.vn/p/%s/",
				"%s Giọng nói của bạn thật quyến rũ . Mình đã đăng bài viết của bạn tại: https://cec.net.vn/p/%s/",
				"%s Wow, thật bất ngờ . Bạn tiến bộ nhanh quá. Để theo dõi sự tiến bộ hàng ngày của mk, bạn truy cập vào link này nhé: https://cec.net.vn/p/%s/",
				"%s ngữ điệu của bạn thật tuyệt vời, tiếp tục luyện tập nhé . Mình đã đăng bài viết của bạn tại: https://cec.net.vn/p/%s/",
				"%s you make me so proud of you and how far you have come in conquering English. I love seeing your video each day. This is another awesome video. Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s this is an amazing video. Your English pronunciations are outstanding. Click here to follow your progress: https://cec.net.vn/p/%s/",
				 "%s you did an awesome job on this video! I'm so proud of you. Keep up the great job. Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s you are making progress! Keep on practicing. Your English pronunciations sound great, i'm so proud of you. Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s, bạn nói hay quá <3. Để tiện theo dõi, bài của bạn được đăng ở https://cec.net.vn/p/%s/",
				 "%s, bạn nói quá hay :-*. Hãy xem lại bài của mình trên website của group chúng ta nhé: https://cec.net.vn/p/%s/",
				 "%s, Great <3. Mình đã đăng lại bài của bạn trên group của chúng ta: https://cec.net.vn/p/%s/",
				 "Bạn nói rất tốt, %s. Mình đã đăng bài của bạn trên https://cec.net.vn/p/%s/",
				 "Bạn nói hay quá, %s. Mình đã up bài của bạn lên https://cec.net.vn/p/%s/ rồi nhé",
				 "Quá tuyệt, %s ạ. Bài của bạn, mình đã đăng lên https://cec.net.vn/p/%s/",
				 "Tiếp tục phát huy nhé, %s. Hãy theo dõi sự tiến bộ của bạn trên này nhé: https://cec.net.vn/p/%s/",
				 "Bạn thật tuyệt vời, %s ạ. Bài của bạn đã được đăng trên https://cec.net.vn/p/%s/",
				 "Thật tuyệt vời không thể tin được %s, mình đã phải đăng nhập để like cho bạn .Video của bạn được mình cất ở https://cec.net.vn/p/%s/ nhé ! ",
				 "%s Love this video.You have such an amazing speaking voice! i love hearing that self- confidence and enthusiasm in your awesome speaking voice. Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s Good Job on this video about progress! I'm so proud of you. Keep up the great work. This video is outstanding. Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s Now that is an awesome video ! I am so proud of you! Love this video https://cec.net.vn/p/%s/",
				 "%s Such an amazing video! You are so awesome!! I am so proud of you. Love all that happy energy and vibes that you bring to your videos! https://cec.net.vn/p/%s/",
				 "%s When you make a video, it is definitely amazing. Awesome job!! https://cec.net.vn/p/%s/",
				 "%s Awesome job on this video. your English pronunciations are outstanding. i hope we see you doing in the teaching field in the future. Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s This is an outstanding video! I always love hearing your videos because you are so skilled in English conversations. I'm so pround of you. keep up the great work. You did an awesome job . Click here to follow your progress : https://cec.net.vn/p/%s/",
				 "%s The one video I always look forward to seeing. You are do awesome with English. I'm so proud of you. i love your awesome speaking voice and your outstanding English pronunciations . Click here to follow your progress : https://cec.net.vn/p/%s/"
				 
				
		};
		
		String[] commentRepoContext = {
				"Mình luôn theo dõi hành trình của bạn. Cố lên nhé chỉ còn %s nữa thôi ."
				
		};
		String post_id = mp.getId().replaceAll("1784461175160264_", "");
		int idx = new Random().nextInt(commentRepo.length);
		String comment_content = String.format(commentRepo[idx], mv.getPosterName(), post_id);

		String video_id = mv.getPermalink();
		video_id = video_id.replaceAll("www", "m");
		System.out.println("video_id:" + video_id);
		driver.get(video_id);
		driver.findElement(By.cssSelector("a._15ko")).click();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("a._15kq")).click();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement currentElement = driver.switchTo().activeElement();

		currentElement.sendKeys(comment_content);
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentElement.submit();
		System.out.println("Find lesson string ... "+mp.getContent().toLowerCase().contains("les"));
		if (mp.getContent().toLowerCase().contains("les")) {
			
			Pattern p = Pattern.compile("(\\d+)");
			Matcher m = p.matcher(mp.getContent().toLowerCase());
			if (m.find()) {
			
				System.out.println(m.group());
				int lesson = Integer.parseInt(m.group());
				System.out.println("Find lesson number :  "+lesson);
				lesson++;
				String validate = MD5(lesson + mp.getPosterId());
				messengerNextLesson(driver, mp.getPosterId(),
						"Chào "+mv.getPosterName()+" đây là link bài tiếp theo : https://cec.net.vn/lesson/" + lesson + "?v=" + validate+"&me="+mp.getPosterId());
			}

		}

		Pattern p = Pattern.compile("(\\d+)(/|\\.)(\\d+)");
		Matcher m = p.matcher(mp.getContent().toLowerCase());
		if (m.find()) {

			String spliter[] = m.group().split("/|\\.");
			int current = Integer.parseInt(spliter[0]);
			int total = Integer.parseInt(spliter[1]);
			
			String msg = "Chúc mừng "+mv.getPosterName()+" đã hoàn thành video số " + current + " trong hành trình " + total
					+ " ngày nói tiếng anh liên tục. Tiếp tục cố gắng nhé, mình sẽ theo dõi hành trình của bạn.";
			
			if(current==total)
			{
				msg = "Chúc mừng "+mv.getPosterName()+" đã hoàn thành hành trình " + total
						+ " ngày nói tiếng anh liên tục. Bạn là thiên tài của mình, rất ít người làm được như bạn.Chờ bạn trong những hành trình tiếp theo";
			}
			messengerNextLesson(driver, mp.getPosterId(), msg);
			System.out.println(msg);

		}

	}

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static void crawlVideo(WebDriver driver) throws UnsupportedEncodingException {
		
		
		Key<MemberVideo> key = ofy().load().type(MemberVideo.class).filter("status", "new").keys().first().now();
		if(key==null) 
			{
			findNewVideo(driver);	
			return;
			}
		String video_id = key.getName();
		driver.get(video_id);
		MemberVideo mv = new MemberVideo();
		mv.setId(video_id);
		mv.setStatus("processed");

		String url = driver.getCurrentUrl();
		if(url.contains("pending"))
		{
			System.out.println("Pending post need approved");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		mv.setPermalink(url);

		System.out.println(url);
		WebElement userContentWrapper = null;
		try {
			userContentWrapper = driver.findElement(By.className("userContentWrapper"));
		} catch (Exception e) {
			mv.setStatus("Error");
			ofy().save().entities(mv);
			return;
		}

		WebElement userContent = userContentWrapper.findElement(By.className("userContent"));
		mv.setContent(userContent.getText().replaceAll("Xem bản dịch", ""));

		System.out.println(userContent.getText());
		WebElement userProfileElement = null;
		try {
			userProfileElement = userContentWrapper.findElement(By.cssSelector(".fwb a"));
		}catch(Exception e)
		{
			mv.setStatus("Profile Error");
			ofy().save().entities(mv);
			return;
		}
		String name = userProfileElement.getText();
		System.out.println(name);
		mv.setPosterName(name);

		String facebookID = Utils.splitQuery(userProfileElement.getAttribute("ajaxify")).get("member_id");
		System.out.println(facebookID);
		mv.setPosterId(facebookID);
		
		Member mb = ofy().load().type(Member.class).id(Long.parseLong(facebookID)).now();
		if(mb==null)
		{
			mb = new Member();
			mb.setName(name);
			mb.setId(facebookID);
			ofy().save().entities(mb);
		}
		
		
		String timespam = userContentWrapper.findElement(By.tagName("abbr")).getAttribute("data-utime");

		mv.setCreatedDate(Long.parseLong(timespam) * 1000);

		System.out.println(mv.getCreatedDate());

		String post_id = url.replaceAll("https://www.facebook.com/groups/cec.edu.vn/permalink/", "").replaceAll("/",
				"");

		MemberPost post = ofy().load().type(MemberPost.class).id("1784461175160264_" + post_id).now();
		if(post!=null)
		{
			mv.setStatus("Inserted");
			ofy().save().entities(mv).now();
			
			return;
		}

		post = new MemberPost();

		post.setContent(mv.getContent());
		post.setCreatedDate(mv.getCreatedDate());
		post.setId("1784461175160264_" + post_id);
		post.setPosterId(mv.getPosterId());
		post.setAttachments("{type:\"video_inline\",url:\"" + mv.getId() + "\"   }");

		ofy().save().entities(post, mv).now();

		commentVideo(driver, mv, post);

	}

	public static void findNewVideo(WebDriver driver) { 
		driver.get("https://www.facebook.com/groups/cec.edu.vn/videos/");

		List<WebElement> videosDiv = driver.findElements(By.className("itemsPlutoniumRedesign"));
		ArrayList<MemberVideo> memberVideoList = new ArrayList<MemberVideo>();
		for (WebElement webElement : videosDiv) {

			try {

				String video_id = webElement.findElement(By.tagName("a")).getAttribute("href");
				if (video_id.startsWith("https://www.facebook.com/")) {

					System.out.println("Found " + video_id);

					Object check = ofy().load().type(MemberVideo.class).id(video_id).now();
					if (check != null) {
						System.out.println("But it exist !!! then break  ");
						continue;
					}
					MemberVideo mv = new MemberVideo();
					mv.setId(video_id);
					mv.setStatus("new");
					memberVideoList.add(mv);

				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		ofy().save().entities(memberVideoList);

	}

}
