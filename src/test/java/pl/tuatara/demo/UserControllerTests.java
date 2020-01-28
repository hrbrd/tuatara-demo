package pl.tuatara.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.tuatara.demo.controller.UserController;
import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;
import pl.tuatara.demo.model.exception.UserNotFoundException;
import pl.tuatara.demo.service.IUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Test
    public void createUser_NewUserGiven_ShouldReturnStatusCreated() throws Exception {
        UserDto user = getTestUser();

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUser_ExistingUserGiven_ShouldReturnUserAlreadyExistsException() throws Exception {
        UserDto user = getTestUser();

        doThrow(new UserAlreadyExistsException(user.getUsername())).when(userService).create(any(UserDto.class));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user)))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(String.format("User %s already exists", user.getUsername())));
    }

    @Test
    public void getUser_ExistingUsernameGiven_ShouldReturnUser() throws Exception {
        UserDto user = getTestUser();

        when(userService.get(any(String.class))).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/" + user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()));
    }

    @Test
    public void getUser_NonExistingUsernameGiven_ShouldReturnUserNotFoundException() throws Exception {
        UserDto user = getTestUser();

        doThrow(new UserNotFoundException(user.getUsername())).when(userService).get(any(String.class));

        mockMvc.perform(get("/api/v1/users/" + user.getUsername()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(String.format("User %s not found", user.getUsername())));
    }


    private UserDto getTestUser() {
        UserDto user = new UserDto();
        user.setFirstName("John");
        user.setLastName("Travolta");
        user.setUsername("jtravolta");

        return user;
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}