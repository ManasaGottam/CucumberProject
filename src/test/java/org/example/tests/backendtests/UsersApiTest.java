package org.example.tests.backendtests;

import lombok.RequiredArgsConstructor;
import org.example.backend.User;
import org.example.backend.UsersApiClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
@Service
@RequiredArgsConstructor
public class UsersApiTest {
    @Autowired
    private UsersApiClient usersApiClient;

    @Test
    public void getAllUsersTest() {
        List<User> users = usersApiClient.getAllUsers();
        Assertions.assertEquals(100, users.size());
        Assertions.assertEquals(1, users.get(0).getId());
    }
}
