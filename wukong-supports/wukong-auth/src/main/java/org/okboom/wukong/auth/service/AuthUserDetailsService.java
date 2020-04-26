package org.okboom.wukong.auth.service;

import lombok.AllArgsConstructor;
import org.okboom.wukong.user.dto.UserInfoDTO;
import org.okboom.wukong.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author tookbra
 */
@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfoDTO userInfoDTO = userService.findByMobile(s);
        if(null == userInfoDTO) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetails("","", null);
    }
}
