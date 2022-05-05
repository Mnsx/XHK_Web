package top.mnsx.xhk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.xhk.dao.StoreDao;
import top.mnsx.xhk.dao.UserDao;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IStoreService;
import top.mnsx.xhk.service.ex.*;
import top.mnsx.xhk.vo.StoreVO;

import java.util.Date;
import java.util.List;

/**
 * 商家业务逻辑层实现类
 */
@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Store> listStore(String searchTest, Integer curPage) {
        int startPage = curPage * 7;
        searchTest = "%" + searchTest + "%";
        List<Store> storeList = storeDao.findAll(startPage, searchTest);
        return storeList;
    }

    @Override
    public Integer getCount(String searchText) {
        searchText = "%" + searchText + "%";
        return storeDao.getCount(searchText);
    }

    @Override
    public void addStore(Store store, String username) {
        Store result = storeDao.getStoreByStoreName(store.getStoreName());
        if (result != null) {
            throw new StoreNameDuplicatedException("店名重复");
        }
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UserInfoWrongException("用户信息错误");
        }
        store.setUid(user.getUid());
        store.setCreatedUser(user.getUsername());
        store.setCreatedTime(new Date());
        store.setModifiedUser(user.getModifiedUser());
        store.setModifiedTime(new Date());
        Integer rows = storeDao.insertStore(store);
        if (rows != 1) {
            throw new InsertException("插入店家数据时产生未知异常");
        }
    }

    @Override
    public void removeStoreBySid(Long sid) {
        Store result = storeDao.findStoreBySid(sid);
        if (result == null) {
            throw new StoreNotFoundException("商家不存在");
        }
        Integer rows = storeDao.deleteBySid(sid);
        if (rows != 1) {
            throw new DeleteException("删除店家数据时产生未知异常");
        }
    }

    @Override
    public StoreVO findOneBySid(Long sid) {
        Store store = storeDao.findStoreBySid(sid);
        if (store == null) {
            throw new StoreNotFoundException("商家不存在");
        }
        Long uid = store.getUid();
        User result = userDao.findUserByUid(uid);
        return new StoreVO(store, result.getUsername());
    }

    @Override
    public void modifiedStoreBySid(Store store, String username) {
        User result = userDao.findUserByUsername(username);
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Integer rows = storeDao.modifiedStore(store, result.getUid());
        if (rows != 1) {
            throw new UpdateException("跟新店家信息时产生未知异常");
        }
    }
}
