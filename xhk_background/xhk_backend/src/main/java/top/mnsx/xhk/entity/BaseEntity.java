package top.mnsx.xhk.entity;

import lombok.Data;

import java.util.Date;

/**
 * 通用类
 * createdUser 创建者
 * createdTime 创建时间
 * modifiedUser 修改者
 * modifiedTime 修改时间
 */
@Data
public class BaseEntity {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
