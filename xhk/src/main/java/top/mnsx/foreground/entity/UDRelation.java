package top.mnsx.foreground.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.Name;

import java.util.Date;

/**
 * 优惠券和用户的关联实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("xhk_discount_user")
public class UDRelation {
    private Long id;
    private Long discountId;
    private Long userId;

    @TableLogic
    private Integer Deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Date deleteTime;

    public UDRelation(Long id, Long discountId, Long userId) {
        this.id = id;
        this.discountId = discountId;
        this.userId = userId;
    }
}
