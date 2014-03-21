package io.koju.sample.directory.service.impl;

import io.koju.sample.directory.entity.User;
import io.koju.sample.directory.exceptions.EntityValidationException;
import io.koju.sample.directory.repo.UserRepository;
import io.koju.sample.directory.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by Kapil Koju on 3/19/14.
 */
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isPasswordValid(User user) {
        if (user.getPassword().equals(user.getUsername())) {
            return false;
        }

        if (user.getPassword().equals(user.getName().replace(" ", ""))) {
            return false;
        }

        return true;
    }

    @Override
    public User createNewUser(User user) throws EntityValidationException {
        if (user == null) {
            throw new EntityValidationException("Invalid User");
        }

        if (user.getId() != null) {
            throw new EntityValidationException("User already exists");
        }

        if (!isPasswordValid(user)) {
            throw new EntityValidationException("Invalid Password");
        }

        try {
            user = userRepository.save(user);
            System.out.println(isNameEmpty(user));
        } catch (ConstraintViolationException e) {
            StringBuilder msgBuilder = new StringBuilder();
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                msgBuilder.append(violation.getPropertyPath() + " " + violation.getMessage() + "\n");
            }

            throw new EntityValidationException(msgBuilder.toString());
        }
        return user;
    }

    public static boolean isNameEmpty(User user) {
        return user.getName().isEmpty();
    }
}
