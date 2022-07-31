package com.zzw.dig_back_220721.controller.basicImgProcessController;

import com.zzw.dig_back_220721.body.ClockBody;
import com.zzw.dig_back_220721.body.Response;
import com.zzw.dig_back_220721.body.Submission;
import com.zzw.dig_back_220721.dao.GeneralDao;
import com.zzw.dig_back_220721.dao.basicImgProcessDao.B301Dao;
import com.zzw.dig_back_220721.tool.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class B301 {
    @Autowired
    GeneralDao generalDao;

    @Autowired
    B301Dao b301Dao;

    @PostMapping({"/three_channel", "/bgr2gray"})
    public Response ThreeChannelAndBGR2Gray(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + GlobalVariable.tcode2py.get(su.getStype());
        String para1=su.getImg1();
        String para2=su.getSid();
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

    @GetMapping(value = "/get_output_img_three_channel_r/{sid}")
    public byte[] GetChannelR(@PathVariable(value = "sid") String sid) {
        File file = new File(GlobalVariable.outputImgPath + sid + "_r.jpg");
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

    @GetMapping(value = "/get_output_img_three_channel_g/{sid}")
    public byte[] GetChannelG(@PathVariable(value = "sid") String sid) {
        File file = new File(GlobalVariable.outputImgPath + sid + "_g.jpg");
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

    @GetMapping(value = "/get_output_img_three_channel_b/{sid}")
    public byte[] GetChannelB(@PathVariable(value = "sid") String sid) {
        File file = new File(GlobalVariable.outputImgPath + sid + "_b.jpg");
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

    @PostMapping("/binarization")
    public Response Binarization(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + GlobalVariable.tcode2py.get(su.getStype());
        String para1=su.getImg1();
        String para2=su.getPara1();
        String para3=su.getSid();
        String[] cmdArr=new String[]{exe,pyFile,para1,para2,para3};
        String cmd=String.join(" ",cmdArr);
        System.out.println(cmd);
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(200,"good");
    }

    @GetMapping("/get_binarization_submission/{sid}")
    public String GetBinarizationSubmission(@PathVariable(value = "sid")String sid){
        return b301Dao.GetThresholdBySid(sid);
    }
}
