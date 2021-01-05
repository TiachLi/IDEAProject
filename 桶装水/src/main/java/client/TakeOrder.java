package client;

import config.Config;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import entity.*;

public class TakeOrder {
    final static String SERVERIP = Config.getValue("SERVERIP");
    final static String PORT     = Config.getValue("communicationPort");
    static Order order = null;
    Socket socket;
    static boolean isSuccess = false;
    public TakeOrder(Order order) throws IOException {
        this.order = order;
        System.out.println("下单");
        // 1、使用Socket 创建客户端
        socket = new Socket(SERVERIP, Integer.parseInt(PORT));
    }

    //不是Thread类的start;
    public boolean start() {
        new Send(socket).send();
        // 接收服务端响应
        new Receive(socket).receive();
        // 3、释放资源
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // 发送
    static class Send{
        private Socket socket;
        private ObjectOutputStream dos;
        private String msg;
        public Send(Socket socket) {
            try {
                this.socket = socket;
                dos = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                dos.writeObject(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 接收
    static class Receive{
        private Socket register;
        private DataInputStream dis;
        public Receive(Socket register) {
            this.register = register;
            try {
                dis = new DataInputStream(register.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void receive() {
            String response;
            try {
                response = dis.readUTF();
                if (response.equals("1")){
                    System.out.println("恭喜你下单成功");
                    isSuccess = true;
                } else {
                    System.out.println("很遗憾下单失败");
                    isSuccess = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
