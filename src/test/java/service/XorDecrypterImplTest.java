package service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by tomerd.
 */
public class XorDecrypterImplTest {
    XorDecrypter xorDecrypter;
    List<Integer> encryptedText;
    String key;
    String text;


    @Before
    public void setUp() throws Exception {
        xorDecrypter = new XorDecrypterImpl();

        encryptedText = Arrays.asList(24, 24, 26, 30, 28);
        key = "yz";
        text = "abcde";
    }

    @Test
    public void testSimpleXor() {
        int firstInput = 65;
        int secondInput = 42;
        int result = 107;

        Assert.assertEquals(firstInput ^ secondInput, result);
    }
    @Test
    public void testXorDecrypt() {
        Assert.assertEquals(xorDecrypter.xorDecrypt(encryptedText, key), text);
    }

    @Test
    public void testXorEncrypt() {
        //xor_decrypt(xor_encrypt(text, key), key)== text

        Assert.assertEquals(xorDecrypter.xorDecrypt(xorDecrypter.xorEncrypt(text, key), key), text);
    }

    @Test
    public void testBreakPassword() {

        DecryptResult decryptResult = new DecryptResult(text, key);
        Set<DecryptResult> results = xorDecrypter.guessKey(encryptedText, 2);
        for (DecryptResult result : results) {
            if (result.equals(decryptResult)) {
                Assert.assertTrue(true);
                return;
            }
        }

        Assert.assertTrue(false);
    }

}
