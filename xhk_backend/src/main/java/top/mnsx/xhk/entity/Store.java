package top.mnsx.xhk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺实体类
 */
@Data
public class Store extends BaseEntity implements Serializable {
    private Long sid;
    private String storeName;
    private String photoUrl;
    private String location;
    private String phone;
    private String description;
    private int deleted;

    public Store(String storeName, String phone, String location) {
        this.storeName = storeName;
        this.phone = phone;
        this.location = location;
    }
}
