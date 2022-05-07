package top.mnsx.xhk.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 券包记录实体类
 */
@Data
public class Package extends BaseEntity implements Serializable {
    private Long pid;
    private Long uid;
    private Long tid;
    private Integer deleted;
}
