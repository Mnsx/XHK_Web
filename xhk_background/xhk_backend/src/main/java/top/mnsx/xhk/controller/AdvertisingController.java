package top.mnsx.xhk.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.mnsx.xhk.controller.ex.FileSizeMoreMaxSizeException;
import top.mnsx.xhk.controller.ex.PhotoNotAcceptException;
import top.mnsx.xhk.entity.Advertising;
import top.mnsx.xhk.service.IAdvertisingService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 图片Controller
 */
@RestController
@RequestMapping("/ads")
public class AdvertisingController extends BaseController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private IAdvertisingService advertisingService;

    @DeleteMapping("/remove_ad/{aid}")
    public Map<String, Object> removeAD(@PathVariable("aid") Long aid) {
        advertisingService.removeAD(aid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @GetMapping("/list_all")
    public Map<String, Object> listAll() {
        List<Advertising> ads = advertisingService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", ads);
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @PostMapping("/save_ad")
    public Map<String, Object> saveAD(@RequestParam MultipartFile data) throws IOException {
        if (data == null) {
            throw new PhotoNotAcceptException("图片文件接受异常");
        }
        if (data.getSize() > 1048576) {
            throw new FileSizeMoreMaxSizeException("文件大小超过最大限制");
        }
        String fileName = data.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + suffixName;
        String photoPath = "D:\\WorkSpace\\XHK\\xhk_background\\xhk_backend\\src\\main\\resources\\static\\img";
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        advertisingService.saveAD("http://localhost:8081/img/" + fileName, "Mnsx_x", fileName);
        data.transferTo(new File(finalPath));
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }
}
