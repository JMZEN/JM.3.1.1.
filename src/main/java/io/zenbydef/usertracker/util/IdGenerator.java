package io.zenbydef.usertracker.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class IdGenerator {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder generateString = new StringBuilder(length);
        IntStream.range(0, length).mapToObj(generate -> getChar()).forEach(generateString::append);
        return new String(generateString);
    }

    private char getChar() {
        return ALPHABET.charAt(nextRandomInt());
    }

    private int nextRandomInt() {
        return RANDOM.nextInt(ALPHABET.length());
    }
}
