package service;

/**
 * Created by tomerd.
 */
public interface KeyFactory {

    String getNextKey();
    boolean isOutOfKeys();
}
