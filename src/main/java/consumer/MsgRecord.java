package consumer;

/**
 * Desc:
 * Author:Kevin
 * Date:2020/2/5
 **/
public class MsgRecord {

    String key;
    String value;
    long offset;

    public MsgRecord(String key, String value, long offset) {
        this.key = key;
        this.value = value;
        this.offset = offset;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "MsgRecord{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", offset=" + offset +
                '}';
    }
}
