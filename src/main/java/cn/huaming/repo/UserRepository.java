package cn.huaming.repo;

import cn.huaming.entity.User;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>,
    JpaSpecificationExecutor<User> {

}
