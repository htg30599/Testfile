package com.ebanking.repository;
import com.ebanking.entity.Role;
import com.ebanking.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Data
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findUserEntityByUsernamePositive() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role((long) 1, "Admin"));
        User detachedEntity = new User((long)1, "username", "password", roles);
        entityManager.persist(detachedEntity);
        entityManager.flush();

        Optional<UserEntity> userEntity = userRepository.findUserEntityByUsername("username");
        assertEquals(userEntity.isPresent(), true);
        assertEquals(userEntity.get().getUsername(), "username");
    }

    @Test
    void findUserEntityByUsernameNegative() {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUsername("username");
        assertEquals(userEntity.isPresent(), false);
        assertEquals(userEntity.get().getUsername(), "username");
    }
}