import java.net.*;
import java.io.*;

public class MultiThreadServer extends Thread {
    private Socket socket = null;

    public MultiThreadServer(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    public void run() {
        System.out.println("Working so far");
    }
}