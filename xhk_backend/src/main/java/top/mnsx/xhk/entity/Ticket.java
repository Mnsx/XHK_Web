package top.mnsx.xhk.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ticket extends BaseEntity implements Serializable {
    private Long tid;
    private Long sid;
    private Double Demand;
    private Double discount;
    private Integer deleted;
}
