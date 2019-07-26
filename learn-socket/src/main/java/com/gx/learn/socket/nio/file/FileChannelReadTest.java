package com.gx.learn.socket.nio.file;

import com.gx.test.socket.nio.server.ChannelUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 用 channel 向文件读数据
 *
 * @author 郭翔
 */
public class FileChannelReadTest {

  public static void main(String[] args) throws IOException {
    // get channel
    RandomAccessFile accessFile =
        new RandomAccessFile("C:\\Users\\Sam\\Documents\\data\\nio-data.txt", "rw");
    FileChannel fileChannel = accessFile.getChannel();
    ChannelUtils.read(fileChannel);
  }
}
