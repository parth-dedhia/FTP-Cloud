package FTP;
import java.lang.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFile; 
public class FTPDelete
{
	private String file_name;
	private FTPClient ftp;
	public FTPDelete(String file_name,FTPClient ftp){
		this.file_name=file_name;
		this.ftp=ftp;
		}
		public void delete_file(){
			try{
				boolean delete = ftp.deleteFile(file_name);  
				if (delete) {  
				System.out.println("File deleted successfully !");  
				} else {  
				System.out.println("Error in deleting file !");  
				}
			}
			catch (Exception e){
						//
					}			
	}
}