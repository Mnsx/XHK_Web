package top.mnsx.xhk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.xhk.dao.AdvertisingDao;
import top.mnsx.xhk.entity.Advertising;
import top.mnsx.xhk.service.IAdvertisingService;
import top.mnsx.xhk.service.ex.AdvertisingNotFoundException;
import top.mnsx.xhk.service.ex.DeleteException;
import top.mnsx.xhk.service.ex.InsertException;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 广告业务逻辑层实现类
 */
@Service
public class AdvertisingServiceImpl implements IAdvertisingService {
    @Autowired
    private AdvertisingDao advertisingDao;
    @Override
    public void saveAD(String url, String username, String file) {
        Integer rows = advertisingDao.insertAD(url, username, new Date(), file);
        if (rows != 1) {
            throw new InsertException("插入广告数据时产生未知异常");
        }
    }

    @Override
    public List<Advertising> findAll() {
        return advertisingDao.findAll();
    }

    @Override
    public void removeAD(Long aid) {
        Advertising result = advertisingDao.findByAid(aid);
        if (result == null) {
            throw new AdvertisingNotFoundException("广告不存在异常");
        }
        Integer rows = advertisingDao.removeADByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除广告数据时产生未知异常");
        }
        File file = new File("D:\\WorkSpace\\XHK\\xhk_backend\\src\\main\\resources\\static\\img\\focus\\" + result.getFile());
        file.delete();
    }

    @Override
    public List<Advertising> getAllAD() {
        return advertisingDao.getAll();
    }
}
