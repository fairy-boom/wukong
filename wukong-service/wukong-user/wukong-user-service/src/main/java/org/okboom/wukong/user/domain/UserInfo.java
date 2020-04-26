package org.okboom.wukong.user.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.mybatis.base.BaseDomain;

import java.io.Serializable;

/**
 * @author tookbra
 */
@Data
@ToString
@TableName("user_info")
public class UserInfo extends BaseDomain {

    private static final long serialVersionUID = -8274936325908283394L;

    /**
     * 手机号
     */
    private Long mobile;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 姓名
     */
    private String username;
    /**
     * 公司名称
     */
    private String companyName;
}
