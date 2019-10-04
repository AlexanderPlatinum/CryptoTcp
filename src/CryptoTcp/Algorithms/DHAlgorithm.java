package CryptoTcp.Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class DHAlgorithm implements IAlgorithm {

    private BufferedReader reader;
    private BufferedWriter writer;

    private int a;
    private int b;
    private BigInteger g;
    private BigInteger p;

    private BigInteger A;
    private BigInteger B;

    private BigInteger key;

    public DHAlgorithm () {
        Random random = new Random();

        a = random.nextInt(100);
        b = random.nextInt(100);

        g = BigInteger.valueOf(random.nextInt(100));
        p = BigInteger.valueOf(random.nextInt(100));
    }

    @Override
    public void SetReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void SetWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void Run(boolean isClient) throws IOException {
        if (isClient) {
            logicClient();
        } else {
            logicServer();
        }
    }

    @Override
    public BigInteger GetKey() {
        return key;
    }

    private void logicClient() throws IOException {
        String _g = reader.readLine();
        String _p = reader.readLine();
        String _A = reader.readLine();

        g = new BigInteger(_g);
        p = new BigInteger(_p);
        A = new BigInteger(_A);

        B = calcBClient();
        key = calcKClient();

        writer.write(B.toString());
        writer.newLine();
        writer.flush();
    }

    private void logicServer() throws IOException {
        A = calcAServer();

        writer.write(g.toString());
        writer.newLine();
        writer.flush();

        writer.write(p.toString());
        writer.newLine();
        writer.flush();

        writer.write(A.toString());
        writer.newLine();
        writer.flush();

        String _B = reader.readLine();
        B = new BigInteger(_B);

        key = calcKServer();
    }

    private BigInteger calcAServer() {
        return g.pow(a).mod(p);
    }

    private BigInteger calcBClient() {
        return g.pow(b).mod(p);
    }

    private BigInteger calcKServer() {
        return B.pow(a).mod(p);
    }

    private BigInteger calcKClient() {
        return A.pow(b).mod(p);
    }
}
