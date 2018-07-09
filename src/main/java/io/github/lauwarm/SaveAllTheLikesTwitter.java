package io.github.lauwarm;

import java.io.IOException;
import twitter4j.TwitterException;

/**
 * @author fabian
 *
 */
public class SaveAllTheLikesTwitter {

	public static void main(String[] args) throws IOException {
		
		ConfigFile.readConfigFile();
		new AccessTokens();
		new TimeStamps();
		new TwitterMedia();
		
		Long timestampNewestFavorite = null;
		try {
			timestampNewestFavorite = TwitterMedia.getTimestampOfNewestLike();
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}
		
		try {
			TwitterMedia.crawlFavoriteList();
		} catch (TwitterException | InterruptedException e) {
			e.printStackTrace();
		}
	
		TimeStamps.setTimestampOld(timestampNewestFavorite);
			
		System.out.println("Saving Config File!");
		ConfigFile.writeConfigFile();
			
		System.out.println("DONE!");
		System.exit(0);
	}
	
}
