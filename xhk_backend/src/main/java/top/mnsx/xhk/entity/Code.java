package top.mnsx.xhk.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 注册码实体类
 */
@Data
public class Code implements Serializable {
    private Long cid;
    private String code;
}
