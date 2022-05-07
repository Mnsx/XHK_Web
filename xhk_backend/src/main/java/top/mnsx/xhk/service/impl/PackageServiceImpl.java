package top.mnsx.xhk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.xhk.dao.PackageDao;
import top.mnsx.xhk.dao.UserDao;
import top.mnsx.xhk.entity.Package;
import top.mnsx.xhk.entity.User;
import top.mnsx.xhk.service.IPackageService;
import top.mnsx.xhk.service.IUserService;
import top.mnsx.xhk.service.ex.DeleteException;
import top.mnsx.xhk.service.ex.InsertException;
import top.mnsx.xhk.service.ex.UsernameNotFoundException;

import java.util.Date;
import java.util.List;

@Service
public class PackageServiceImpl implements IPackageService {
    @Autowired
    private PackageDao packageDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Package> listAll(Integer curPage) {
        int startPage = curPage * 7;
        List<Package> packageList = packageDao.findAll(startPage);
        return packageList;
    }

    @Override
    public Integer getCount() {
        return packageDao.getCount();
    }

    @Override
    public void addPackage(Package p, String username) {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        Integer rows = packageDao.insertPackage(user.getUid(), p.getTid(), p.getCreatedUser(), new Date());
        if (rows != 1) {
            throw new InsertException("插入券包数据时产生未知异常");
        }
    }

    @Override
    public void removePackage(Long pid) {
        Integer rows = packageDao.deletePackage(pid);
        if (rows != 1) {
            throw new DeleteException("删除用户数据时产生未知错误");
        }
    }

    @Override
    public void removePackageByUid(Long uid) {
        Integer rows = packageDao.deletePackageByUid(uid);
        if (rows != 1) {
            throw new DeleteException("删除用户数据时产生未知异常");
        }
    }


}
