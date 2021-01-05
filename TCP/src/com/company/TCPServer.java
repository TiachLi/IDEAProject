package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {


    public static void main(String[] args) throws IOException {
        ServerSocket serve =new ServerSocket(8888);
        Socket socket = serve.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes=new byte[1024];
        inputStream.read(bytes);
        System.out.println(new String(bytes));
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("收到谢谢".getBytes());
        socket.close();
        serve.close();
    }}
