package org.example.tests;

import org.example.server.ConnectionStatus;
import org.example.server.Server;
import org.junit.jupiter.api.*;

import java.io.ObjectInputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerE2ETest {

    static Thread serverThread;

    @BeforeAll
    static void startServer() {

        serverThread =
                new Thread(() -> {

                    try{
                        Server.main(
                                new String[]{}
                        );
                    }
                    catch(Exception e){

                        e.printStackTrace();
                    }

                });

        serverThread.start();

        try {

            Thread.sleep(1000);

        } catch (Exception ignored) {}
    }

    @Test
    void shouldConnectClientToServer() throws Exception {

        Socket client = new Socket("localhost",5000);

        ObjectInputStream input = new ObjectInputStream(client.getInputStream());

        ConnectionStatus status = (ConnectionStatus) input.readObject();

        assertEquals(ConnectionStatus.OK, status);

        client.close();
    }

}