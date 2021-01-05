package server;

import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImpl;
import dao.UserDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dao.impl.UserDaoImpl;
import entity.*;
/**
 基于TCP协议的用户登录
 */
public class Server {
    final static UserDaoImpl userDao = new UserDaoImpl();
    final static ProductDaoImpl productDaoImpl = new ProductDaoImpl();
    final static OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    static List<Product> products;
    static List<Order> orders;
    public Server() throws IOException {
        System.out.println("=========server========");
        // 1、使用serverSocket 创建服务端
        ServerSocket server = new ServerSocket(8888);
        boolean isRuning = true;
        userDao.getConnection();
        productDaoImpl.getConnection();
        products = productDaoImpl.getAll();
        while (isRuning) {
            // 2、阻塞式连接
            Socket socket = server.accept();
            System.out.println("一个客户端建立了连接");
            // 3、操作
            new Thread(new Channel(socket)).start();
            products = productDaoImpl.getAll();
        }
        server.close();
    }

    // 一个Channel 代表一个客户端
    static class Channel implements Runnable{
        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;
        private ObjectOutputStream objectOutputStream;

        public Channel(Socket socket) {
            this.socket = socket;
            try {
                // 输入
                dis = new DataInputStream(socket.getInputStream());
                // 输出
                dos = new DataOutputStream(socket.getOutputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release(); // 一个出错就不用玩了
            }

        }

        // 接收数据
        public String receive() {
            String datas = "";
            try {
                datas = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return datas;
        }

        // 发送数据
        public void send(String msg) {
            try {
                dos.writeUTF(msg);
                if(msg.equals("1")){
                objectOutputStream.writeObject(products);
                objectOutputStream.writeObject(orders);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 释放资源
        public void release() {
            try {
                if(null != dos)
                    dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != dis)
                    dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != dos)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            // 3、操作
            String datas = receive();
            // 解析用户信息
            String[] dataArray = datas.split("&");
            String uname = null;
            String pwd = null;
            for (String info : dataArray) {
                String[] userInfo = info.split("=");
                if ("uname".equals(userInfo[0])) {
                    uname = userInfo[1];
                    System.out.println("用户名：" + uname);
                } else if ("pwd".equals(userInfo[0])) {
                    pwd = userInfo[1];
                    System.out.println("密码：" + pwd);
                }
            }
            try {
                if (!userDao.isConnect())
                {
                    userDao.Connect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (userDao.checkingAccount(uname,pwd)) {
                orderDaoImpl.getConnection();
                orders = orderDaoImpl.getOrderByID(uname);
                send("1");
            } else {
                send("0");
            }
            // 4、释放资源
            release();
        }

    }
}
