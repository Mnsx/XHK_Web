package top.mnsx.xhk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mnsx.xhk.dao.CodeDao;
import top.mnsx.xhk.entity.Code;
import top.mnsx.xhk.service.ICodeService;

import java.util.List;

/**
 * 注册码业务逻辑层实现类
 */
@Service
public class CodeServiceImpl implements ICodeService {
    @Autowired
    private CodeDao codeDao;

    @Override
    public List<Code> listAll(Integer curPage) {
        int startPage = curPage * 7;
        List<Code> codeList = codeDao.findAll(startPage);
        return codeList;
    }

    @Override
    public Integer getCount() {
        return codeDao.getCount();
    }

    @Override
    public void addCode(String code) {
        codeDao.insertCode(code);
    }

    @Override
    public void removeCode(Long cid) {
        codeDao.deleteByCid(cid);
        if (codeDao.getCount() <= 0) {
            for (int i = 0; i < 100; ++i) {
                codeDao.insertCode(getCode());
            }
        }
    }

    /**
     * 注册码生成
     */
    private String getCode() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; ++i) {
            sb.append((int)Math.floor(Math.random() * 10));
        }
        return sb.toString();
    }
}
