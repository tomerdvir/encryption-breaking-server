package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DecryptResult;
import service.XorDecrypter;
import service.XorDecrypterImpl;

import java.util.List;
import java.util.Set;

/**
 * Created by tomerd on 20/01/2018.
 */
@RestController
public class DecryptController {

    @RequestMapping("/guessKey")
    public Set<DecryptResult> guessResult(@RequestParam List<Integer> encryptedText, int keySize) {
        XorDecrypter xorDecrypter = new XorDecrypterImpl();
        return xorDecrypter.guessKey(encryptedText, keySize);
    }
}
