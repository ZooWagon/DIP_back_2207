package com.zzw.dig_back_220721.controller.basicImgProcessController;

import com.zzw.dig_back_220721.body.*;
import com.zzw.dig_back_220721.dao.GeneralDao;
import com.zzw.dig_back_220721.dao.basicImgProcessDao.B302Dao;
import com.zzw.dig_back_220721.tool.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class B302 {

    @Autowired
    GeneralDao generalDao;

    @Autowired
    B302Dao b302Dao;

    @PostMapping("/resize")
    public Response Resize(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + GlobalVariable.tcode2py.get(su.getStype());
        String para1=su.getImg1();
        String para2="("+su.getPara1()+","+su.getPara2()+")";
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

    @GetMapping("/get_resize_submission/{sid}")
    public Para2 GetResizeSubmission(@PathVariable(value = "sid")String sid){
        return b302Dao.GetResizeSub(sid);
    }

    @PostMapping("/translation")
    public Response Translation(@RequestBody(required=false) Submission su){
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

    @GetMapping("/get_translation_submission/{sid}")
    public Para2 GetTranslationSubmission(@PathVariable(value = "sid")String sid){
        return b302Dao.GetTranslationSub(sid);
    }

    @PostMapping("/mirror_flip")
    public Response MirrorFlip(@RequestBody(required=false) Submission su){
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

    @GetMapping("/get_mirror_flip_submission/{sid}")
    public String GetMirrorFlipSubmission(@PathVariable(value = "sid")String sid){
        return b302Dao.GetMirrorFlipSub(sid);
    }

    @PostMapping("/zoom")
    public Response Zoom(@RequestBody(required=false) Submission su){
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

    @GetMapping("/get_zoom_submission/{sid}")
    public Para2 GetZoomSubmission(@PathVariable(value = "sid")String sid){
        return b302Dao.GetZoomSub(sid);
    }

    @PostMapping("/rotate")
    public Response Rotate(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + GlobalVariable.tcode2py.get(su.getStype());
        String para1=su.getImg1();
        String para2="("+su.getPara1()+")";
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

    @GetMapping("/get_rotate_submission/{sid}")
    public Para3 GetRotateSubmission(@PathVariable(value = "sid")String sid){
        return generalDao.Get3ParaSub(sid);
    }

    @PostMapping("/warp_affine")
    public Response WarpAffine(@RequestBody(required=false) Submission su){
        // 1. update DB
        String stime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        su.setStime(stime);
        generalDao.InsertSubmission(su);

        // 2. run python
        String exe="python";
        String pyFile= GlobalVariable.pyProPath + GlobalVariable.tcode2py.get(su.getStype());
        String para1=su.getImg1();
        String para2="[["+su.getPara1()+"],["+su.getPara2()+"],["+su.getPara3()+"]]";
        String para3="[["+su.getPara4()+"],["+su.getPara5()+"],["+su.getPara6()+"]]";
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

    @GetMapping("/get_warp_affine_submission/{sid}")
    public Para6 GetWarpAffineSubmission(@PathVariable(value = "sid")String sid){
        return b302Dao.GetWarpAffineSub(sid);
    }
}
