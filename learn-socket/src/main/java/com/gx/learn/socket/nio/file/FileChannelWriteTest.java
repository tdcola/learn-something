package com.gx.learn.socket.nio.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用 channel 向文件写数据
 *
 * @author 郭翔
 */
public class FileChannelWriteTest {

  public static void main(String[] args) throws IOException {
    // get channel
    RandomAccessFile accessFile =
        new RandomAccessFile("C:\\Users\\Sam\\Documents\\data\\nio-data.txt", "rw");
    FileChannel fileChannel = accessFile.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // write into buffer
    String str = "hello world, i write this on " + System.currentTimeMillis();
    buffer.put(str.getBytes());
    buffer.flip();
    // buffer to file
    while (buffer.hasRemaining()) {
      fileChannel.write(buffer);
    }
    fileChannel.close();
  }
}
