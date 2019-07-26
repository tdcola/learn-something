package com.gx.learn.socket.bio.server;

import com.gx.test.socket.util.SocketUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多连接 socket 服务
 *
 * @author 郭翔
 */
public class MultiPongServer {

  public static void main(String[] args) {
    MultiPongServer multiPongServer = new MultiPongServer();
    try {
      multiPongServer.run();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void run() throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(100);
    ServerSocket serverSocket = new ServerSocket(12580);
    while (Thread.currentThread().isAlive()) {
      Socket socket = serverSocket.accept();
      executor.submit(new SocketImpl(socket));
    }
  }

  class SocketImpl implements Runnable {

    private Socket socket;

    private BufferedReader reader;

    private BufferedWriter writer;

    SocketImpl(Socket socket) {
      try {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's <code>run</code> method to be called in that
     * separately executing thread.
     *
     * <p>The general contract of the method <code>run</code> is that it may take any action
     * whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
      SocketUtil.pong(socket, writer, reader);
    }
  }
}
