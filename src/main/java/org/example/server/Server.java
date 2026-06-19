package org.example.server;

import org.example.repository.DataRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;

public class Server {

    // Set the server port as a const
    private static final int PORT = 5000;

    // Set the maximum number of clients the server can handle
    private static final int MAX_CLIENTS = 2;

    private static final Semaphore clientSlots = new Semaphore(MAX_CLIENTS, true);

    private static final AtomicInteger activeClients = new AtomicInteger();

    private static final DataRepository repo = new DataRepository();

    public static void main(String[] args) throws IOException {

        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(PORT);

        // Debug console string - server has started
        System.out.println("Server started on port " + PORT);

        while (true) {

            Socket socket = serverSocket.accept();

            System.out.println("New connection: " + socket);

            executor.submit(new ClientHandler(
                    socket,
                    repo,
                    activeClients,
                    clientSlots));
        }
    }
}
