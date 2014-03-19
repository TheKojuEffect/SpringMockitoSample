package io.koju.sample.directory.service;

import io.koju.sample.directory.entity.User;
import io.koju.sample.directory.exceptions.EntityValidationException;

/**
 * Created by kpl on 3/18/14.
 */
public interface IUserService {

    boolean isPasswordValid(User user);

    User createNewUser(User user) throws EntityValidationException;
}
