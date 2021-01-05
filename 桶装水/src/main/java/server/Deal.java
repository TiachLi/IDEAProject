package server;

import config.Config;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dao.impl.OrderDaoImpl;
import entity.*;
public class Deal extends Thread{
    ServerSocket serverSocket;
    String PORT = Config.getValue("communicationPort");
    ObjectInputStream objectInputStream;
    DataOutputStream dataOutputStream;
    OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    Order order;
    boolean isSuccess = false;
    public Deal(){
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (this.isAlive()){
            try {
                Socket socket = serverSocket.accept();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                receive();
                send(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void receive() {
        try {
            try {
                order = (Order) objectInputStream.readObject();
                orderDaoImpl.getConnection();
                try {
                    if (!orderDaoImpl.isConnect())
                    {
                        orderDaoImpl.Connect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isSuccess = orderDaoImpl.insertOrder(order);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(Socket socket){
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            if (isSuccess)
            dataOutputStream.writeUTF("1");
            else dataOutputStream.writeUTF("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 释放资源
    public void release() {
        try {
            if(null != objectInputStream)
                objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
