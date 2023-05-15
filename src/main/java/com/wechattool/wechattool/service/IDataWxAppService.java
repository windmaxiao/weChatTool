package com.wechattool.wechattool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wechattool.wechattool.model.DataWxApp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-05-12
 */
public interface IDataWxAppService extends IService<DataWxApp> {

    /**
     * 获取微信验证的数据
     * @return
     */
    DataWxApp getWxData();

}
