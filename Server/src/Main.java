import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Math.round;

public class Main {
    public static void main (String[] args) throws AWTException {
        Robot robot=new Robot();
        try(ServerSocket server=new ServerSocket(8080)) {
            try(Socket socket=server.accept()) {
                System.out.println("Connected");
                DataInputStream in=new DataInputStream(socket.getInputStream());
                boolean isX=true;
                float dx=0;
                float dy=0;
                while(true) {
                        dx=in.readFloat();
                        dy=in.readFloat();
                        System.out.println("moved "+dx+" "+dy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
