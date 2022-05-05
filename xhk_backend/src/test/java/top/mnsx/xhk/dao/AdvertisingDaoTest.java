package top.mnsx.xhk.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Advertising;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AdvertisingDaoTest {
    @Autowired
    private AdvertisingDao advertisingDao;

    @Test
    public void insertAD() {
        Integer rows = advertisingDao.insertAD("test", "Mnsx_x", new Date(), "test");
        System.out.println(rows);
    }

    @Test
    public void findAll() {
        List<Advertising> ads = advertisingDao.findAll();
        ads.forEach(System.out::println);
    }

    @Test
    public void removeADByAid() {
        System.out.println(advertisingDao.removeADByAid(18L));
    }

    @Test
    public void findByAid() {
        System.out.println(advertisingDao.findByAid(19L));
    }
}
