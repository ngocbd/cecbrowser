package net.cec.entity;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
public class PostFB {
	@Id
	private long id;
	@Index
	private String url;
	@Index
	private String posterID;
	@Index
	private DataFB dataFB;
	@Index
	private List<String> likes;
	@Index
	private List<String> comments;
	@Index
	private String content;
	
	
	public PostFB(String posterID){
		this.id=System.currentTimeMillis();
		this.posterID=posterID;
		dataFB = new DataFB();
		dataFB.setLike(0);
		dataFB.setPost(0);
		dataFB.setShares(0);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPosterID() {
		return posterID;
	}
	public void setPosterID(String posterID) {
		this.posterID = posterID;
	}
	public DataFB getDataFB() {
		return dataFB;
	}
	public void setDataFB(DataFB dataFB) {
		this.dataFB = dataFB;
	}
	public List<String> getLikes() {
		return likes;
	}
	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
		
	

}
