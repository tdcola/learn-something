package com.gx.learn.socket.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 服务端测试
 *
 * @author 郭翔
 */
public class ServerTest {

  private static Selector selector;

  public static void main(String[] args) throws IOException {
    selector = Selector.open();
    // get server socket
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.bind(new InetSocketAddress(12580));
    // register
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      if (selector.select(3000) == 0) {
        continue;
      }
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      for (SelectionKey selectionKey : selectionKeys) {
        if (selectionKey.isAcceptable()) {
          ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
          SocketChannel socketChannel = channel.accept();
          socketChannel.configureBlocking(false);
          socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        }
      }
    }
  }
}
