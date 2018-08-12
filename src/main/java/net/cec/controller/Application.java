package net.cec.controller;

import com.googlecode.objectify.ObjectifyService;

import net.cec.entity.Member;

public class Application {

	public static void main(String[] args) {
		ObjectifyService.init();
		ObjectifyService.register(Member.class);
		ObjectifyService.begin();
		
		
		
	}

}
