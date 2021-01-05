package server;

import config.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import dao.impl.UserDaoImpl;
import entity.*;
public class Register extends Thread{
    final static String registerPort = Config.getValue("registerPort");
    ServerSocket serverSocket;
    static UserDaoImpl userDao = new UserDaoImpl();
    static User user;
    static boolean isSuccess;
    @Override
    public void run() {
        boolean isRuing = true;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(registerPort));
            while (isRuing){
            Socket socket = serverSocket.accept();
            new Receive(socket).receive();
            new Send(socket).send();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 发送
    static class Send{
        private Socket socket;
        private DataOutputStream dos;
        private String msg;
        public Send(Socket socket) {
            try {
                this.socket = socket;
                dos = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void send() {
            try {
                if (isSuccess) {
                    msg = "1";
                }else{
                    msg = "0";
                }
                dos.writeUTF(this.msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 接收
    static class Receive{
        private Socket socket;
        private ObjectInputStream objectInputStream;
        public Receive(Socket socket) {
            this.socket = socket;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void receive() {
            try {
                try {
                    user = (User) objectInputStream.readObject();
                    userDao.getConnection();
                    int influence = userDao.insertUser(user.getID(),user.getPSW());
                    if (influence == 0)
                        isSuccess = false;
                    else isSuccess = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
