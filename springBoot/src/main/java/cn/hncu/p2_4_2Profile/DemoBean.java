package cn.hncu.p2_4_2Profile;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 下午 8:37.
 * Explain:示例Bean
 */
public class DemoBean {
    public String content;

    public DemoBean(String content) {
        super();
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
