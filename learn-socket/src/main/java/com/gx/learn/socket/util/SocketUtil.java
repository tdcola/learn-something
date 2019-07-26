package com.gx.learn.socket.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * socket 工具类
 *
 * @author 郭翔
 */
public class SocketUtil {

  public static Socket getClient(String ip, int port) {
    try {
      return new Socket("localhost", 12580);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /** socket: 接收的消息, 原样返回, 收到 "q" 结束 */
  public static void pong(Socket socket, BufferedWriter writer, BufferedReader reader) {
    try {
      while (Thread.currentThread().isAlive()) {
        String line = reader.readLine();
        System.out.println("ping: " + line);
        // 退出
        if ("q".equals(line) || null == line) {
          socket.close();
          break;
        }
        // pong
        String pong = String.format("pong: %s", line);
        System.out.println(pong);
        writer.write(pong);
        writer.write("\n");
        writer.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
