package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppQueryRespMessage extends Message {

    public CmppQueryRespMessage() {
        super(CmppCommand.QUERY_RESP, 12);
    }

    /**
     * 时间YYYYMMDD(精确至日)
     */
    private String time;

    /**
     * 查询类别： 0：总数查询； 1：按业务类型查询
     */
    private short queryType;

    /**
     * 查询码
     * 当Query_Type 为0 时，此项无效；
     * 当 Query_Type为 1时，此项填写业务类型 Service_Id
     */
    private String queryCode;

    /**
     * 从SP 接收信息总数
     */
    private Long mtTlMsg;

    /**
     * 从SP 接收用户总数
     */
    private Long mtTlUsr;

    /**
     * 成功转发数
     */
    private Long mtScs;

    /**
     * 待转发数
     */
    private Long mtWt;

    /**
     * 转发失败数
     */
    private Long mtFl;

    /**
     * 向sp成功送达数
     */
    private Long moScs;

    /**
     * 向sp待送达数
     */
    private Long moWt;

    /**
     * 向sp送达失败数
     */
    private Long moFl;

}
