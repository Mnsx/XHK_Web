package top.mnsx.xhk.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.Code;
import top.mnsx.xhk.entity.Package;
import top.mnsx.xhk.entity.Ticket;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.ICodeService;
import top.mnsx.xhk.service.IPackageService;
import top.mnsx.xhk.service.ITicketService;
import top.mnsx.xhk.service.IUserService;
import top.mnsx.xhk.service.ex.UsernameNotFoundException;
import top.mnsx.xhk.service.impl.CodeServiceImpl;
import top.mnsx.xhk.service.impl.TicketServiceImpl;
import top.mnsx.xhk.vo.TicketVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/codes")
public class CodeController extends BaseController {
    @Autowired
    private ICodeService codeService;
    @Autowired
    private IPackageService packageService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITicketService ticketService;

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

    @DeleteMapping("/delete_by_code/{code}/{uid}")
    public Map<String, Object> deleteByCode(@PathVariable("code") String code, @PathVariable("uid") Long uid) {
        codeService.removeCode(code);
        User result = userService.findUserByUid(uid);
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        codeService.removeCode(code);
        result.setBalance(result.getBalance() + 200);
        userService.modifiedUserByUid(result, result.getUid());
        List<Ticket> tickets = ticketService.getAllTicket();
        for (Ticket t : tickets) {
            Package p = new Package();
            p.setTid(t.getTid());
            p.setUid(result.getUid());
            p.setCreatedUser(result.getUsername());
            packageService.addPackage(p, result.getUsername());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }
}
