package top.mnsx.xhk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.mnsx.xhk.entity.Store;

import java.util.List;

@Data
@AllArgsConstructor
public class StoreVO {
    private Store store;
    private String username;
}
