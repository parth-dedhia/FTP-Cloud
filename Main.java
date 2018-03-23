import FTP.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFile;
public class Main{
	public static void main(String args[])
	{
		try{
			FTPConnection ftpconnection = new FTPConnection("192.168.1.33", "test1", "test1");
			FTPClient ftpc=ftpconnection.ftp;
			int n=0;
			while(n!=4){
			System.out.println("Enter a choice");
			System.out.println("1)Upload a file");
			System.out.println("2)Download a file");
			System.out.println("3)Delete a file");
			System.out.println("4)Exit the  system");
			Scanner input=new Scanner(System.in);	
			System.out.println("Enter your choice : ");
			n = input.nextInt();
			switch (n)
			{
				case 1:System.out.println("Enter the file u want to upload : ");
				       String upload=input.next();
			           FTPUploader ftpupload = new FTPUploader("C:/Users/prded/Desktop/"+upload,upload,"C:/Users/prded/Desktop/JAVA/",ftpc);
					   ftpupload.uploadFile();
					   System.out.println("File Uploaded Successfully!!\nFiles Present on server are : ");
					   new FilesOnServer(ftpc);
					   break;
				case 2:new FilesOnServer(ftpc);
				       System.out.println("Enter the file u want to download : ");
					   String file_name=input.next();
					   FTPDownloader ftpdownloader = new FTPDownloader(file_name,"/Users/prded/Desktop/"+file_name,ftpc);
					   ftpdownloader.downloadFile();
					   System.out.println("FTP File downloaded successfully");
					   break;
				case 3:new FilesOnServer(ftpc);
				       System.out.print("Enter the name of the file to be deleted : ");
					   file_name=input.next();
					   FTPDelete ftpdelete = new FTPDelete(file_name,ftpc);
			           ftpdelete.delete_file();
					   break;
			    case 4:Main useless = new Main();
			           useless.disconnect(ftpc);
			}
	}}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void disconnect(FTPClient ftpc) {
			if (ftpc.isConnected()) {
				try {
					ftpc.logout();
					ftpc.disconnect();
				} catch (IOException f) {
                // do nothing as file is already downloaded from FTP server
            }
        }
    }
}