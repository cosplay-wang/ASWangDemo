package as.founder.demo.wang.sqlite;

/**
 * Created by zhiwei.wang on 2016/11/8.
 * 先生_不靠谱
 */

public class DBtestClass {
    int id;
    String name;
    String order;

    public DBtestClass() {
    }

    public DBtestClass(int id, String name, String order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
