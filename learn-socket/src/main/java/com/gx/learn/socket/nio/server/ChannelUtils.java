package com.gx.learn.socket.nio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * channel 工具类
 *
 * @author 郭翔
 */
public class ChannelUtils {

  private static final int CAPACITY = 1024;

  public static void read(SocketChannel channel) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(CAPACITY);
    byte[] array = new byte[CAPACITY];

    try {
      int read = channel.read(byteBuffer);
      while (read != -1) {
        System.out.println("GET: " + read);
        // set read mode
        byteBuffer.flip();
        byteBuffer.get(array);
        System.out.println(Arrays.toString(array));
        byteBuffer.clear();
        // again
        read = channel.read(byteBuffer);
      }
      channel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void read(FileChannel channel) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(CAPACITY);

    try {
      int read = channel.read(byteBuffer);
      while (read != -1) {
        System.out.println("GET: " + read);
        // set read mode
        byteBuffer.flip();
        byte[] array = byteBuffer.array();
        System.out.println(new String(array, StandardCharsets.UTF_8));
        byteBuffer.clear();
        // again
        read = channel.read(byteBuffer);
      }
      channel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
