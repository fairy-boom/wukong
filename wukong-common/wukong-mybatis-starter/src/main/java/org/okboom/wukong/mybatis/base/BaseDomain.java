package org.okboom.wukong.mybatis.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tookbra
 */
@Data
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 8354402933053978830L;


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    private Integer deleted;
}
