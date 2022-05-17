package top.mnsx.xhk.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Store;

import java.util.List;

@SpringBootTest
public class StoreDaoTest {
    @Autowired
    private StoreDao storeDao;

    @Test
    public void findAll() {
        List<Store> storeList = storeDao.findAll(0, "%%");
        storeList.forEach(System.out::println);
    }

    @Test
    public void getCount() {
        System.out.println(storeDao.getCount("%%"));
    }

    @Test
    public void insertStore() {
//        Store store = new Store();
//        store.setUid(5L);
//        store.setStoreName("hello");
//        Integer row = storeDao.insertStore(store);
//        System.out.println(row);
    }

    @Test
    public void getStoreByStoreName() {
        System.out.println(storeDao.getStoreByStoreName("test1"));
    }

    @Test
    public void deleteBySid() {
        Integer row = storeDao.deleteBySid(10L);
        System.out.println(row);
    }

    @Test
    public void findStoreByBid() {
        Store store = storeDao.findStoreBySid(9L);
        System.out.println(store);
    }

    @Test
    public void modifiedStore() {
        Store store = new Store("testmo", "123", "wu");
        store.setSid(11L);
//        Integer row = storeDao.modifiedStore(store, 26L);
//        System.out.println(row);
    }

    @Test
    public void deleteStoreByUid() {
        storeDao.deleteStoreByUid(5L);
    }
}
