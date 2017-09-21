package as.founder.demo.wang.bean;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by zhiwei.wang on 2016/6/15.
 */
public class MGsonFormat {


    /**
     * ads : [{"imgsrc":"http://img1.cache.netease.com/3g/2016/6/15/2016061507102296a38.jpg","subtitle":"","tag":"photoset","title":"看客：揭秘\"电子垃圾村\"生存状况","url":"3R710001|2186922"},{"imgsrc":"http://img6.cache.netease.com/3g/2016/6/15/20160615100845747c3.jpg","subtitle":"","tag":"photoset","title":"载有中国游客大巴华盛顿附近出车祸","url":"00AO0001|2187026"},{"imgsrc":"http://img4.cache.netease.com/3g/2016/6/15/20160615092807a3e87.jpg","subtitle":"","tag":"photoset","title":"郑州4名\"黑孩\"为入学接受亲子鉴定","url":"00AP0001|2187024"},{"imgsrc":"http://img5.cache.netease.com/3g/2016/6/15/20160615081522579bd.jpg","subtitle":"","tag":"photoset","title":"深圳楼房坚守16年 开发商放弃拆除","url":"00AP0001|2187018"}]
     * clkNum : 0
     * docid : 9IG74V5H00963VRO_00AP0001|2187028
     * hasAD : 1
     * hasHead : 1
     * img : http://img2.cache.netease.com/3g/2016/6/15/20160615103647e63a8.jpg
     * imgType : 1
     * imgsrc : http://img2.cache.netease.com/3g/2016/6/15/20160615103647e63a8.jpg
     * interest : P
     * lmodify : 2016-06-15 10:29:02
     * photosetID : 00AP0001|2187028
     * picCount : 0
     * program : LTitleA
     * prompt : 成功为您推荐10条新内容
     * ptime : 2016-06-15 10:29:02
     * recSource : 图集
     * recType : 0
     * recprog : base
     * replyCount : 0
     * skipID : 00AP0001|2187028
     * skipType : photoset
     * subtitle :
     * tag : photoset
     * template : normal1
     * title : 广西持续暴雨 罗城城区被淹
     */

    private List<T1348647909107Bean> T1348647909107;

    public List<T1348647909107Bean> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<T1348647909107Bean> T1348647909107) {
        this.T1348647909107 = T1348647909107;
    }

    public static class T1348647909107Bean {
        private int clkNum;
        private String docid;
        private int hasAD;
        private int hasHead;
        private String img;
        private int imgType;
        private String imgsrc;
        private String interest;
        private String lmodify;
        private String photosetID;
        private int picCount;
        private String program;
        private String prompt;
        private String ptime;
        private String recSource;
        private int recType;
        private String recprog;
        private int replyCount;
        private String skipID;
        private String skipType;
        private String subtitle;
        private String tag;
        private String template;
        private String title;
        /**
         * imgsrc : http://img1.cache.netease.com/3g/2016/6/15/2016061507102296a38.jpg
         * subtitle :
         * tag : photoset
         * title : 看客：揭秘"电子垃圾村"生存状况
         * url : 3R710001|2186922
         */

        private List<AdsBean> ads;

        public int getClkNum() {
            return clkNum;
        }

        public void setClkNum(int clkNum) {
            this.clkNum = clkNum;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getImgType() {
            return imgType;
        }

        public void setImgType(int imgType) {
            this.imgType = imgType;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getPhotosetID() {
            return photosetID;
        }

        public void setPhotosetID(String photosetID) {
            this.photosetID = photosetID;
        }

        public int getPicCount() {
            return picCount;
        }

        public void setPicCount(int picCount) {
            this.picCount = picCount;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getRecSource() {
            return recSource;
        }

        public void setRecSource(String recSource) {
            this.recSource = recSource;
        }

        public int getRecType() {
            return recType;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }

        public String getRecprog() {
            return recprog;
        }

        public void setRecprog(String recprog) {
            this.recprog = recprog;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getSkipID() {
            return skipID;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public String getSkipType() {
            return skipType;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public static class AdsBean {
            private String imgsrc;
            private String subtitle;
            private String tag;
            private String title;
            private String url;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
