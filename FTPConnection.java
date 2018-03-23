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
public class FTPConnection{
	public FTPClient ftp = null;
	public FTPConnection(String host, String user, String pwd) throws Exception {
        ftp = new FTPClient();
        //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        boolean con=ftp.login(user, pwd);
		if (con==true)
		{
			System.out.println("Your connection has been successfull");
		}
        /**ftp.setFileType( FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
		FTPFile[] files = ftp.listFiles();  
		for (FTPFile file : files) {  
			if (file.getType() == FTPFile.FILE_TYPE) {  
			System.out.println("File Name: "  + file.getName());  
			}  
		} 
		*/
    }
}