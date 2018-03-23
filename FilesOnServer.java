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
public class FilesOnServer
{
	public FTPClient ftp = null;
	public FilesOnServer(FTPClient ftp)
	{
		this.ftp=ftp;
		try{
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
		FTPFile[] files = ftp.listFiles();  
		for (FTPFile file : files) {  
			if (file.getType() == FTPFile.FILE_TYPE) {  
			System.out.println("File Name: "  + file.getName());  
			}
			String details = file.getName();
			if (file.isDirectory()) {
        System.out.println("Directory Name: "  + details);
    }
		}			
		}
		catch (Exception e){}
	}
}