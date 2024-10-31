package org.example;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger lengthWord3 =new AtomicInteger(0);
    public static AtomicInteger lengthWord4 = new AtomicInteger(0);
    public static AtomicInteger lengthWord5 = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        new Thread(()-> {
            for (int i = 0; i <100_000 ; i++) {
                if(texts[i].length() == 3 && isPalindrome(texts[i])){
                    lengthWord3.incrementAndGet();
                }
            }
        }).start();
        new Thread(()-> {
            for (int i = 0; i < 100_000; i++) {
                if(texts[i].length() == 4 && isPalindrome(texts[i])){
                    lengthWord4.incrementAndGet();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                if(texts[i].length() ==5 && isPalindrome(texts[i])){
                    lengthWord5.incrementAndGet();
                }
            }
        }).start();
        System.out.println("Красивых слов с длиной 3:" + lengthWord3 + " шт");
        System.out.println("Красивых слов с длиной 4:" + lengthWord4+ " шт");
        System.out.println("Красивых слов с длиной 5:" + lengthWord5 + " шт");
    }
    private static boolean isPalindrome(String text) {
        String reversed = new StringBuilder(text).reverse().toString();
        return text.equals(reversed);
    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}