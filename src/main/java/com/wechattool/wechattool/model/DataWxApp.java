package com.wechattool.wechattool.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author MAXIAO
 * @since 2023-05-12
 */
@TableName("data_wx_app")
@Data
@ToString
public class DataWxApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String appId;

    private String appSecret;

    private String token;

    private String aesKey;

}
