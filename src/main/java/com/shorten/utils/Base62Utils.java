package com.shorten.utils;

public class Base62Utils {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private Base62Utils() {
    }

    public static String idToShortKey(long id) {
        StringBuilder stringBuilder = new StringBuilder();
        while (id > 0) {
            stringBuilder.append(BASE62.charAt((int) (id % 62)));
            id = id / 62;
        }

        while (stringBuilder.length() < 6) {
            stringBuilder.append(0);
        }

        return stringBuilder.reverse().toString();
    }

    public static long shortKeyToId(String shortKey) {
        long id = 0;
        for (int i = 0; i < shortKey.length(); i++) {
            id = id * 62 + BASE62.indexOf(shortKey.charAt(i));
        }

        return id;
    }

    public static void main(String[] args) {
        System.out.println(idToShortKey(1));
        System.out.println(idToShortKey(100000));
        System.out.println(idToShortKey(9999999999999L));

        System.out.println(shortKeyToId("000001"));
        System.out.println(shortKeyToId("000Q0u"));
        System.out.println(shortKeyToId("2q3Rktod"));
    }
}
