package client;

import java.io.*;
import java.net.Socket;

import config.Config;
import entity.*;

public class Register{
    final static String SERVERIP = Config.getValue("SERVERIP");
    final static String PORT     = Config.getValue("registerPort");
    static User user = null;
    Socket register;
    static boolean isSuccess = false;
    public Register(User user) throws IOException {
        this.user = user;
        System.out.println("Register");
        // 1、使用Socket 创建客户端
        register = new Socket(SERVERIP, Integer.parseInt(PORT));

    }

    //不是Thread类的start;
    public boolean start() {
        new Send(register).send();
        // 接收服务端响应
        new Receive(register).receive();
        // 3、释放资源
        try {
            register.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // 发送
    static class Send{
        private Socket register;
        private ObjectOutputStream dos;
        private String msg;
        public Send(Socket register) {
            try {
                this.register = register;
                dos = new ObjectOutputStream(register.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                dos.writeObject(user);
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
                System.out.println("恭喜你注册成功");
                isSuccess = true;
                } else {
                    System.out.println("很遗憾注册失败");
                    isSuccess = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
