package com.zzw.dig_back_220721.controller;

import com.zzw.dig_back_220721.body.Response;
import com.zzw.dig_back_220721.body.Submission;
import com.zzw.dig_back_220721.dao.GeneralDao;
import com.zzw.dig_back_220721.tool.GlobalVariable;
import com.zzw.dig_back_220721.tool.IdGenerator;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GeneralControler {
    @Autowired
    GeneralDao generalDao;

    @PostMapping(value = "/upload_image")
    public Response ReceiveImage(
            @RequestParam("file") MultipartFile file) {
        System.out.println("upload img");
        if (file.isEmpty()) {
            return new Response(-100,"");
        } else {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = GlobalVariable.inputImgPath;
            fileName = IdGenerator.GenerateImgId() + suffixName;
            System.out.println(filePath + fileName);
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Response(200,fileName);
        }
    }


    @GetMapping("/get_sid")
    public String GetPid(){
        return IdGenerator.GenerateSid();
    }

    @GetMapping("/get_record")
    public List<Submission> GetSubmissionRecord(){
        return generalDao.GetSubmissionRecord();
    }

    @GetMapping(value = "/get_input_img/{filename}")
    public byte[] GetInputImgByFilename(@PathVariable(value = "filename") String filename) {
        File file = new File(GlobalVariable.inputImgPath + filename);
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

    @GetMapping(value = "/get_output_img/{sid}")
    public byte[] GetOutputImgBySid(@PathVariable(value = "sid") String sid) {
        File file = new File(GlobalVariable.outputImgPath + sid + "_out.jpg");
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

    @RequestMapping("/download_pic_py_zip")
    public Object DownloadPicPyZip(HttpServletResponse response){
        OutputStream os = null;
        InputStream is= null;
        String fileName="use.zip";
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/zip;charset=utf-8");
            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，
            // 保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
            //把文件名按UTF-8取出，并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"),"ISO8859-1"));
            //读取流
            File f = new File(GlobalVariable.filePath+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return null;
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return null;
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "yes";
    }
}
