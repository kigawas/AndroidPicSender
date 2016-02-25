import java.net.*;
import java.io.*;

public class ServerTcpListener {

    public static void main(String[] args) {

        try {
            final ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress("0.0.0.0", 4242));

            while (true) {
                try {
                    System.out.println("Start...");
                    Socket socket = server.accept();
                    System.out.println("Success");
                    receiveFile(socket);

                } catch (Exception e) {
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
    }

    public static void receiveFile(Socket socket) {

        byte[] inputByte = null;
        int length = 0;
        DataInputStream dis = null;
        FileOutputStream fos = null;
       
            try {

                dis = new DataInputStream(socket.getInputStream());
                fos = new FileOutputStream(new File("C:\\Users\\Will\\Desktop\\aaabbb.PNG"));
                inputByte = new byte[1024 * 4];
                System.out.println("Receiving...");
                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
                    fos.write(inputByte, 0, length);
                    fos.flush();
                }
                System.out.println("Finish");


                //return the result
                String res = "Finish";
                OutputStream out = socket.getOutputStream();
                out.write(res.getBytes());
                //closing resources
                socket.close();
                dis.close();
                fos.close();
                out.close();
            } catch (Exception e){e.printStackTrace();}
        
    }
}