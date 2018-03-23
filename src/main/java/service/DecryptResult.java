package service;

/**
 * Created by tomerd.
 */
public class DecryptResult {
    String text;
    String key;

    public DecryptResult(String text, String key) {
        this.text = text;
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "service.DecryptResult{" +
                "text='" + text + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecryptResult that = (DecryptResult) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }
}
