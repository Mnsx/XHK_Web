package top.mnsx.xhk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.mnsx.xhk.entity.Package;

@Data
public class PackageVO {
    private Package p;
    private String username;
    private String storeName;
    private Long tid;
}
