package cn.hncu.p3.p4_conditional;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/22.
 * Time: 下午 11:01.
 * Explain:
 */
public class AA {
    public static void main(String[] args) {
        try {
            DataOutputStream dout=new DataOutputStream(new FileOutputStream("d:/c.txt"));

            short i=0;
            short j=1;
            do{
                dout.writeShort(i);
                dout.writeShort(j);

                i=(short) (i+j);
                j=(short) (i+j);
            }while(i>0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e1){

        }
    }

}
