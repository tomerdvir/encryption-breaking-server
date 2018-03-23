package service;

/**
 * Created by tomerd on 20/01/2018.
 */
public class KeyFactoryImpl implements KeyFactory {

    public static final char FINAL_CHAR = 'z';
    public static final char STARTING_CHAR = 'a';

    int keySize;
    FactoryState state;
    StringBuilder currKey;

    public KeyFactoryImpl(int keySize) {
        this.keySize = keySize;
        state = FactoryState.INIT;
    }

    @Override
    public String getNextKey() {
        switch (state) {
            case INIT:
                initKey();
                break;
            case READY:
                incrementKey();
                break;
            case DONE:
                throw new RuntimeException("A new key was requested, while service.KeyFactory ran out of keys to generate");
        }

        return currKey.toString();
    }

    private void incrementKey() {

        if (isOutOfKeys()) {
            return;
        }

        for (int i = currKey.length() - 1; i >= 0 ; i--) {
            if (currKey.charAt(i) != FINAL_CHAR) {
                currKey.setCharAt(i , (char) (currKey.charAt(i) + 1));
                return;
            }
            else if (i != 0){
                currKey.setCharAt(i, STARTING_CHAR);
                currKey.setCharAt(i -1, (char) (currKey.charAt(i -1) + 1));
                return;
            }
        }
        /*

        StringBuilder stringBuilder  = new StringBuilder(keySize);

        for (int i = 0; i < keySize; i++) {
            stringBuilder.append(STARTING_CHAR);
        }

        currKey = stringBuilder.toString();

        int currLocation = getCurrentLocation();
        keyBuilder.setCharAt(currLocation, (char) (currKey.charAt(currLocation) + 1));

        currKey = keyBuilder.toString();*/
    }

    private int getCurrentLocation() {
        for (int i = 0; i < currKey.length(); i++) {
            if (currKey.charAt(i) < FINAL_CHAR) {
                return i;
            }
        }

        throw new RuntimeException("Key Factory could not create a new key. Could not increment key");
    }

    private void initKey() {
        currKey = new StringBuilder();

        for (int i = 0; i < keySize; i++) {
            currKey.append(STARTING_CHAR);
        }

        state = FactoryState.READY;
    }

    @Override
    public boolean isOutOfKeys() {
        if (state.equals(FactoryState.INIT)) {
            return false;
        }

        for (int i = 0; i < keySize; i++) {
            if (currKey.charAt(i) != FINAL_CHAR) {
                return false;
            }
        }

        return true;
    }

    enum FactoryState {
        INIT,
        READY,
        DONE
    }
}
