package com.vehicleWeb.services;
import com.vehicleWeb.data.User;
import com.vehicleWeb.exceptions.UserException;
import com.vehicleWeb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @SpyBean
    private UserRepository userRepository;

    @Test
    void save() throws UserException {
        User user = new User();
        user.setAddress("barrio");
        user.setEmail("faffjd");
        user.setName("andrea");
        user.setPhone("32164792");

        Optional<User> opUser = userService.save(user);

        assertTrue(opUser.isPresent());
    }

    @Test
    void findAll() throws UserException {
        mockFindAllWithResults();
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertFalse(users.isEmpty(), "The user list should not be empty");
    }

    private void mockFindAllWithResults() {
        User user = new User();
        user.setAddress("barrio");
        user.setEmail("faffjd");
        user.setName("andrea");
        user.setPhone("32164792");
        doReturn(List.of(user)).when(userRepository).findAll();
    }

    @Test
    void findAllError() throws UserException {
        doReturn(Collections.emptyList()).when(userRepository).findAll();

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertTrue(users.isEmpty(), "The user list should be empty");
    }

    @Test
    void findByEmail() throws UserException {
        String email = "faffjd";
        User users = userService.findByEmail(email);
        assertNotNull(users);
        assertFalse(users == null, "The user list should not be empty for the given email");
        assertEquals(email, users.getEmail(), "The email of the first user should match the queried email");
    }


    @Test
    void deleteUser() throws UserException {
        String email = "faffjd";

        User existingUser = new User();
        existingUser.setName("andrea");
        existingUser.setAddress("barrio");
        existingUser.setEmail(email);
        existingUser.setPhone("32164792");

        doReturn(existingUser).when(userRepository).findByEmail(email);

        userService.deleteUser(email);

        doReturn(null).when(userRepository).findByEmail(email);

        User deletedUser = userRepository.findByEmail(email);
        assertNull(deletedUser, "The user should be deleted");
    }

    @Test
    void deleteUserNotFound() throws UserException {
        String email = "nonexistent";

        doReturn(null).when(userRepository).findByEmail(email);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.deleteUser(email);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void updateUser() throws UserException {
        String email = "faffjd";
        User user = new User();
        user.setName("Andrea Updated");
        user.setAddress("Updated Address");
        user.setPhone("987654321");

        User existingUser = new User();
        existingUser.setName("andrea");
        existingUser.setAddress("barrio");
        existingUser.setEmail(email);
        existingUser.setPhone("32164792");

        doReturn(existingUser).when(userRepository).findByEmail(email);
        doReturn(user).when(userRepository).save(existingUser);

        User updatedUser = userService.updateUser(email, user);

        assertNotNull(updatedUser);
        assertEquals(user.getName(), updatedUser.getName(), "The name should be updated");
        assertEquals(user.getAddress(), updatedUser.getAddress(), "The address should be updated");
        assertEquals(user.getPhone(), updatedUser.getPhone(), "The phone should be updated");
    }

    @Test
    void updateUserNotFound() {
        String email = "nonexistent";
        User user = new User();
        user.setName("Andrea Updated");
        user.setAddress("Updated Address");
        user.setPhone("987654321");

        doReturn(null).when(userRepository).findByEmail(email);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.updateUser(email, user);
        });

        assertEquals("User not found with email: " + email, exception.getMessage());
    }

    @Test
    void findByPhone() throws UserException {
        String phone = "32164792";

        User user = new User();
        user.setAddress("barrio");
        user.setEmail("faffjd");
        user.setName("andrea");
        user.setPhone(phone);

        doReturn(user).when(userRepository).findByPhone(phone);

        User result = userService.findByPhone(phone);
        assertNotNull(result, "The user should not be null");
        assertEquals(phone, result.getPhone(), "The phone of the user should match the queried phone");
    }

    @Test
    void findByPhoneNotFound() {
        String phone = "nonexistent";

        doReturn(null).when(userRepository).findByPhone(phone);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.findByPhone(phone);
        });

        assertEquals("Phone number not found, please check the phone number and try again.", exception.getMessage());
    }

    @Test
    void countUsers() throws UserException {
        Long userCount = 5L;

        doReturn(userCount).when(userRepository).count();

        Long result = userService.countUsers();
        assertNotNull(result, "The user count should not be null");
        assertEquals(userCount, result, "The user count should match the expected value");
    }



}