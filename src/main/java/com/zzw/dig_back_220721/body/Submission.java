package com.zzw.dig_back_220721.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Submission {
    private String sid;
    private String stime;
    private String stype;
    private String img1;
    private String img2;
    private String para1;
    private String para2;
    private String para3;
    private String para4;
    private String para5;
    private String para6;
    private String para7;
    private String para8;
    private String status;

    public void show_test(){
        System.out.println("--sub--");
        System.out.println(sid);
        System.out.println(stime);
        System.out.println(stype);
        System.out.println(img1);
        System.out.println(para1);
    }

    public void show(){
        Object obj=this;
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i = 0 , len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o;
                try {
                    o = fields[i].get(obj);
                    System.out.print(varName + " = " + o + ", ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println();
    }

    public String getTuple(){
        String ret="('";
        Object obj=this;
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i = 0 , len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o;
                try {
                    o = fields[i].get(obj);
                    ret = ret + o;
                    if(i==fields.length-1)
                        ret=ret+"')";
                    else
                        ret=ret+"','";
                    System.out.println(varName + " = " + o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }
}
