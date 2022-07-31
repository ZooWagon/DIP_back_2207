package com.zzw.dig_back_220721.controller;

import com.zzw.dig_back_220721.body.ParaPacket;
import com.zzw.dig_back_220721.body.Response;
import com.zzw.dig_back_220721.dao.Daodao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zzw.dig_back_220721.tool.IdGenerator;

@RestController
@CrossOrigin(origins = "*")
public class ControllerTest {

    @Autowired
    Daodao daodao;

    @GetMapping("/test")
    public String test(){
        Process proc;
        try{
            String exe="python";
            String exe2="C:/Anaconda3/envs/bpnn/python.exe";
            String pyFile="C:/Programs/PyPrograms/test/test2.py";
            String para="C:/Programs/PyPrograms/test/charlotteED/ED01.png";
            String[] cmdArr=new String[]{exe2,pyFile,para};
            String cmd=String.join(" ",cmdArr);
            System.out.println(cmd);
            System.out.println("---");
            proc=Runtime.getRuntime().exec(cmd);
            // read
            BufferedReader pyOut=new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while((line=pyOut.readLine())!=null){
                System.out.println(line);
            }
            pyOut.close();
            proc.waitFor();  // block wait
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return "hello";
    }

    @PostMapping("/post_para_test")
    public Response PostParaTest(@RequestBody(required=false) ParaPacket pp){
        pp.show();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // run python
        String exe="python";
        String pyFile="equalizeHist.py";
        String para="/zzw/ogTest/input/"+pp.getImg1();
        String[] cmdArr=new String[]{exe,pyFile,para};
        String cmd=String.join(" ",cmdArr);
        System.out.println(cmd);
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new Response(200,"good");
    }

    @GetMapping("/get_submission_test")
    public String GetSubmission(){
        daodao.SelectResidentBySid("90220726115159300");
        return "yes";
    }

}
