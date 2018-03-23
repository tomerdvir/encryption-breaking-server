package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tomerd.
 */

/**
 Example:
 xor_decrypt([24, 24, 26, 30, 28], "yz") -> “abcde”
 Explanation:
 ‘y’ = 121 -> 24 XOR 121 = 97 -> 97 = ‘a’ (or in short: 24 XOR ‘y’
 = ‘a’)
 24 XOR ‘z’ = ‘b’
 26 XOR ‘y’ = ‘c’
 30 XOR ‘z’ = ‘d’
 28 XOR ‘y’ = ‘e’
 */

public class XorDecrypterImpl implements XorDecrypter {


    public static final char MIN_CHAR = '!';
    public static final char MAX_CHAR = '~';

    @Override
    public String xorDecrypt(List<Integer> encryptedText, String key) {
        StringBuffer decryptedText = new StringBuffer(encryptedText.size());

        for (int i = 0; i < encryptedText.size(); i++) {
            decryptedText.append((char) (encryptedText.get(i) ^ getKey(key, i)));
        }

        return decryptedText.toString();
    }


    @Override
    public List<Integer> xorEncrypt(String text, String key) {
        List<Integer> encryptedText = new ArrayList<Integer>(text.length());

        for (int i = 0; i < text.length(); i++) {
            encryptedText.add(text.charAt(i) ^ getKey(key, i));
        }

        return encryptedText;
    }

    @Override
    public Set<DecryptResult> guessKey(List<Integer> encryptedText, int keySize) {
        Set<DecryptResult> results = new HashSet<DecryptResult>();

        KeyFactory keyFactory = new KeyFactoryImpl(keySize);

        String currKey;
        String currText;
        DecryptResult currDecryptResult;

        while (!keyFactory.isOutOfKeys()) {
             currKey = keyFactory.getNextKey();
             currText = xorDecrypt(encryptedText, currKey);
             if (!shouldFilterResult(currText)) {
                 currDecryptResult = new DecryptResult(currText, currKey);
                 results.add(currDecryptResult);
             }
        }

        return results;
    }

    private boolean shouldFilterResult(String currText) {
        for (int i = 0; i < currText.length(); i++) {
            if (currText.charAt(i) < MIN_CHAR || currText.charAt(i) > MAX_CHAR) {
                return true;
            }
        }

        return false;
    }

    private char getKey(String key, int i) {
        return key.charAt(i % key.length());
    }
}
