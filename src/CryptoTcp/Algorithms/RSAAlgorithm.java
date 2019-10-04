package CryptoTcp.Algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSAAlgorithm implements IAlgorithm {

    private BufferedReader reader;
    private BufferedWriter writer;

    private BigInteger p;
    private BigInteger q;

    private BigInteger n;

    private BigInteger e;
    private BigInteger d;

    private BigInteger key;

    private static final int INT_WIDTH = 8;

    public RSAAlgorithm () {
        Random random = new Random();

        p = BigInteger.probablePrime(INT_WIDTH, random);

        do {

            q = BigInteger.probablePrime(INT_WIDTH, random);

        } while (q.compareTo(p) == 0);

        n = p.multiply(q);

        BigInteger fi = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));

        do {

            e = BigInteger.probablePrime(INT_WIDTH, random);

        } while (fi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(fi) > 0 && e.compareTo(BigInteger.ONE) > 0);

        d = e.modInverse(fi);
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

        e = new BigInteger(_e);
        n = new BigInteger(_n);

        BigInteger encoded = encode(key);

        writer.write(encoded.toString());
        writer.newLine();
        writer.flush();
    }

    private void logicServer () throws IOException {

        System.out.println(e);
        System.out.println(n);
        System.out.println(d);

        writer.write(e.toString());
        writer.newLine();
        writer.flush();

        writer.write(n.toString());
        writer.newLine();
        writer.flush();

        String message = reader.readLine();
        key = decode(new BigInteger(message));
    }

    private BigInteger encode (BigInteger key) {
        return key.pow(e.intValue()).mod(n);
    }

    private BigInteger decode (BigInteger key) {
        return key.pow(d.intValue()).mod(n);
    }
}
