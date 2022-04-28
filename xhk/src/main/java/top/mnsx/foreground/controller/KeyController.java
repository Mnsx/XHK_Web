package top.mnsx.foreground.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mnsx.foreground.entity.User;
import top.mnsx.foreground.service.KeyService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/key")
@CrossOrigin
public class KeyController {
    @Autowired
    private KeyService keyService;

    @DeleteMapping
    public Boolean deleteKey(@RequestBody String rCode, HttpSession session){
        String value = (String) JSON.parse(rCode);
        User user = (User) session.getAttribute("user");
        return keyService.useKey(value, user.getId());
    }
}
