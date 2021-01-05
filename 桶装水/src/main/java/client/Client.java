package client;

import java.io.*;
import java.net.Socket;
import java.util.List;

import config.Config;
import entity.*;

public class Client {
    public static List<Product> products;
    public static List<Order> Order;
    final static String SERVERIP = Config.getValue("SERVERIP");
    final static String PORT     = Config.getValue("PORT");
    static User user;
    static boolean isSuccess;

    public boolean Client(User user) throws IOException {
        System.out.println("=========client========");
        // 1、使用Socket 创建客户端
        Socket client = new Socket(SERVERIP, Integer.parseInt(PORT));
        this.user = user;
        // 2、操作
        // 输入
        new Send(client).send();
        // 接收服务端响应
        new Receive(client).receive();
        // 3、释放资源
        client.close();
        return isSuccess;
    }

    // 发送
    static class Send{
        private Socket client;
        private BufferedReader br;
        private DataOutputStream dos;
        private String msg;
        public Send(Socket client) {
            try {
                this.client = client;
                br = new BufferedReader(new InputStreamReader(System.in));
                msg = "uname=" + user.getID() +"&pwd=" + user.getPSW();
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                dos.writeUTF(this.msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 接收
    static class Receive{
        private Socket client;
        private DataInputStream dis;
        private ObjectInputStream objectInputStream;
        public Receive(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                objectInputStream = new ObjectInputStream(client.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void receive() {
            String response;
            try {
                response = dis.readUTF();
                if (response.equals("1"))
                    isSuccess = true;
                else isSuccess = false;
                try {
                    if (isSuccess){
                    products = (List<Product>)objectInputStream.readObject();
                    Order = (List<Order>)objectInputStream.readObject();
                    }
                    for (Product product: products)
                    {
                        System.out.println(product.toString());
                    }
                    for (Order order: Order)
                    {
                        System.out.println(order.toString());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static User getUser() {
        return user;
    }

    public  List<Product> getProducts() {
        return products;
    }

    public  List<Order> getOrder() {
        return Order;
    }

}
