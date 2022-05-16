package top.mnsx.xhk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.mnsx.xhk.entity.Store;
import top.mnsx.xhk.entity.Ticket;

@Data
@AllArgsConstructor
public class FirstVO {
    private Long pid;
    private Ticket ticket;
    private Store store;
}
