package CryptoTcp;

import CryptoTcp.Algorithms.AlgorithmType;
import CryptoTcp.Algorithms.DHAlgorithm;
import CryptoTcp.Algorithms.IAlgorithm;
import CryptoTcp.Algorithms.RSAAlgorithm;
import CryptoTcp.Apps.AppType;
import CryptoTcp.Apps.ClientApp;
import CryptoTcp.Apps.IApp;
import CryptoTcp.Apps.ServerApp;

import java.io.IOException;

public class Application {

    private static IApp getApp(AppType type) {
        switch (type) {
            case CLIENT:
                return new ClientApp();
            case SERVER:
                return new ServerApp();
            default:
                return null;
        }
    }

    private static IAlgorithm getAlgorithm(AlgorithmType type) {
        switch (type) {
            case RSA:
                return new RSAAlgorithm();

            case DH:
                return new DHAlgorithm();

            default:
                return null;
        }
    }

    public static void main (String [] args) {
        IApp app = getApp(AppType.CLIENT);
        IAlgorithm algorithm = getAlgorithm(AlgorithmType.DH);

        app.SetAlgorithm(algorithm);

        app.SetHost("localhost");
        app.SetPort(7777);

        try {
            app.Run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
