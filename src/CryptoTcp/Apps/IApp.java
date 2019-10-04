package CryptoTcp.Apps;

import java.io.IOException;

public interface IApp {
    void SetHost(String host);
    void SetPort(int port);
    void Run() throws IOException;
}
