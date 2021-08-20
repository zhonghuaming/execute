package cn.huaming.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_user")
@Data
@NoArgsConstructor
@DynamicUpdate
public class User implements Serializable {
//===========================数据库字段================================
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "account")
    private String account;

    @Column(name = "pwd")
    private String pwd;
}
