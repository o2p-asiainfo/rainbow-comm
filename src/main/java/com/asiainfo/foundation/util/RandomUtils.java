package com.asiainfo.foundation.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * @author 颖勤
 * @since 2.0
 */
public class RandomUtils {
	private static SecureRandom random ;
	private static RandomUtils rndUtils = new RandomUtils();
	private RandomUtils(){
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int nextInt(){
		if(random!=null){
			return random.nextInt();
		}
		return 0;
	}
	
	public static int nextInt(int n){
		if(random!=null){
			return random.nextInt(n);
		}
		return 0;
	}
	
	public static long nextLong(){
		if(random!=null){
			return random.nextLong();
		}
		return 0;		
	} 
	
	public static void main(String args[]){
		System.out.println(RandomUtils.nextInt());
	}
}
