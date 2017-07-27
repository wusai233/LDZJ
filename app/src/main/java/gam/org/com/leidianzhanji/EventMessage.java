package gam.org.com.leidianzhanji;

/**
 * Created by lenovo on 2017/7/26.
 */

public class EventMessage {
    /**
     * 擎天柱  10元
     */
    public static final int TAG1 = 10001;
    /**
     * 水晶石   2元 4元 6元
     */
    public static final int TAG2 = 10002;
    /**
     * 一键满级 7元
     */
    public static final int TAG3 = 10003;
    /**
     * 必杀  2元/4个
     */
    public static final int TAG4 = 10004;
    /**
     * 护盾  2元/4个
     */
    public static final int TAG5 = 10005;
    /**
     * 立即复活 1元
     */
    public static final int TAG6 = 10006;
    /**
     * 战斗礼包 4元
     */
    public static final int TAG7 = 10007;

    private int tag;
    private Object content;

    int sj = 0;

    public EventMessage(int tag) {
        this.tag = tag;
    }

    public EventMessage(int tag, Object content) {
        this.tag = tag;
        this.content = content;
    }

    public EventMessage(int tag, int sj) {
        this.tag = tag;
        this.sj = sj;

    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public int getSj() {
        return sj;
    }

    public void setSj(int sj) {
        this.sj = sj;
    }


}
