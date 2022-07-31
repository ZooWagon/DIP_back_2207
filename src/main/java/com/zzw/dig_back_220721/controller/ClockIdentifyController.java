package com.zzw.dig_back_220721.controller;

import com.zzw.dig_back_220721.body.ClockBody;
import com.zzw.dig_back_220721.body.ParaPacket;
import com.zzw.dig_back_220721.body.Response;
import com.zzw.dig_back_220721.body.Submission;
import com.zzw.dig_back_220721.dao.ClockDao;
import com.zzw.dig_back_220721.dao.GeneralDao;
import com.zzw.dig_back_220721.tool.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class ClockIdentifyController {

    @Autowired
    GeneralDao generalDao;

    @Autowired
    ClockDao clockDao;

    @PostMapping("/clock_identify")
    public Response PostParaTest(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + "clock_identify.py";
        String para1=su.getSid();
        String para2=su.getImg1();
        String[] cmdArr=new String[]{exe,pyFile,para1,para2};
        String cmd=String.join(" ",cmdArr);
        System.out.println(cmd);
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(200,"good");
    }

    @GetMapping("/get_clock_submission/{sid}")
    public ClockBody GetClockSubmission(@PathVariable(value = "sid")String sid){
        return clockDao.GetClockSubmissionBySid(sid);
    }

    @GetMapping(value = "/get_clock_img_clock/{sid}")
    public byte[] GetClockImgClockBySid(@PathVariable(value = "sid") String sid) {
        File file = new File(GlobalVariable.outputImgPath + sid + "_clock.jpg");
        byte[] bytes=null;
        try{
            FileInputStream inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytes;
    }

    @GetMapping(value = "/get_clock_img_edge/{sid}")
    public byte[] GetClockImgEdgeBySid(@PathVariable(value = "sid") String sid) {
        File file = new File(GlobalVariable.outputImgPath + sid + "_edge.jpg");
        byte[] bytes=null;
        try{
            FileInputStream inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytes;
    }

}
