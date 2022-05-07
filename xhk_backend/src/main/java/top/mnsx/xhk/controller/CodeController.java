package top.mnsx.xhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.Code;
import top.mnsx.xhk.service.ICodeService;
import top.mnsx.xhk.service.impl.CodeServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/codes")
public class CodeController {
    @Autowired
    private ICodeService codeService;

    @GetMapping("list_code/{curPage}")
    public Map<String, Object> listCode(@PathVariable("curPage")Integer curPage) {
        List<Code> data = codeService.listAll(curPage);
        Integer count = codeService.getCount();
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", data);
        response.put("count", count);
        return response;
    }

    @PostMapping("/add_code")
    public Map<String, Object> addCode(String code) {
        codeService.addCode(code);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @DeleteMapping("/delete_by_cid/{cid}")
    public Map<String, Object> deleteByCid(@PathVariable("cid") Long cid) {
        codeService.removeCode(cid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }
}
