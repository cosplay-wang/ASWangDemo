// AIDLBookManager.aidl
package as.founder.demo.wang.aidl;

// Declare any non-default types here with import statements
import as.founder.demo.wang.aidl.AIDLBook;
interface AIDLBookManager {
   //所有的返回值前都不需要加任何东西，不管是什么数据类型
      List<AIDLBook> getBooks();

      //传参时除了Java基本类型以及String，CharSequence之外的类型
      //都需要在前面加上定向tag，具体加什么量需而定
      void addBook(in AIDLBook book);
}
