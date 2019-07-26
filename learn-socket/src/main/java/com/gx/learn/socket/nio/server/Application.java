package com.gx.learn.socket.nio.server;

import java.io.IOException;

/**
 * 测试启动器
 *
 * @author 郭翔
 */
public class Application {

  public static void main(String[] args) throws IOException {
    new ServerSocketReactor(12580).run();
  }
}
