import io.koju.sample.directory.context.TestAppContext;
import io.koju.sample.directory.entity.User;
import io.koju.sample.directory.exceptions.EntityValidationException;
import io.koju.sample.directory.repo.UserRepository;
import io.koju.sample.directory.service.IUserService;
import io.koju.sample.directory.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Kapil Koju on 3/19/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppContext.class})
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IUserService userService = new UserService(userRepository);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsPasswordValid() {

        User user = new User();
        user.setName("Test User One");
        user.setUsername("testuser");
        user.setPassword("testuser");

        boolean valid = false;

        valid = userService.isPasswordValid(user);
        assertThat(valid, is(false));

        user.setName("test user");
        user.setUsername("username");
        valid = userService.isPasswordValid(user);
        assertThat(valid, is(false));

        user.setPassword("ajfhjkahfdkahfdjk");
        valid = userService.isPasswordValid(user);
        assertThat(valid, is(true));
    }

    @Test
    public void testCreateNewUser() {
        User user = new User();
        user.setName("Chuck Norris");
        user.setUsername("chuchy");
        user.setPassword("0n1yCh7ckN0rissC@nS3t");

        User userMock = mock(User.class);
        userMock.setName(user.getName());
        userMock.setUsername(user.getUsername());
        userMock.setPassword(user.getPassword());

        when(userMock.getId()).thenReturn(1L);

        when(userRepository.save(user)).thenReturn(userMock);

        try {
            User createdUser = userService.createNewUser(user);
            assertThat(createdUser, notNullValue());
            verify(userRepository, times(1));

        } catch (EntityValidationException e) {
            fail("Exception shouldn't have occured");
        }

    }
}
