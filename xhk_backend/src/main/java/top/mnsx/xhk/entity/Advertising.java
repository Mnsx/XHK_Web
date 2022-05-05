package top.mnsx.xhk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Advertising extends BaseEntity implements Serializable {
    private Long aid;
    private String url;
    private String file;
}
