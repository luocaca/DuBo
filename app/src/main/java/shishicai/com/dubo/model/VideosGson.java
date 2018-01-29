package shishicai.com.dubo.model;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class VideosGson {
//    http://m.zhcw.com/clienth5.do?lottery=FC_SSQ&pageSize=20&pageNo=6&transactionType=300301&src=0000100001%7C6000003060

    /**
     * url : http://m.zhcw.com/kaijiang/kj_live.jsp?lottery=FC_SSQ&from=client
     * textTitle : 开奖直播时间：周二、四、日 21:15,直播通道>>
     * lottery : FC_SSQ
     * pageNo : 6
     * pageSize : 20
     * totalPage : 111
     * dataList : [{"kjIssue":"2017066","kjdate":"2017/06/08","kjznum":"01 04 06 17 19 26","kjtnum":"03","mlist":[{"mname":"本期销量：329,821,920元"},{"mname":"奖池累计：624,968,496元"}],"bonus":[{"zname":"一等奖","znum":"8","money":"7075823"},{"zname":"二等奖","znum":"103","money":"201536"},{"zname":"三等奖","znum":"1364","money":"3000"},{"zname":"四等奖","znum":"74828","money":"200"},{"zname":"五等奖","znum":"1364595","money":"10"},{"zname":"六等奖","znum":"9175247","money":"5"}]},{"kjIssue":"2017065","kjdate":"2017/06/06","kjznum":"02 05 08 10 12 21","kjtnum":"07","mlist":[{"mname":"本期销量：320,800,086元"},{"mname":"奖池累计：619,300,374元"}],"bonus":[{"zname":"一等奖","znum":"14","money":"5907122"},{"zname":"二等奖","znum":"181","money":"87705"},{"zname":"三等奖","znum":"1985","money":"3000"},{"zname":"四等奖","znum":"88342","money":"200"},{"zname":"五等奖","znum":"1457827","money":"10"},{"zname":"六等奖","znum":"11098363","money":"5"}]},{"kjIssue":"2017064","kjdate":"2017/06/04","kjznum":"02 10 16 22 24 28","kjtnum":"15","mlist":[{"mname":"本期销量：351,655,222元"},{"mname":"奖池累计：654,376,180元"}],"bonus":[{"zname":"一等奖","znum":"5","money":"9001480"},{"zname":"二等奖","znum":"158","money":"158286"},{"zname":"三等奖","znum":"1194","money":"3000"},{"zname":"四等奖","znum":"63332","money":"200"},{"zname":"五等奖","znum":"1220261","money":"10"},{"zname":"六等奖","znum":"8764605","money":"5"}]},{"kjIssue":"2017063","kjdate":"2017/06/01","kjznum":"12 16 20 22 25 31","kjtnum":"04","mlist":[{"mname":"本期销量：307,572,084元"},{"mname":"奖池累计：624,355,820元"}],"bonus":[{"zname":"一等奖","znum":"4","money":"10000000"},{"zname":"二等奖","znum":"246","money":"102608"},{"zname":"三等奖","znum":"715","money":"3000"},{"zname":"四等奖","znum":"38031","money":"200"},{"zname":"五等奖","znum":"818953","money":"10"},{"zname":"六等奖","znum":"6360637","money":"5"}]},{"kjIssue":"2017062","kjdate":"2017/05/30","kjznum":"01 07 22 24 26 31","kjtnum":"10","mlist":[{"mname":"本期销量：292,747,704元"},{"mname":"奖池累计：588,631,018元"}],"bonus":[{"zname":"一等奖","znum":"2","money":"10000000"},{"zname":"二等奖","znum":"50","money":"413968"},{"zname":"三等奖","znum":"688","money":"3000"},{"zname":"四等奖","znum":"37912","money":"200"},{"zname":"五等奖","znum":"779016","money":"10"},{"zname":"六等奖","znum":"8643212","money":"5"}]},{"kjIssue":"2017061","kjdate":"2017/05/28","kjznum":"06 07 12 20 26 27","kjtnum":"11","mlist":[{"mname":"本期销量：327,891,234元"},{"mname":"奖池累计：546,535,704元"}],"bonus":[{"zname":"一等奖","znum":"22","money":"5719289"},{"zname":"二等奖","znum":"236","money":"83815"},{"zname":"三等奖","znum":"1818","money":"3000"},{"zname":"四等奖","znum":"97177","money":"200"},{"zname":"五等奖","znum":"1749641","money":"10"},{"zname":"六等奖","znum":"7831806","money":"5"}]},{"kjIssue":"2017060","kjdate":"2017/05/25","kjznum":"05 10 13 24 26 31","kjtnum":"04","mlist":[{"mname":"本期销量：320,553,910元"},{"mname":"奖池累计：613,018,696元"}],"bonus":[{"zname":"一等奖","znum":"4","money":"9226054"},{"zname":"二等奖","znum":"113","money":"186993"},{"zname":"三等奖","znum":"1178","money":"3000"},{"zname":"四等奖","znum":"64781","money":"200"},{"zname":"五等奖","znum":"1188625","money":"10"},{"zname":"六等奖","znum":"8834776","money":"5"}]},{"kjIssue":"2017059","kjdate":"2017/05/23","kjznum":"04 08 09 15 19 25","kjtnum":"09","mlist":[{"mname":"本期销量：306,145,516元"},{"mname":"奖池累计：586,532,102元"}],"bonus":[{"zname":"一等奖","znum":"26","money":"5191242"},{"zname":"二等奖","znum":"222","money":"27997"},{"zname":"三等奖","znum":"3608","money":"3000"},{"zname":"四等奖","znum":"132859","money":"200"},{"zname":"五等奖","znum":"1854184","money":"10"},{"zname":"六等奖","znum":"13842428","money":"5"}]},{"kjIssue":"2017058","kjdate":"2017/05/21","kjznum":"01 09 13 22 28 32","kjtnum":"11","mlist":[{"mname":"本期销量：338,794,650元"},{"mname":"奖池累计：702,858,272元"}],"bonus":[{"zname":"一等奖","znum":"8","money":"7035961"},{"zname":"二等奖","znum":"111","money":"183419"},{"zname":"三等奖","znum":"1428","money":"3000"},{"zname":"四等奖","znum":"67171","money":"200"},{"zname":"五等奖","znum":"1172549","money":"10"},{"zname":"六等奖","znum":"11025443","money":"5"}]},{"kjIssue":"2017057","kjdate":"2017/05/18","kjznum":"18 20 22 23 30 31","kjtnum":"16","mlist":[{"mname":"本期销量：314,697,362元"},{"mname":"奖池累计：698,067,117元"}],"bonus":[{"zname":"一等奖","znum":"9","money":"6821373"},{"zname":"二等奖","znum":"71","money":"288597"},{"zname":"三等奖","znum":"881","money":"3000"},{"zname":"四等奖","znum":"53312","money":"200"},{"zname":"五等奖","znum":"1052315","money":"10"},{"zname":"六等奖","znum":"9682267","money":"5"}]},{"kjIssue":"2017056","kjdate":"2017/05/16","kjznum":"13 14 18 19 21 28","kjtnum":"06","mlist":[{"mname":"本期销量：317,424,132元"},{"mname":"奖池累计：697,988,120元"}],"bonus":[{"zname":"一等奖","znum":"8","money":"7367832"},{"zname":"二等奖","znum":"184","money":"128686"},{"zname":"三等奖","znum":"900","money":"3000"},{"zname":"四等奖","znum":"49707","money":"200"},{"zname":"五等奖","znum":"973032","money":"10"},{"zname":"六等奖","znum":"7690564","money":"5"}]},{"kjIssue":"2017055","kjdate":"2017/05/14","kjznum":"07 12 13 20 24 31","kjtnum":"05","mlist":[{"mname":"本期销量：342,200,872元"},{"mname":"奖池累计：685,895,820元"}],"bonus":[{"zname":"一等奖","znum":"20","money":"5718169"},{"zname":"二等奖","znum":"109","money":"164717"},{"zname":"三等奖","znum":"1901","money":"3000"},{"zname":"四等奖","znum":"73900","money":"200"},{"zname":"五等奖","znum":"1276271","money":"10"},{"zname":"六等奖","znum":"12523162","money":"5"}]},{"kjIssue":"2017054","kjdate":"2017/05/11","kjznum":"02 03 09 23 28 33","kjtnum":"08","mlist":[{"mname":"本期销量：315,730,948元"},{"mname":"奖池累计：746,396,522元"}],"bonus":[{"zname":"一等奖","znum":"2","money":"10000000"},{"zname":"二等奖","znum":"73","money":"227292"},{"zname":"三等奖","znum":"1391","money":"3000"},{"zname":"四等奖","znum":"72619","money":"200"},{"zname":"五等奖","znum":"1296663","money":"10"},{"zname":"六等奖","znum":"11335094","money":"5"}]},{"kjIssue":"2017053","kjdate":"2017/05/09","kjznum":"04 09 11 15 29 31","kjtnum":"06","mlist":[{"mname":"本期销量：316,559,816元"},{"mname":"奖池累计：716,619,576元"}],"bonus":[{"zname":"一等奖","znum":"6","money":"7534369"},{"zname":"二等奖","znum":"77","money":"246854"},{"zname":"三等奖","znum":"1207","money":"3000"},{"zname":"四等奖","znum":"63200","money":"200"},{"zname":"五等奖","znum":"1162556","money":"10"},{"zname":"六等奖","znum":"10239332","money":"5"}]},{"kjIssue":"2017052","kjdate":"2017/05/07","kjznum":"07 08 18 24 29 31","kjtnum":"07","mlist":[{"mname":"本期销量：346,847,224元"},{"mname":"奖池累计：704,802,480元"}],"bonus":[{"zname":"一等奖","znum":"5","money":"8409592"},{"zname":"二等奖","znum":"182","money":"117087"},{"zname":"三等奖","znum":"1613","money":"3000"},{"zname":"四等奖","znum":"80324","money":"200"},{"zname":"五等奖","znum":"1458362","money":"10"},{"zname":"六等奖","znum":"9845579","money":"5"}]},{"kjIssue":"2017051","kjdate":"2017/05/04","kjznum":"02 05 09 15 24 25","kjtnum":"11","mlist":[{"mname":"本期销量：315,667,040元"},{"mname":"奖池累计：682,920,580元"}],"bonus":[{"zname":"一等奖","znum":"10","money":"6175841"},{"zname":"二等奖","znum":"194","money":"75762"},{"zname":"三等奖","znum":"1842","money":"3000"},{"zname":"四等奖","znum":"87633","money":"200"},{"zname":"五等奖","znum":"1456013","money":"10"},{"zname":"六等奖","znum":"11654411","money":"5"}]},{"kjIssue":"2017050","kjdate":"2017/05/02","kjznum":"10 12 20 24 27 29","kjtnum":"07","mlist":[{"mname":"本期销量：308,163,776元"},{"mname":"奖池累计：700,584,945元"}],"bonus":[{"zname":"一等奖","znum":"5","money":"8309640"},{"zname":"二等奖","znum":"64","money":"323207"},{"zname":"三等奖","znum":"1036","money":"3000"},{"zname":"四等奖","znum":"55426","money":"200"},{"zname":"五等奖","znum":"1082731","money":"10"},{"zname":"六等奖","znum":"8647748","money":"5"}]},{"kjIssue":"2017049","kjdate":"2017/04/30","kjznum":"01 08 14 15 20 29","kjtnum":"10","mlist":[{"mname":"本期销量：326,777,700元"},{"mname":"奖池累计：680,077,398元"}],"bonus":[{"zname":"一等奖","znum":"7","money":"7636512"},{"zname":"二等奖","znum":"113","money":"204154"},{"zname":"三等奖","znum":"1317","money":"3000"},{"zname":"四等奖","znum":"64218","money":"200"},{"zname":"五等奖","znum":"1200540","money":"10"},{"zname":"六等奖","znum":"7808625","money":"5"}]},{"kjIssue":"2017048","kjdate":"2017/04/27","kjznum":"05 08 09 14 15 19","kjtnum":"07","mlist":[{"mname":"本期销量：317,465,116元"},{"mname":"奖池累计：664,324,529元"}],"bonus":[{"zname":"一等奖","znum":"11","money":"5831308"},{"zname":"二等奖","znum":"107","money":"106827"},{"zname":"三等奖","znum":"2232","money":"3000"},{"zname":"四等奖","znum":"97941","money":"200"},{"zname":"五等奖","znum":"1602997","money":"10"},{"zname":"六等奖","znum":"13504350","money":"5"}]},{"kjIssue":"2017047","kjdate":"2017/04/25","kjznum":"02 05 08 10 32 33","kjtnum":"02","mlist":[{"mname":"本期销量：313,845,556元"},{"mname":"奖池累计：694,177,440元"}],"bonus":[{"zname":"一等奖","znum":"6","money":"7801881"},{"zname":"二等奖","znum":"181","money":"116100"},{"zname":"三等奖","znum":"1654","money":"3000"},{"zname":"四等奖","znum":"63304","money":"200"},{"zname":"五等奖","znum":"1081732","money":"10"},{"zname":"六等奖","znum":"8257553","money":"5"}]}]
     */

    public String url;
    public String textTitle;
    public String lottery;
    public String pageNo;
    public String pageSize;
    public String totalPage;
    public List<DataListbean> dataList;

    public static class DataListbean {
        /**
         * kjIssue : 2017066
         * kjdate : 2017/06/08
         * kjznum : 01 04 06 17 19 26
         * kjtnum : 03
         * mlist : [{"mname":"本期销量：329,821,920元"},{"mname":"奖池累计：624,968,496元"}]
         * bonus : [{"zname":"一等奖","znum":"8","money":"7075823"},
         * {"zname":"二等奖","znum":"103","money":"201536"},
         * {"zname":"三等奖","znum":"1364","money":"3000"},
         * {"zname":"四等奖","znum":"74828","money":"200"},
         * {"zname":"五等奖","znum":"1364595","money":"10"},
         * {"zname":"六等奖","znum":"9175247","money":"5"}]
         */

        public String kjIssue;
        public String kjdate;
        public String kjznum;
        public String kjtnum;
        public List<Mlistbean> mlist;
        public List<Bonusbean> bonus;

        public static class Mlistbean {
            /**
             * mname : 本期销量：329,821,920元
             */

            public String mname;
        }

        public static class Bonusbean {
            /**
             * zname : 一等奖
             * znum : 8
             * money : 7075823
             */

            public String zname;
            public String znum;
            public String money;
        }
    }
}
