package shishicai.com.dubo.model;

/**
 * 新闻种类
 */

import java.io.Serializable;

/**
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300203&src=0000100001%7C6000003060   //头条
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300205&src=0000100001%7C6000003060   //彩讯
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300207&src=0000100001%7C6000003060   //公益
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300209&src=0000100001%7C6000003060   // 视频
 * <p>
 * <p>
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300210&src=0000100001%7C6000003060   // 政策
 * <p>
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300211&src=0000100001%7C6000003060  //专题
 */

public enum NewsType implements Serializable {
    头条("300203", "头条"),
    彩讯("300205", "彩讯"),
    公益("300207", "公益"),
    视频("300209", "视频"),
    政策("300210", "政策"),
    专题("300211", "专题");

    private String code, name;

    NewsType(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
