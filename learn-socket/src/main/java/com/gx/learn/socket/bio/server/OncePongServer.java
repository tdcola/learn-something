package com.gx.learn.socket.bio.server;

import com.gx.test.socket.util.SocketUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 单次连接 socket 服务
 *
 * @author 郭翔
 */
public class OncePongServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(12580);
    Socket socket = serverSocket.accept();
    // 用户输入
    BufferedReader reader =
        new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    // 系统返回
    BufferedWriter writer =
        new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
    // 逻辑
    SocketUtil.pong(socket, writer, reader);
  }
}
