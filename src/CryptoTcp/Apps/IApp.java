package CryptoTcp.Apps;

import CryptoTcp.Algorithms.AlgorithmType;
import CryptoTcp.Algorithms.IAlgorithm;

import java.io.IOException;

public interface IApp {
    void SetHost(String host);
    void SetPort(int port);
    void SetAlgorithm(IAlgorithm algorithm);

    void Run() throws IOException;
}
