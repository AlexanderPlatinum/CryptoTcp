package CryptoTcp.Apps;

import java.io.*;
import java.net.Socket;

public class ClientApp implements IApp {

    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;

    private String host;
    private int port;

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
        socket = new Socket(host, port);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        writer.write("I am client!\n");
        writer.flush();

        String status = reader.readLine();
        System.out.println(status);
    }

    private void closeAll() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
