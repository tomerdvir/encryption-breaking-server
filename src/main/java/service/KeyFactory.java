package service;

/**
 * Created by tomerd on 20/01/2018.
 */
public interface KeyFactory {

    String getNextKey();
    boolean isOutOfKeys();
}
