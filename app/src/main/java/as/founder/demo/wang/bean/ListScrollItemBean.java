package as.founder.demo.wang.bean;

/**
 * Created by zhiwei.wang on 2016/8/17.
 * 先生_不靠谱
 */
public class ListScrollItemBean {
    String name;
    boolean isScroll;

    public ListScrollItemBean(String name, boolean isScroll) {
        this.name = name;
        this.isScroll = isScroll;
    }

    public ListScrollItemBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isScroll() {
        return isScroll;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
}
