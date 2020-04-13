package org.okboom.wukong.sms.exchange.protocol.cmpp;

import lombok.Data;
import lombok.ToString;
import org.okboom.wukong.sms.exchange.protocol.Message;

/**
 * @author tookbra
 */
@Data
@ToString
public class CmppQueryMessage extends Message {

    public CmppQueryMessage() {
        super(CmppCommand.QUERY, 12);
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
     * 保留
     */
    private String reserve;

}
