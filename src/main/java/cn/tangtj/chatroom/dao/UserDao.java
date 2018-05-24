package cn.tangtj.chatroom.dao;

import cn.tangtj.chatroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tang
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>{

    /***
     * 通过username查找用户
     * @param username 用户名
     * @return User未找到返回null
     */
    User findByUsername(String username);
}
