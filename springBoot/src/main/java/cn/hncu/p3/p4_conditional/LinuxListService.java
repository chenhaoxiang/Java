package cn.hncu.p3.p4_conditional;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:42.
 * Explain:Linux下所要创建的Bean的类
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
