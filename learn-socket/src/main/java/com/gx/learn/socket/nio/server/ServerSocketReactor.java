package com.gx.learn.socket.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * server socket 客户端
 *
 * @author 郭翔
 */
public class ServerSocketReactor implements Runnable {

  private static Selector selector = null;
  private static ServerSocketChannel serverSocket = null;

  ServerSocketReactor(int port) throws IOException {
    // init selector
    selector = Selector.open();
    // init serverSocket 并设置为异步
    serverSocket = ServerSocketChannel.open();
    serverSocket.socket().bind(new InetSocketAddress(port));
    serverSocket.configureBlocking(false);
    // serverSocket 注册到 selector, 并监听 OP_ACCEPT 事件
    SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    // 为此事件设置 "附件" (处理适配器)
    selectionKey.attach(new Acceptor());
  }

  @Override
  public void run() {
    try {
      while (!Thread.interrupted()) {
        if (selector.select(3000) == 0) {
          continue;
        }
        // 所有出发注册事件的管道, 处理
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey selectionKey : selectionKeys) {
          dispatch(selectionKey);
        }
        // 清空
        selectionKeys.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 统一处理所有管道触发的注册事件
   *
   * @param selectionKey token
   */
  private void dispatch(SelectionKey selectionKey) {
    Runnable attachment = (Runnable) selectionKey.attachment();
    if (attachment != null) {
      attachment.run();
    }
  }

  static class Acceptor implements Runnable {
    @Override
    public void run() {
      try {
        // 获取 socketChannel 并注册为异步
        SocketChannel socketChannel = serverSocket.accept();
        socketChannel.configureBlocking(false);
        // 作为服务端 socket, 监听 read 事件
        SelectionKey read = socketChannel.register(selector, SelectionKey.OP_READ);
        read.attach(new ReadHandler(socketChannel, selector));
        System.out.println("ADD: " + socketChannel);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
