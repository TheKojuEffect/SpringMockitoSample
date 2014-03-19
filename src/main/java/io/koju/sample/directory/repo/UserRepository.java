package io.koju.sample.directory.repo;

import io.koju.sample.directory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kpl on 3/18/14.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
