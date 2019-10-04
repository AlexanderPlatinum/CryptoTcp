package CryptoTcp.Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.math.BigInteger;

public interface IAlgorithm {
    void SetReader(BufferedReader reader);
    void SetWriter(BufferedWriter writer);

    void Run(boolean isClient);

    BigInteger GetKey();
}
