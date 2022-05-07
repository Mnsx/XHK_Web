package top.mnsx.xhk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.mnsx.xhk.entity.Ticket;

@Data
@AllArgsConstructor
public class TicketVO {
    private Ticket ticket;
    private String storeName;
}
