package io.github.lauwarm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @author fabian
 *
 */
public class DownloadFile {

	public DownloadFile() {
		// TODO Auto-generated constructor stub
	}

	public static void downloadFile(String mediaURL, String filePath, String fileName) throws IOException {
		try {
			URL url = new URL (mediaURL);
			String path = filePath + fileName;
			
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(new File(path));
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();

			System.out.println("downloaded: " + mediaURL + " | " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String fileNameBuilder(String user, Long timestamp, Long id, String extension) {
		
		return new String(user + "_" + timestamp + "_" + id + extension);
	}
}
