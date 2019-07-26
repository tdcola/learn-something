package com.gx.learn.socket.bio.client;

import com.gx.learn.socket.util.SocketUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * socket client
 *
 * @author 郭翔
 */
public class OnceClient {

  public static void main(String[] args) {
    try {
      Socket localhost = SocketUtil.getClient("localhost", 12580);
      BufferedWriter bufferedWriter =
          new BufferedWriter(new OutputStreamWriter(localhost.getOutputStream()));
      // 写数据
      bufferedWriter.write("hello world 1");
      bufferedWriter.newLine();
      bufferedWriter.flush();
      bufferedWriter.write("hello world 2");
      bufferedWriter.newLine();
      bufferedWriter.flush();
      // done
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
