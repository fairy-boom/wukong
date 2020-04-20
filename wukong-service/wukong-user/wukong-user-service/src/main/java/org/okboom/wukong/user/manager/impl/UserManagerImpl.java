package org.okboom.wukong.user.manager.impl;

import lombok.AllArgsConstructor;
import org.okboom.wukong.user.dao.UserDao;
import org.okboom.wukong.user.domain.User;
import org.okboom.wukong.user.manager.UserManager;
import org.springframework.stereotype.Service;

/**
 * @author tookbra
 */
@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {

    private final UserDao userDao;

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public User findByAccount(String account) {
        return null;
    }
}
