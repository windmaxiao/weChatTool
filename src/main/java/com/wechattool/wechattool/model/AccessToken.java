package com.wechattool.wechattool.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author maxiao
 */
@TableName("data_wx_access_token")
@Data
@ToString
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String accessToken;

    private Long expiresIn;

    private String errmsg;

    private String errcode;

    private LocalDateTime createTime;

    private LocalDateTime expiresTime;
}
