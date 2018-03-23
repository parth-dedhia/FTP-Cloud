package FTP;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFile; 
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
public class FTPDownloader{
	private String remoteFilePath;
	private String localFilePath;
	private FTPClient ftp;
	public FTPDownloader(String remoteFilePath, String localFilePath,FTPClient ftp)
	{
		this.remoteFilePath=remoteFilePath;
		this.localFilePath=localFilePath;
		this.ftp=ftp;
	}
	public void downloadFile() {
		System.out.println("Download begins");
		
        try (FileOutputStream fos = new FileOutputStream(localFilePath)){
            this.ftp.retrieveFile(remoteFilePath, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}