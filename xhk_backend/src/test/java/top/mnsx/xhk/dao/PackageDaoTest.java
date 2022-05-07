package top.mnsx.xhk.dao;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Package;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class PackageDaoTest {
    @Autowired
    private PackageDao packageDao;

    @Test
    public void getAll() {
        List<Package> packageList = packageDao.findAll(0);
        packageList.forEach(System.out::println);
    }

    @Test
    public void getCount() {
        System.out.println(packageDao.getCount());
    }

    @Test
    public void addPackage() {
        Integer row = packageDao.insertPackage(5L, 1L, "mnsx", new Date());
        System.out.println(row);
    }

    @Test
    public void deletePackageByUid() {
        packageDao.deletePackageByUid(5L);
    }
}
