package TCPDocument;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(8888);
        while (true){
            Socket socket = serverSocket.accept();
            InputStream socketInputStream = socket.getInputStream();
            File file =new File("d:\\test");
            String fileName = System.currentTimeMillis()+".jpg";
            FileOutputStream outputStream =new FileOutputStream(file+"\\"+fileName);
            int len =0;
            byte[] bytes = new byte[1024];
            while((len = socketInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            outputStream.write(bytes);
            socket.close();
            outputStream.close();
        }


    }
}
