package CryptoTcp.Apps;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp implements IApp {

    private Socket socket;
    private ServerSocket server;

    private String host;
    private int port;

    private BufferedReader reader;
    private BufferedWriter writer;

    @Override
    public void SetHost(String host) {
        this.host = host;
    }

    @Override
    public void SetPort(int port) {
        this.port = port;
    }

    @Override
    public void Run() throws IOException {
        server = new ServerSocket(port);

        System.out.printf("Server run on %s:%d \n", host, port);

        socket = server.accept();

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String word = reader.readLine();
        System.out.println(word);

        writer.write("OK!");
        writer.flush();

        closeAll();
    }

    private void closeAll() throws IOException {
        socket.close();
        reader.close();
        writer.close();
        server.close();
    }
}
