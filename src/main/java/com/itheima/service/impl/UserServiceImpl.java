package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(SysUser user) {
        //对密码进行加密，然后再入库
        bCryptPasswordEncoder.encode(user.getPassword());
        userDao.save(user);
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = userDao.findRolesByUid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        userDao.removeRoles(userId);
        for (Integer rid : ids) {
            userDao.addRoles(userId, rid);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SysUser sysUser = userDao.findByName(username);
            if (sysUser == null) {
                //若用户名不正确，则直接返回null，表示认证失败
                return null;
            }
            List<SimpleGrantedAuthority> authorties = new ArrayList<>();
            List<SysRole> roles = sysUser.getRoles();
            for (SysRole role : roles) {
                authorties.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            //最终需要返回一个SpringSecurity的UserDetails对象，{noop}表示不加密认证
            return new User(sysUser.getUsername(),
                    sysUser.getPassword(),
                    sysUser.getStatus() == 1,
                    true,
                    true,
                    true,
                    authorties);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
