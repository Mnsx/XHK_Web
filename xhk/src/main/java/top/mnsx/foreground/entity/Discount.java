package top.mnsx.foreground.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Discount {
    private Long id;
    private Integer money;
    private String storeName;
}
