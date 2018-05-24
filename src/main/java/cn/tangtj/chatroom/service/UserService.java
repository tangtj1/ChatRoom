package cn.tangtj.chatroom.service;

import cn.tangtj.chatroom.dao.UserDao;
import cn.tangtj.chatroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tang
 */
@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User get(Long id){
        return userDao.findById(id).orElse(null);
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
