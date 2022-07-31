package com.zzw.dig_back_220721.controller.basicImgProcessController;

import com.zzw.dig_back_220721.body.Para2;
import com.zzw.dig_back_220721.body.Para3;
import com.zzw.dig_back_220721.body.Response;
import com.zzw.dig_back_220721.body.Submission;
import com.zzw.dig_back_220721.dao.GeneralDao;
import com.zzw.dig_back_220721.tool.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class B306 {
    @Autowired
    GeneralDao generalDao;

    @PostMapping("/highpass_filter")
    public Response HighpassFilter(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + GlobalVariable.tcode2py.get(su.getStype());
        String para1=su.getImg1();
        String para2=su.getPara1();
        String para3=su.getPara2();
        String para4=su.getSid();
        String[] cmdArr=new String[]{exe,pyFile,para1,para2,para3,para4};
        String cmd=String.join(" ",cmdArr);
        System.out.println(cmd);
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(200,"good");
    }

    @GetMapping("/get_highpass_filter_submission/{sid}")
    public Para2 GetHighpassFilterSubmission(@PathVariable(value = "sid")String sid){
        return generalDao.Get2ParaSub(sid);
    }

    @PostMapping("/differentiation")
    public Response RemoveNoise(@RequestBody(required=false) Submission su){
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

    @GetMapping("/get_differentiation_submission/{sid}")
    public String GetDifferentiationSubmission(@PathVariable(value = "sid")String sid){
        return generalDao.Get1ParaSub(sid);
    }

    @PostMapping({"/antishaped_mask", "/bgr2gray"})
    public Response AntishapedMask(@RequestBody(required=false) Submission su){
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
}
