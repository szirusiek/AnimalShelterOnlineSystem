package org.example.tests;

import org.example.repository.DataRepository;
import org.example.server.ClientHandler;
import org.example.server.ConnectionStatus;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientHandlerIntegrationtest {

    @Test
    void acceptClientTest() throws Exception {

        ServerSocket serverSocket = new ServerSocket(5000);

        Thread serverThread = new Thread(() -> {

            try {

                Socket socket = serverSocket.accept();

                ClientHandler handler = new ClientHandler(socket, new DataRepository(), new AtomicInteger(), new Semaphore(4, true));

                handler.run();
            } catch (Exception e) {

                e.printStackTrace();
            }
        });

        serverThread.start();

        Socket client = new Socket("localhost", 5000);

        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

        ConnectionStatus status = (ConnectionStatus) in.readObject();

        assertEquals(ConnectionStatus.OK, status);

        client.close();
        serverSocket.close();
    }
}
