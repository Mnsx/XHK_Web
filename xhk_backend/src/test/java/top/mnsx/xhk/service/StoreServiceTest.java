package top.mnsx.xhk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Store;

@SpringBootTest
public class StoreServiceTest {
    @Autowired
    private IStoreService storeService;

    @Test
    public void listStore() {
        storeService.listStore("test", 0).forEach(System.out::println);
    }

    @Test
    public void addStore() {
//        Store store = new Store();
//        store.setUid(5L);
//        store.setStoreName("test3");
//        storeService.addStore(store, "test1");
    }

    @Test
    public void removeStoreBySid() {
        storeService.removeStoreBySid(9L);
    }

    @Test
    public void modifiedStoreBySid() {
        Store store =  new Store("mo", "110", "110");
        store.setSid(7L);
        storeService.modifiedStoreBySid(store, "test9");
    }
}
