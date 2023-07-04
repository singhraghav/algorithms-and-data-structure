package com.singraghav.fastio;

public class FastIODemo {

    public static void main(String[] args) {
        FastConsoleInputReader reader = new FastConsoleInputReader();

        int a = reader.nextInt();
        System.out.println(a);
        int b = reader.nextInt();
        System.out.println(b);

        String c = reader.nextString();
        System.out.println(c);

        String d = reader.nextString();
        System.out.println(d);

        double e = reader.nextDouble();
        System.out.println(e);

    }
}
