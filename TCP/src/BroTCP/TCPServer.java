package BroTCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(8080);

        while (true){

            new Thread(new Runnable() {
                Socket accept = serverSocket.accept();
                public void run() {
                    try {
                        InputStream inputStream = accept.getInputStream();
                        /*int len=0;
                          byte[] bytes =new byte[1024];
                          while ((len=inputStream.read(bytes))!=-1){
                          System.out.println(new String(bytes));
                          }*/
                        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                        //把客户端请求信息的第一行读取出来 GET /11_Net/web/index.html HTTP/1.1
                        String line = br.readLine();
                        String[] arr = line.split(" ");
                        //把路径前边的/去掉,进行截取 11_Net/web/index.html
                        String htmlpath = arr[1].substring(1);
                        System.out.println(htmlpath);
                        //创建一个本地字节输入流,构造方法中绑定要读取的html路径
                        FileInputStream fis = new FileInputStream("C:\\IDEAProject\\"+htmlpath);
                        //使用Socket中的方法getOutputStream获取网络字节输出流OutputStream对象
                        OutputStream os = accept.getOutputStream();

                        // 写入HTTP协议响应头,固定写法
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type:text/html\r\n".getBytes());
                        // 必须要写入空行,否则浏览器不解析
                        os.write("\r\n".getBytes());
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while((len = fis.read(bytes))!=-1){
                            os.write(bytes,0,len);
                        }
                        fis.close();

                        accept.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
