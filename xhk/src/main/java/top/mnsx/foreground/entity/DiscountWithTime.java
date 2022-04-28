package top.mnsx.foreground.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@AllArgsConstructor
@Data
public class DiscountWithTime {
    private Integer money;
    private String storeName;
    private String createTime;
}
