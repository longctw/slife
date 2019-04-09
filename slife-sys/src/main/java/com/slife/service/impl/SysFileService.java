package com.slife.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.SysFileDao;
import com.slife.entity.SysFile;
import com.slife.service.ISysFileService;

/**
 *
 * @author xull
 * @date 2017/7/31
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 文件 service
 */
@Service
@Transactional(readOnly = false,rollbackFor = Exception.class)
public class SysFileService extends BaseService<SysFileDao, SysFile> implements ISysFileService {

}
