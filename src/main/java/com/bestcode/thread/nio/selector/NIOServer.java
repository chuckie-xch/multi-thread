package com.bestcode.thread.nio.selector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio
 *
 * @author xch
 * @create 2017-12-16 14:54
 **/
public class NIOServer {
    private Selector selector;
    private ExecutorService pool = Executors.newCachedThreadPool();
    public static Map<Socket, Long> time_stat = new HashMap<Socket, Long>(10240);

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
        ssc.socket().bind(inetSocketAddress);
        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        for (;;) {
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator iterator = readyKeys.iterator();
            long e = 0;
            while (iterator.hasNext()) {
                SelectionKey sk = (SelectionKey) iterator.next();
                iterator.remove();
                if(sk.isAcceptable()) {
//                    doAccept();
                }
//                else if(sk.isValid() && sk.isReadable()) {
//                    if(!time_stat.containsKey(((SocketChannel) sk.channel())).socket()) {
//
//                    }
//                }
            }
        }
    }
}
