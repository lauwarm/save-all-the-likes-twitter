package io.github.lauwarm;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import twitter4j.ExtendedMediaEntity;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.ExtendedMediaEntity.Variant;

public class TwitterMedia {
	
	private static Long currentTimestamp = 0l;
	private static Long oldTimestamp = TimeStamps.getTimestampOld();
	private static Twitter twitter = null;
	private static User user = null;
	private static Paging paging = null;
	private static List<Status> list = null;
	
	private static final String path = "./download/";
	
	public TwitterMedia() {
		try {
			setTwitter(new TwitterFactory().getInstance());
			setUser(getTwitter().verifyCredentials());
			setPaging(new Paging(1));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public static Long getCurrentTimestamp() {
		return currentTimestamp;
	}

	public static void setCurrentTimestamp(Long currentTimestamp) {
		TwitterMedia.currentTimestamp = currentTimestamp;
	}

	public static Long getOldTimestamp() {
		return oldTimestamp;
	}

	public static void setOldTimestamp(Long oldTimestamp) {
		TwitterMedia.oldTimestamp = oldTimestamp;
	}

	public static Twitter getTwitter() {
		return twitter;
	}

	public static void setTwitter(Twitter twitter) {
		TwitterMedia.twitter = twitter;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		TwitterMedia.user = user;
	}

	public static Paging getPaging() {
		return paging;
	}

	public static void setPaging(Paging paging) {
		TwitterMedia.paging = paging;
	}

	public static List<Status> getList() {
		return list;
	}

	public static void setList(List<Status> list) {
		TwitterMedia.list = list;
	}

	public static Long getTimestampOfNewestLike() throws TwitterException {
		return twitter.getFavorites().get(0).getCreatedAt().getTime();
	}
	
	public static void crawlFavoriteList() throws TwitterException, IOException, InterruptedException {
		do {
			list = twitter.getFavorites(paging);
			
			for(Status s : list) {
				currentTimestamp = s.getCreatedAt().getTime();
				
				for(ExtendedMediaEntity fav : s.getExtendedMediaEntities()) {
					if(fav.getType().equals("photo")) {
						DownloadFile.downloadFile(fav.getMediaURLHttps() + ":large", path, 
								DownloadFile.fileNameBuilder(s.getUser().getScreenName(), s.getCreatedAt().getTime(), 
										fav.getId(), ".jpg"));
					}
					else if(fav.getType().equals("video")) {
						int bitrate = 0;
						String url = "";
						for(Variant vario: fav.getVideoVariants()) {
							if (vario.getBitrate() > bitrate) {
								url = vario.getUrl();
								bitrate = vario.getBitrate(); 
							}
						}
						DownloadFile.downloadFile(url, path, 
								DownloadFile.fileNameBuilder(s.getUser().getScreenName(), s.getCreatedAt().getTime(), 
										fav.getId(), ".mp4"));
					}
					else if(fav.getType().equals("animated_gif")) {
						for(Variant vario : fav.getVideoVariants()) {
							DownloadFile.downloadFile(vario.getUrl(), path, 
									DownloadFile.fileNameBuilder(s.getUser().getScreenName(), s.getCreatedAt().getTime(), 
											fav.getId(), ".mp4"));
						}
					}
					else {
						System.out.println("hmm");
					}
				}
			}
			
			paging.setPage(paging.getPage() + 1);
			TimeUnit.SECONDS.sleep(20);
		} while (list.size() > 0 && (getOldTimestamp() < currentTimestamp));
	}
}
