package FTP;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//import java.io.*;
import java.io.FileInputStream;
import java.io.InputStream;
import  java.io.File;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFile; 
public class FTPUploader
{
	private String localFileFullName;
	private String fileName;
	private FTPClient ftp;
	private String hostDir;
	public FTPUploader(String localFileFullName, String fileName, String hostDir, FTPClient ftp)
	{
		this.localFileFullName=localFileFullName;
		this.fileName=fileName;
		this.hostDir=hostDir;
		this.ftp=ftp;
	}
	public void uploadFile() throws Exception
	{
		
		try(InputStream input = new FileInputStream(new File(localFileFullName))){
		this.ftp.storeFile(fileName, input);
		
	}
	}
}