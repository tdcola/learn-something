package com.gx.learn.socket.bio.client;

import com.gx.learn.socket.util.SocketUtil;

import java.io.*;
import java.net.Socket;

/**
 * 系统输入 socket client
 *
 * @author 郭翔
 */
public class SystemInClient {

  public static void main(String[] args) throws IOException {
    Socket client = SocketUtil.getClient("localhost", 12580);
    // server
    BufferedWriter bufferedWriter =
        new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    BufferedReader respond =
        new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
    // user input
    BufferedReader request = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

    while (true) {
      System.out.println("=====");
      // 用户输入, 写到 socket writer
      String line = request.readLine();
      bufferedWriter.write(line);
      bufferedWriter.newLine();
      bufferedWriter.flush();
      if ("q".equals(line)) {
        client.close();
        break;
      }
      // read
      System.out.println("GET: " + respond.readLine());
    }
  }
}
