package as.founder.demo.wang.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhiwei.wang on 2018/3/19.
 * wechat 760560322
 * 作用：
 */

public class AIDLBook implements Parcelable{
    String name;
    int id;
    String page;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public static final Creator<AIDLBook> CREATOR = new Creator<AIDLBook>()
    {
        public AIDLBook createFromParcel(Parcel in)
        {
            AIDLBook book = new AIDLBook();
            book.setId(in.readInt());
            book.setName(in.readString());
            return book;
           // return new AIDLBook(in);
        }

        public AIDLBook[] newArray(int size)
        {
            return new AIDLBook[size];
        }
    };

    public AIDLBook() {
    }

    public AIDLBook(String name, int id, String page) {
        this.name = name;
        this.id = id;
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }
    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = dest.readString();
        id = dest.readInt();
    }
    //方便打印数据
    @Override
    public String toString() {
        return "name : " + name + " , price : " + id;
    }
}
