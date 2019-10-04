package CryptoTcp.Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSAAlgorithm implements IAlgorithm {

    private BufferedReader reader;
    private BufferedWriter writer;

    private int p;
    private int q;

    private int n;

    private int e;
    private int d;

    private BigInteger key;

    public RSAAlgorithm () {
        Random random = new Random();

        p = random.nextInt(100) + 1;
        q = random.nextInt(100) + 1;

        e = random.nextInt(100) + 1;
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
        return this.key;
    }

    @Override
    public void SetKey(BigInteger key) {
        this.key = key;
    }

    private void logicClient () throws IOException {
        String _e = reader.readLine();
        String _n = reader.readLine();

        e = Integer.parseInt(_e);
        n = Integer.parseInt(_n);

        int msg = encode(key.intValue());
        writer.write(Integer.toString(msg));
        writer.newLine();
        writer.flush();
    }

    private void logicServer () throws IOException {
        n = calcN();
        d = calcD();

        System.out.println(e);
        System.out.println(n);
        System.out.println(d);

        writer.write(Integer.toString(e));
        writer.newLine();
        writer.flush();

        writer.write(Integer.toString(n));
        writer.newLine();
        writer.flush();

        String message = reader.readLine();
        int decoded = decode(Integer.parseInt(message));

        key = BigInteger.valueOf(decoded);
    }

    private int calcN () {
        return p * q;
    }

    private int calcD () {
        int fi = (p - 1) * (q - 1);
        return 0; // todo: need calc
    }

    private int encode (int key) {
        return (int)Math.pow(key, e) % n;
    }

    private int decode (int key) {
        return (int)Math.pow(key, d) % n;
    }

}
