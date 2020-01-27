package pl.tuatara.demo.converter;

import org.springframework.stereotype.Component;
import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.entity.User;

@Component
public class UserConverter {

    public User convertToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCompanies(user.getCompanies());

        return userDto;
    }

}
