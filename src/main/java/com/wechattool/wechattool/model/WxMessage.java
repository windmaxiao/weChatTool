package com.wechattool.wechattool.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author maxiao
 */
@Data
@ToString
@NoArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@TableName("wx_massage")
public class WxMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    //ToUserName	    开发者微信号  to_user_name
    //FromUserName	    发送方帐号（一个OpenID） from_user_name
    //CreateTime	    消息创建时间 （整型） create_time
    //MsgType	        消息类型，文本为text msg_type
    //Content	        文本消息内容 content
    //MsgId	            消息id，64位整型 msg_id
    //MsgDataId	        消息的数据ID（消息如果来自文章时才有） msg_data_id
    //Idx	            多图文时第几篇文章，从1开始（消息如果来自文章时才有） Idx

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开发者微信号
     */
    @XmlElement(name = "ToUserName")
    protected String toUserName;
    /**
     * 发送方账号（一个OpenID）
     */
    @XmlElement(name = "FromUserName")
    protected String fromUserName;
    /**
     * 消息类型，文本为text
     */
    @XmlElement(name = "MsgType")
    protected String msgType;
    /**
     * 消息id，64位整型
     */
    @XmlElement(name = "MsgId")
    protected String msgId;
    /**
     * 消息的数据ID（消息如果来自文章时才有）
     */
    @XmlElement(name = "MsgDataId")
    protected String msgDataId;
    /**
     * 多图文时第几篇文章，从1开始（消息如果来自文章时才有）
     */
    @XmlElement(name = "Idx")
    protected String idx;
    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    protected long createTime;
    /**
     * 文本消息内容
     */
    @XmlElement(name = "Content")
    private String content;
}