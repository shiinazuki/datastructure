package com.iori.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>TinyURL 的加密与解密</h3>
 */
public class TinyURLLeetcode535 {

    public static void main(String[] args) {
        CodecSequence codec = new CodecSequence();
        String encoded = codec.encode("https://leetcode.cn/problems/1");
        System.out.println(encoded);

        encoded = codec.encode("https://leetcode.cn/problems/2");
        System.out.println(encoded);


        //for (int i = 0; i <= 64; i++) {
        //    System.out.println(i + "\t" + toBase64(i));
        //}

        System.out.println(toBase64(137849728));
    }

    /*
           要让【长】【短】网址一一对应

               1. 用【随机数】作为短网址标识
               2. 用【hash码】作为短网址标识
               3. 用【递增数】作为短网址标识
                   1) 多线程下可以使用吗？
                   2) 分布式下可以使用吗？
                   3) 4e9iAk 是怎么生成的？

                   a-z 0-9 A-Z  62进制的数字

           0   1   2   3   4   5   6   7   8   9   a   b   c   d   e   f

           十进制 => 十六进制
           31       1f

           31 % 16 = 15
           31 / 16 = 1

           1 % 16 = 1
           1 / 16 = 0


           长网址： https://leetcode.cn/problems/encode-and-decode-tinyurl/description/
           对应的短网址： http://tinyurl.com/4e9iAk
        */

    private static final char[] toBase64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static String toBase64(int number) {
        if (number == 0) {
            return String.valueOf(toBase64[0]);
        }
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int r = number % 64;
            sb.append(toBase64[r]);
            number = number / 64;
        }
        return sb.toString();
    }


    //使用随机数与hash码
    static class Codec {

        private Map<String, String> longToShort = new HashMap<>();
        private Map<String, String> shortToLong = new HashMap<>();

        private static final String SHORT_PREFIX = "http://tinyurl.com/";

        public String encode(String longUrl) {
            String shortUrl = longToShort.get(longUrl);
            if (shortUrl != null) {
                return shortUrl;
            }
            //生成短网址
            //使用hashCode吗来作为标识
            int id = longUrl.hashCode();
            while (true) {
                //使用随机数作为短网址的标识
                //int id = ThreadLocalRandom.current().nextInt(); //int
                shortUrl = SHORT_PREFIX + id;
                if (!shortToLong.containsKey(shortUrl)) {
                    longToShort.put(longUrl, shortUrl);
                    shortToLong.put(shortUrl, longUrl);
                    break;
                }
                id++;
            }
            return shortUrl;
        }

        public String decode(String shortUrl) {
            return shortToLong.get(shortUrl);
        }

    }

    //使用递增数字
    static class CodecSequence {

        private Map<String, String> longToShort = new HashMap<>();
        private Map<String, String> shortToLong = new HashMap<>();

        private static final String SHORT_PREFIX = "http://tinyurl.com/";
        private static int id =1;

        public String encode(String longUrl) {
            String shortUrl = longToShort.get(longUrl);
            if (shortUrl != null) {
                return shortUrl;
            }
            //生成短网址
            shortUrl = SHORT_PREFIX + toBase64(id);
            longToShort.put(longUrl, shortUrl);
            shortToLong.put(shortUrl, longUrl);
            id++;
            return shortUrl;
        }

        public String decode(String shortUrl) {
            return shortToLong.get(shortUrl);
        }

    }


}
