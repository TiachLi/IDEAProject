package com.company;

import sun.security.util.Length;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient
{

    public static void main(String[] args) throws IOException {
	// write your code hereby
        Socket socket =new Socket("127.0.0.1",8888);
        OutputStream os = socket.getOutputStream();
        os.write("你好服务器".getBytes());
        InputStream in = socket.getInputStream();
        byte[] bytes =new byte[1024];
        in.read(bytes);
        System.out.println(new String(bytes));
        socket.close();
    }
}
