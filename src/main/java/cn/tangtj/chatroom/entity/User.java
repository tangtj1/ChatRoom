package cn.tangtj.chatroom.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tang
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nicename")
    private String nicename;
}
