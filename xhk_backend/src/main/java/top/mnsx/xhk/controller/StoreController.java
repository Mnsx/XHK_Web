package top.mnsx.xhk.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IStoreService;
import top.mnsx.xhk.service.IUserService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
public class StoreController extends BaseController{
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IUserService userService;

    @GetMapping("/list_by_search/{curPage}")
    public Map<String, Object> listAll(@PathVariable("curPage")Integer curPage, String searchText) {
        List<Store> data = storeService.listStore(searchText, curPage);
        Integer count = storeService.getCount(searchText);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", data);
        response.put("count", count);
        return response;
    }

    @PostMapping("/add_store")
    public Map<String, Object> addStore(@RequestBody String json) {
        Map<String, String> map = JSON.parseObject(json, Map.class);
        Store store = new Store(map.get("storeName"), map.get("phone"), map.get("location"));
        storeService.addStore(store, "Mnsx_x");
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @DeleteMapping("/remove_store_by_sid/{sid}")
    public Map<String, Object> removeStoreBySid(@PathVariable("sid") Long sid) {
        storeService.removeStoreBySid(sid);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @GetMapping("/find_one_by_sid/{sid}")
    public Map<String, Object> findOneBySid(@PathVariable("sid") Long sid) {
        Store data = storeService.findOneBySid(sid);
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }

    @PutMapping("/modified_store_by_sid/{username}")
    public Map<String, Object> modifiedStoreBySid(@PathVariable("username")String username, @RequestBody String data) {
        Store store = JSON.parseObject(data, Store.class);
        storeService.modifiedStoreBySid(store, username);
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        return response;
    }
}
