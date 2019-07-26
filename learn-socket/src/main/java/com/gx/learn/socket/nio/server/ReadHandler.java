package com.gx.learn.socket.nio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * read 事件适配器
 *
 * @author 郭翔
 */
@Slf4j
public class ReadHandler implements Runnable {
  private static final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
  private SocketChannel socketChannel = null;

  ReadHandler(SocketChannel socketChannel, Selector selector) throws IOException {
    this.socketChannel = socketChannel;
    this.socketChannel.configureBlocking(false);
    SelectionKey key = this.socketChannel.register(selector, SelectionKey.OP_READ);
    key.attach(this);
    selector.wakeup();
  }

  @Override
  public void run() {
    try {
      log.info("sss");
      socketChannel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
