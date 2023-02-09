package com.example.BookstoreSystem.publicController;


import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.util.FileUtil;
import com.example.BookstoreSystem.util.UploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping("/upload")
public class uploadController {

    /**
     * 接收附件
     *
     * @param request
     * @return
     */
    @ResponseBody  //@ResponseBody的作用其实是将java对象转为json格式的数据。

    @RequestMapping(value = "fileupload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request) throws IOException {
        //文件上传的请求
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        //获取请求的参数
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        //项目的绝对路径   request.getSession().getServletContext().getRealPath()这个是得到项目的绝对地址
//        String ctxPath = request.getSession().getServletContext().getRealPath("/")
//                + FileOperateUtil.FILEDIR;
//        File file = new File(ctxPath);
//        if (!file.exists()) {
//            file.mkdir();
//        }
        Map<String, Object> result = new HashMap<String, Object>();
        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
        //用hasNext() 判断是否有值，用next()方法把元素取出。
        while (it.hasNext()) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            if (mFile.getSize() != 0 && !"".equals(mFile.getName())) {
                System.out.println(mFile.getOriginalFilename());
//                write(mFile.getInputStream(), new FileOutputStream(ctxPath+mFile.getOriginalFilename()));
//                result.put(mFile.getName(), mFile.getOriginalFilename());
            }
        }
    }
    /**
     * 接收图片文件
     *
     * @param file
     * @return Map
     * @throws IOException
     * @throws IllegalStateException
     */
    @CrossOrigin
    @PostMapping(value = "/imageupload")
    public Result imageUpload(MultipartFile file) throws IllegalStateException, IOException {
        System.out.println("imageupload");
        HashMap<String, Object> map = new HashMap<>();
        //文件名称
        String realFileName = file.getOriginalFilename();
        System.out.println("文件名称：" + realFileName);
        //文件后缀
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        /***************文件处理*********************/
        try {
            String filePath = UploadUtils.getUUIDName(realFileName);
            file.transferTo(FileUtil.getFile(filePath));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("filePath", "/img/"+filePath);
            jsonObject.put("name", realFileName);
            return new Result(200, "上传成功", jsonObject);
//            map.put(ERROR_CODE, CODE_OK);
//            map.put("name", realFileName);
//            map.put("picPath", filePath);
//            map.put(ERROR_MSSAGE, "上传图片成功");
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new Result(204, "上传失败",realFileName);

    }
}
