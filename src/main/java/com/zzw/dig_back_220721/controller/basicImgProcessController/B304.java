package com.zzw.dig_back_220721.controller.basicImgProcessController;

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
public class B304 {
    @Autowired
    GeneralDao generalDao;

    @PostMapping("/edge_detect")
    public Response EdgeDetect(@RequestBody(required=false) Submission su){
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

    @GetMapping("/get_edge_detect_submission/{sid}")
    public String GetEdgeDetectSubmission(@PathVariable(value = "sid")String sid){
        return generalDao.Get1ParaSub(sid);
    }

    @PostMapping("/hough_line")
    public Response HoughLine(@RequestBody(required=false) Submission su){
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
        String para4=su.getPara3();
        String para5=su.getSid();
        String[] cmdArr=new String[]{exe,pyFile,para1,para2,para3,para4,para5};
        String cmd=String.join(" ",cmdArr);
        System.out.println(cmd);
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(200,"good");
    }

    @GetMapping("/get_hough_line_submission/{sid}")
    public Para3 GetHoughLineSubmission(@PathVariable(value = "sid")String sid){
        return generalDao.Get3ParaSub(sid);
    }
}
