package com.mtx.metro.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class MyClient {
    String HOST = "192.168.199.1";
    int PORT = 12345;

    PrintStream out = null;
    OutputStream outputStream = null;
    InputStream inputStream = null;

    // 访问服务进程的套接字
    Socket socket = null;
    byte[] bytes = new byte[1024 * 4];

    public MyClient(){
        try {
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket(HOST,PORT);
            // 获取输出流对象
            outputStream = socket.getOutputStream();
//            out= new PrintStream(outputStream);
            inputStream = socket.getInputStream();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(String req) throws IOException {
        // 发送内容
//        out.print(str);
        // 告诉服务进程，内容发送完毕，可以开始处理
//        out.print("over");
//        out.flush();
        outputStream.write(req.getBytes());
    }


    public String receive() throws IOException {
        // 获取服务进程的输入流
        //输入流
        int len = inputStream.read(bytes);
        return new String(bytes,0,len);
    }

    public void close() throws IOException {
        socket.close();
    }


}
