import java.net.*;
import java.io.*;
 
public class MultiServerThread extends Thread {
    private Socket socket = null;
    
    public MultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    }