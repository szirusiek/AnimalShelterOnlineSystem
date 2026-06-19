package org.example.server;

import org.example.repository.DataRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final DataRepository repo;
    private final AtomicInteger activeClients;
    private static Semaphore clientSlots;


    public ClientHandler(
            Socket socket,
            DataRepository repo,
            AtomicInteger activeClients,
            Semaphore clientSlots) {

        this.socket = socket;
        this.repo = repo;
        this.activeClients = activeClients;
        ClientHandler.clientSlots = clientSlots;
    }

    @Override
    public void run() {

        try {

            handleClient();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (activeClients.get() > 0) {

                activeClients.decrementAndGet();

                clientSlots.release();

            }

            try {

                socket.close();

            } catch (IOException ignored) {}
        }
    }

    private void handleClient() throws Exception {

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        if (clientSlots.availablePermits() == 0) {

            outputStream.writeObject(ConnectionStatus.WAIT);
            outputStream.flush();

            System.out.println("Client waiting...");
        }

        clientSlots.acquire();

        activeClients.incrementAndGet();

        outputStream.writeObject(ConnectionStatus.OK);
        outputStream.flush();

        int clientId = (Integer) inputStream.readObject();

        System.out.println("Client accepted: " + clientId);


        Random random = new Random();

        while (true) {

            String request = (String) inputStream.readObject();

            if (request.equalsIgnoreCase("EXIT")) {
                break;
            }

            Thread.sleep(random.nextInt(3000));

            sendObjects(
                    request,
                    clientId,
                    outputStream
            );
        }
    }

    private void sendObjects(
            String request,
            int clientId,
            ObjectOutputStream outputStream)
        throws IOException {

        List<Object> objects = repo.getObjects(request.toLowerCase());

        if (objects.isEmpty()) {

            outputStream.writeObject(new RuntimeException("Wrong type!"));

            outputStream.flush();

            System.out.println("Sent wrong object to " + clientId);

            return;
        }

        outputStream.writeObject(objects);

        outputStream.flush();

        System.out.println("Sent " + objects.size() + " objects to " + clientId);
    }
}
