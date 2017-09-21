package as.founder.demo.wang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import as.founder.demo.wang.R;
import as.founder.demo.wang.custometextview.TypesetTextview;

public class TextActivity extends AppCompatActivity {
TypesetTextview typesetTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        typesetTv = (TypesetTextview) findViewById(R.id.typeset);
        typesetTv.setText(" 电子书\n" +
                "1.产品介绍\n" +
                "　　目前电子书库有220余万册可供阅读的电子图书，2.2亿章节。其中可全文下载的共68.9万，可试读本数183万，支持移动阅读17万（目前正在替换书苑的CEB文件，全部完成后有38万本可适用移动阅读）。外文图书5000多种，民国期刊20000多期，中医古籍2000余册，国学要览80000余册。\n" +
                "线下书包：企鹅英文原版书、Apabi经典套装、经典文学、法典、师联、原创文学第二批、四库全书、北京周报、中医古籍、国学要览、民国期刊、阅读中国、微软教学资源库、国家民委民族问题五种丛书、《钦定古今图书集成》特色资源包。\n" +
                " \n" +
                "2.特色资源介绍：\n" +
                "　　（1）、教参全文数据库\n" +
                "　　方正Apabi教参全文数据库，是方正于2003年5月与CALIS管理中心全面开始合作，针对高校对数字内容的需求，搜集和整理的收录高校经典教材的数据库。\n" +
                "　　（2）、中国中小学学•教精品电子书库\n" +
                "　　 中国中小学学•教精品电子书库是方正Apabi与有关教育专家、中小学校共同研究开发的。它面向中小学教育，结合课程设置，为教师进行备课、评价教学，为学生开展自主学习、提高综合素质，为家长充分参与学生学习提供了强有力的支撑。\n" +
                "\n" +
                " \n" +
                " \n" +
                "　　（3）、企鹅外文电子书库\n" +
                "　　北京方正阿帕比公司是企鹅集团在中国境内制作和发行电子书的唯一合作伙伴。企鹅集团诞生于1935年，是世界上最大的大众图书出版商之一，出版的图书包括文学奖得主的图书等\n" +
                "\n" +
                " \n" +
                "\n" +
                " \n" +
                "　　（4）、《北京周刊—中国英文新闻周刊》50年回溯资源库\n" +
                "\n" +
                "\n" +
                " \n" +
                "　　中央级重点宣传刊物，也是中国唯一的英文新闻周刊\n" +
                "　　浓缩了半个世纪中外重要交往的精华，对于完整了解近50年的中国与世界间的重大变化发挥着无可替代的唯一作用\n" +
                "　　德、法、日、西班牙、英文5个语言版本\n" +
                "　　江泽民同志曾为《北京周刊》题词“中国之窗，世界之友”\n" +
                "　　（5）、《文渊阁四库全书》数据库\n" +
                "　　收录经、史、子、集四部;版本全，校对好，专家审核;显示质量高，保持原版风貌\n" +
                "\n" +
                " \n" +
                " \n" +
                "　　(6)、《国学要览》古籍库\n" +
                "　　收录了包括义理之学、考据之学、辞章之学、经世之学、科学之学在内的，承载着中国传统文明精髓的古籍图书\n" +
                "　　收录古籍数量大、价格低、原版扫描\n" +
                " \n" +
                "3.产品特点：\n" +
                "　　•    版权无忧：由出版社、作者授予信息网络传播权，从源头彻底解决版权问题，购买与使用无后顾之忧。\n" +
                "　　•    种类齐全：覆盖中图法所有二级分类，并在计算机、管理、外语、文学等方面重点建设。\n" +
                "　　•    数量丰富：250万册可供全文阅读的电子图书，400万册可供条目检索的电子图书\n" +
                "　　•    绝对好书：反映图书发展前沿，满足读者阅读需要。方正阿帕比的图书来自：出版社推荐图书、特色图书、高校与科研单位图书馆推荐的著作、相关奖项获奖图书、特聘顾问推荐图书。");
    }
}
