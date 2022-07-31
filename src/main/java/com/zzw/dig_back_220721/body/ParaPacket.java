package com.zzw.dig_back_220721.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParaPacket {
    private String sid;
    private String stype;
    private String img1;

    public void show(){
        System.out.println(sid +" "+ img1 +" "+ stype);
    }
}
