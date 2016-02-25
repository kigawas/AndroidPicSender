import java.io.*;
import java.net.*;

class SampleClient {
    public static void main(String[] args) {
        SampleClient c = new SampleClient();
        c.sendFileGetResult(new File("C:\\Users\\Will\\Desktop\\aaa.PNG"), "192.168.0.102", 4242, 2);
    }


    private String sendFileGetResult(File file, String url, int port, int secTimeout) {
        Socket s = new Socket();
        String res = "";

        try {
            s.connect(new InetSocketAddress(url, port), secTimeout * 1000);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            FileInputStream fis = new FileInputStream(file);
            byte [] sendBytes = new byte[1024 * 4];
            int len = 0;

            while ((len = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
                dos.write(sendBytes, 0, len);
                dos.flush();
            }
            s.shutdownOutput();

            //get result
            InputStream in = s.getInputStream();
            byte[] result = new byte[1024];
            int num = in.read(result);
            
            res = new String(result, 0, num);
            System.out.println(res);

            //closing resources
            s.close();
            dos.close();
            fis.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            return res;
        }
    }
}
