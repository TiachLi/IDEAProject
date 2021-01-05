package TCPDocument;

import java.io.*;
import java.net.Socket;

public class FileClient {
    public static void main(String[] args) throws IOException {
        File file =new File("c:\\01.jpg");
        FileInputStream inputStream =new FileInputStream(file);
        Socket socket = new Socket("127.0.0.1",8888);

        OutputStream socketOutputStream = socket.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = inputStream.read(bytes))!=-1){
            socketOutputStream.write(bytes,0,len);
        }
        inputStream.close();
        socket.close();
    }
}
