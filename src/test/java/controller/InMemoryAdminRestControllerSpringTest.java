package controller;

import configuration.TestDatabaseConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.UserTestData;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Test
    public void testDelete() {
        controller.delete(UserTestData.USER_ID);

        Collection<User> users = controller.getAll();
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(users.iterator().next(), UserTestData.ADMIN);
    }

    @Test
    public void testGetAll() {
        List<User> allUsers = controller.getAll();

        assertThat(allUsers)
            .hasSize(2)
            .containsExactlyInAnyOrder(UserTestData.ADMIN, UserTestData.USER);
    }

    @Test
    public void testGetById() {
        User user = controller.get(UserTestData.USER_ID);
        assertThat(user).isEqualTo(UserTestData.USER);
    }

    @Test
    public void testCreate() {
        User user = new User(null, "some_new_user", "new_email@com", "new_password", 2000, true, Collections.singleton(Role.ROLE_USER));

        controller.create(user);

        assertThat(controller.getAll()).contains(user);
    }

    @Test
    public void testUpdate() {
        User updatedUser = new User(1, "some_new__updated_user", "new_updated_email@com", "new_updated_password", 2000, true,
            Collections.singleton(Role.ROLE_USER));

        controller.update(updatedUser, updatedUser.getId());

        assertThat(controller.get(updatedUser.getId())).isEqualTo(updatedUser);
    }

    @Test
    public void testGetByMail() {
        User user = controller.getByMail(UserTestData.USER.getEmail());

        assertThat(user).isEqualTo(UserTestData.USER);
    }
}
