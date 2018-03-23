import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import java.io.File;
import java.io.IOException;
import org.apache.ftpserver.usermanager.*;
import java.util.*;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.impl.*;
import FTP.*;

class Server1{
public static void main(String args[]) throws IOException
{
FtpServerFactory serverFactory = new FtpServerFactory();
ListenerFactory factory = new ListenerFactory();
factory.setPort(21);// set the port of the listener (choose your desired port, not 1234)
serverFactory.addListener("default", factory.createListener());
PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
userManagerFactory.setFile(new File("c:\\Users\\prded\\Desktop\\JAVA\\copy\\users.properties"));//choose any. We're telling the FTP-server where to read it's user list
userManagerFactory.setPasswordEncryptor(new PasswordEncryptor()
{//We store clear-text passwords in this example

        @Override
        public String encrypt(String password) {
            return password;
        }

        @Override
        public boolean matches(String passwordToCheck, String storedPassword) {
            return passwordToCheck.equals(storedPassword);
        }
    });
    //Let's add a user, since our myusers.properties files is empty on our first test run
    BaseUser user = new BaseUser();
    user.setName("test1");
    user.setPassword("test1");
    user.setHomeDirectory("c:\\Users\\prded\\Desktop\\JAVA");
    List<Authority> authorities = new ArrayList<Authority>();
    authorities.add(new WritePermission());
    user.setAuthorities(authorities);
    UserManager um = userManagerFactory.createUserManager();
    try
    {
        um.save(user);//Save the user to the user list on the filesystem
    }
    catch (FtpException e1)
    {
        //Deal with exception as you need
    }
    serverFactory.setUserManager(um);
    Map<String, Ftplet> m = new HashMap<String, Ftplet>();
    m.put("miaFtplet", new Ftplet()
    {

        @Override
        public void init(FtpletContext ftpletContext) throws FtpException {
            System.out.println("init");
            System.out.println("Thread #" + Thread.currentThread().getId());
        }

        @Override
        public void destroy() {
            System.out.println("destroy");
            System.out.println("Thread #" + Thread.currentThread().getId());
        }

        @Override
        public FtpletResult beforeCommand(FtpSession session, FtpRequest request) throws FtpException, IOException
        {
            System.out.println("beforeCommand " + session.getUserArgument() + " : " + session.toString() + " | " + request.getArgument() + " : " + request.getCommand() + " : " + request.getRequestLine());
            System.out.println("Thread #" + Thread.currentThread().getId());

            //do something
            return FtpletResult.DEFAULT;//...or return accordingly
        }

        @Override
        public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply) throws FtpException, IOException
        {
            System.out.println("afterCommand " + session.getUserArgument() + " : " + session.toString() + " | " + request.getArgument() + " : " + request.getCommand() + " : " + request.getRequestLine() + " | " + reply.getMessage() + " : " + reply.toString());
            System.out.println("Thread #" + Thread.currentThread().getId());

            //do something
            return FtpletResult.DEFAULT;//...or return accordingly
        }

        @Override
        public FtpletResult onConnect(FtpSession session) throws FtpException, IOException
        {
            System.out.println("onConnect " + session.getUserArgument() + " : " + session.toString());
            System.out.println("Thread #" + Thread.currentThread().getId());

            //do something
            return FtpletResult.DEFAULT;//...or return accordingly
        }

        @Override
        public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException
        {
            System.out.println("onDisconnect " + session.getUserArgument() + " : " + session.toString());
            System.out.println("Thread #" + Thread.currentThread().getId());

            //do something
            return FtpletResult.DEFAULT;//...or return accordingly
        }
    });
    serverFactory.setFtplets(m);
    Map<String, Ftplet> mappa = serverFactory.getFtplets();
    System.out.println(mappa.size());
    System.out.println("Thread #" + Thread.currentThread().getId());
    System.out.println(mappa.toString());
    FtpServer server = serverFactory.createServer();
    try
    {
        server.start();
		//System.out.println("FTP server started SUCCESSFULLY!!!!!!!!!");//Your FTP server starts listening for incoming FTP-connections, using the configuration options previously set
	}
    catch (FtpException ex)
    {
        //Deal with exception as you need
    }
	//String name;
	String name = user.getName();
	System.out.println(name);
	}
}