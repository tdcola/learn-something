package com.gx.learn.socket.nio.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 测试
 *
 * @author 郭翔
 */
public class ClientTest {

  private static final byte[] DATA = "hello world nio".getBytes();

  public static void main(String[] args) throws IOException {
    // get channel
    SocketChannel channel = SocketChannel.open();
    channel.configureBlocking(false);
    // connect
    channel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 12580));
    channel.finishConnect();
    System.out.println("Connected to server complete");
    // get two buffer: dataBuffer, emptyBuffer
    ByteBuffer dataBuffer = ByteBuffer.wrap(DATA);
    // send msg
    while (dataBuffer.hasRemaining()) {
      channel.write(dataBuffer);
    }
    // close
    channel.close();
  }
}
