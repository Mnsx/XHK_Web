package top.mnsx.xhk.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.Package;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.Ticket;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IPackageService;
import top.mnsx.xhk.service.IStoreService;
import top.mnsx.xhk.service.ITicketService;
import top.mnsx.xhk.service.IUserService;
import top.mnsx.xhk.service.impl.UserServiceImpl;
import top.mnsx.xhk.vo.PackageVO;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/packages")
public class PackageController extends BaseController{
    @Autowired
    private IPackageService packageService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/list_all/{curPage}")
    public Map<String, Object> listAll(@PathVariable("curPage")Integer curPage) {
        List<Package> packageList = packageService.listAll(curPage);
        List<PackageVO> data = new ArrayList<>();
        for (Package p : packageList) {
            Ticket ticket = ticketService.getTicketByTid(p.getTid());
            Store store = storeService.findOneBySid(ticket.getSid());
            User user = userService.findUserByUid(p.getUid());
            PackageVO packageVO = new PackageVO();
            packageVO.setP(p);
            packageVO.setUsername(user.getUsername());
            packageVO.setStoreName(store.getStoreName());
            packageVO.setTid(p.getTid());
            data.add(packageVO);
        }
        Integer count = packageService.getCount();
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", data);
        response.put("count", count);
        return response;
    }

    @PostMapping("/add_package")
    public Map<String, Object> addPackage(@RequestBody String json) {
        Map<String, Object> map = JSON.parseObject(json, Map.class);
        Package p = new Package();
        p.setTid(Long.parseLong((String)map.get("tid")));
        p.setCreatedUser((String)map.get("createdUser"));
        packageService.addPackage(p, (String)map.get("username"));
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @DeleteMapping("/remove_package/{pid}")
    public Map<String, Object> removePackage(@PathVariable("pid")Long pid) {
        packageService.removePackage(pid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }
}
