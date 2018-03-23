package service;

import java.util.List;
import java.util.Set;

/**
 * Created by tomerd.
 */
public interface XorDecrypter {
    String xorDecrypt(List<Integer> encryptedText, String key);
    List<Integer> xorEncrypt(String text, String key);
    Set<DecryptResult> guessKey(List<Integer> encryptedText, int keySize);
}
