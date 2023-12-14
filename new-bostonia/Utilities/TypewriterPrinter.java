package Utilities;

import java.util.concurrent.TimeUnit;
// typewriter effect
public class TypewriterPrinter {
    
    public static void typeWithTypewriterEffect(String text) {
        long delayMillis = 10; 
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                TimeUnit.MILLISECONDS.sleep(delayMillis);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}