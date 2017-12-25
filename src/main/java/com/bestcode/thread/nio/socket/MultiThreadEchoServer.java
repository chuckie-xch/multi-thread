package com.bestcode.thread.nio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ServiceConfigurationError;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * echo服务器
 * 服务器会为每一个客户端连接启动一个线程，为客户端服务，并且，为了接收客户端连接，服务器还会
 * 额外使用一个派发线程
 *
 * @author xch
 * @create 2017-12-16 14:22
 **/
public class MultiThreadEchoServer {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable {

        Socket clientSocket;

        public HandleMsg(Socket socket) {
            this.clientSocket = socket;
        }
        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(), true);
                String inputline = null;
                long beginTime = System.currentTimeMillis();
                while ((inputline = is.readLine()) != null) {
                    os.println(inputline);
                }
                long endTime = System.currentTimeMillis();
                System.out.println("spend:" + (endTime-beginTime) + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            ServerSocket echoServer = null;
            Socket clientSocket = null;
            try {
                echoServer = new ServerSocket(8000);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    clientSocket = echoServer.accept();
                    System.out.println(clientSocket.getRemoteSocketAddress() + "connet!");
                    threadPool.execute(new HandleMsg(clientSocket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
