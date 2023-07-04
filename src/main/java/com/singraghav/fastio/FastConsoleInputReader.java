package com.singraghav.fastio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastConsoleInputReader {

    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastConsoleInputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private String next() {
        if(tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public float nextFloat() {
        return Float.parseFloat(next());
    }

    public String nextString() {
        String result = null;
        if(tokenizer.hasMoreTokens())
            result = tokenizer.nextToken("\n");
        else
            try {
                result = reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        return result.trim();
    }
}