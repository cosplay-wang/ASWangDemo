package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import as.founder.demo.wang.R;


public class CollectionSortActivity extends AppCompatActivity {
    List<String> lists = new ArrayList<String>();
    List<A> list = new ArrayList<A>();
    List<B> listB = new ArrayList<B>();
    TextView showResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_sort);
        showResult = (TextView) findViewById(R.id.show_result);
        setDataImp();
        showResult.setText(outPutData(list));
      //  showResult.setText(A.class.getSimpleName());
    }

    public void setDataString() {

        lists.add("5");
        lists.add("2");
        lists.add("9");
        //lists中的对象String 本身含有compareTo方法，所以可以直接调用sort方法，按自然顺序排序，即升序排序
        Collections.sort(lists);
    }
    public void setDataImp() {
        //第二种方法
        A aa = new A();
        aa.setName("aa");
        aa.setOrder(10);
        A bb = new A();
        bb.setName("bb");
        bb.setOrder(87);
        A cc = new A();
        cc.setName("cc");
        cc.setOrder(3);
        list.add(bb);
        list.add(cc);
        list.add(aa);
        //list中的对象A实现Comparable接口
        Collections.sort(list);
    }
    public void setDataNew() {
        //第一种方法：
        B ab = new B();
        ab.setName("ab");
        ab.setOrder("6");
        B ba = new B();
        ba.setName("ba");
        ba.setOrder("2");
        B a = new B();
        a.setName("ba");
        a.setOrder("1");
        B bad = new B();
        bad.setName("ba");
        bad.setOrder("3");
        B baa = new B();
        baa.setName("ba");
        baa.setOrder("4");


        listB.add(ba);
        listB.add(ab);
        listB.add(a);
        listB.add(baa);
        listB.add(bad);
        //根据Collections.sort重载方法来实现
        Collections.sort(listB, new Comparator<B>() {
            @Override
            public int compare(B b1, B b2) {
                return b1.getOrder().compareTo(b2.getOrder());
            }

        });



    }
    public String outPutData(List<A> list){
        String out = "";
        for(int i=0;i<list.size();i++){
            out = out + list.get(i).getOrder()+"\n";

        }
        return  out;
    }

    class A implements Comparable<A> {
        private String name;
        private int order;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        @Override
        public String toString() {
            return "name is " + name + " order is " + order;
        }

        @Override
        public int compareTo(A a) {
            return this.getOrder() - (a.getOrder());
        }

    }

    class B {
        private String name;
        private String order;

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

        @Override
        public String toString() {
            return "name is " + name + " order is " + order;
        }
    }


}
