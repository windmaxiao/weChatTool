package com.wechattool.wechattool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechattool.wechattool.mapper.WxMessageMapper;
import com.wechattool.wechattool.model.WxMessage;
import com.wechattool.wechattool.service.WxMessageService;
import org.springframework.stereotype.Service;

/**
 * @author maxiao
 */
@Service
public class WxMessageServiceImpl extends ServiceImpl<WxMessageMapper, WxMessage> implements WxMessageService {
}
