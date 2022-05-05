package top.mnsx.xhk.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Advertising;

import java.util.Date;
import java.util.List;

/**
 * 广告业务逻辑层测试类
 */
@SpringBootTest
public class AdvertisingServiceTest {
    @Autowired
    private IAdvertisingService advertisingService;

    @Test
    public void saveAD() {
        advertisingService.saveAD("hello", "Mnsx_x", "test");
    }

    @Test
    public void findAll() {
        List<Advertising> all = advertisingService.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void removeAD() {
        advertisingService.removeAD(19L);
    }
}
