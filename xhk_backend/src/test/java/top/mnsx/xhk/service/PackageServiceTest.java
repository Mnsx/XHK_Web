package top.mnsx.xhk.service;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.xhk.entity.Package;

import java.util.List;

@SpringBootTest
public class PackageServiceTest {
    @Autowired
    private IPackageService packageService;

    @Test
    public void listAll() {
        List<Package> packages = packageService.listAll(0);
        packages.forEach(System.out::println);
    }

    @Test
    public void getCount() {
        System.out.println(packageService.getCount());
    }

    @Test
    public void removePackageByUid() {
        packageService.removePackageByUid(5L);
    }
}
