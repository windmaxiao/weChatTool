package com.wechattool.wechattool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechattool.wechattool.mapper.AccessTokenMapper;
import com.wechattool.wechattool.model.AccessToken;
import com.wechattool.wechattool.service.AccessTokenService;
import org.springframework.stereotype.Service;

/**
 * @author maxiao
 */
@Service
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenMapper, AccessToken> implements AccessTokenService {
}
