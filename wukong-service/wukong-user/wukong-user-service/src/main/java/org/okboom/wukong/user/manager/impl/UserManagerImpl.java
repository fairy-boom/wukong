package org.okboom.wukong.user.manager.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.okboom.wukong.dubbo.exception.ServiceException;
import org.okboom.wukong.user.dao.UserInfoDao;
import org.okboom.wukong.user.domain.UserInfo;
import org.okboom.wukong.user.manager.UserManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tookbra
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserManagerImpl implements UserManager {

    private final UserInfoDao userInfoDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean save(UserInfo user) {
        return userInfoDao.insert(user) > 0;
    }

    @Override
    public boolean exists(String account) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>();
        if(Validator.isEmail(account)) {
            queryWrapper.lambda().eq(UserInfo::getEmail, account);
        } else if(Validator.isMobile(account)) {
            queryWrapper.lambda().eq(UserInfo::getMobile, account);
        } else {
            throw new ServiceException(1, "dsd");
        }
        int count = userInfoDao.selectCount(queryWrapper);
        return count > 1;
    }

    @Override
    public UserInfo findByMobile(String mobile) {
        UserInfo userInfo = userInfoDao.selectOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getMobile, mobile));
        return userInfo;
    }
}
