package org.example.client;

import org.example.server.ConnectionStatus;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private static String request;

    public static void sendRequest(
            String request,
            int clientId,
            ObjectOutputStream outputStream,
            ObjectInputStream inputStream
    ) throws Exception {

        System.out.println(
                "Client "
                        + clientId
                        + " asks for "
                        + request
        );

        outputStream.writeObject(request);
        outputStream.flush();

        try {

            Object response = inputStream.readObject();

            if (!(response instanceof List<?>)) {
                throw new ClassCastException("Expected List!");
            }

            List<Object> objects = (List<Object>) response;

            objects.stream()
                    .forEach(obj -> System.out.println(
                            "Client "
                                    + clientId
                                    + " -> "
                                    + obj
                    ));
        } catch (ClassCastException e) {
            System.out.println(
                    "Client "
                            + clientId
                            + " received wrong object type!"
            );
        }
    }

    public static void main(String[] args) {

        int clientId = new Random().nextInt(1000);

        try (
                Socket socket = new Socket(HOST, PORT);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        ) {

            ConnectionStatus status;

            do {

                status = (ConnectionStatus) inputStream.readObject();

                if (status == ConnectionStatus.WAIT) {

                    System.out.println("Server busy, waiting...");
                }
            } while (status != ConnectionStatus.OK);

            System.out.println("Client " + clientId + " connected");

            outputStream.writeObject(clientId);
            outputStream.flush();

            sendRequest(
                    "animal",
                    clientId,
                    outputStream,
                    inputStream
            );

            outputStream.writeObject("EXIT");
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
