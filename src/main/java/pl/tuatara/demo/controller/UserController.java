package pl.tuatara.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;
import pl.tuatara.demo.service.IUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userService.get(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody @Valid UserDto user) throws UserAlreadyExistsException {
        userService.create(user);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This user already exists");
    }

}