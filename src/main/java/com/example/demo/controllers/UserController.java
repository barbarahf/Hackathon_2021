package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.exceptions.EmailIsNotValid;
import com.example.demo.services.UserRegisterService;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    UserRegisterService userRegisterService;

    /**
     * @return Get the entire object of a user
     */
    @GetMapping(path = "/user")
    public String getUser() {
        return "";
    }

    /**
     * @param userId user to be delete
     * @return You can delete user object through its ID
     */
    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String userId) {
        return ResponseEntity.badRequest().build();
    }

    /**
     * @param user user to be modified
     * @return response entity
     */
    @PutMapping("/update/user/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {
       /* try {
            if (userService.findOne(user.getEmail()) == null)
                throw new UsernameNotFoundException("The email does not exists");
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }*/
        return ResponseEntity.badRequest().build();
    }

    /**
     * @param user DTO object to create an user
     * @return the registered user
     */
    @PostMapping("/sign-up/user")
    @SneakyThrows
    public HttpStatus registerUser(@RequestBody User user) {
        try {
            userRegisterService.register(user);
        } catch (Exception e) {
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.CREATED;
    }

    @GetMapping(path = "/sign-up/user/confirm")
    public String confirm(@RequestParam("token") String token) {
        return userRegisterService.confirmToken(token);
    }


    /*
    ST7: POST /github/:username
Descripción: Crea un GithubUserl, lo conecta con un usuario previamante creado a través de su id y guarda el User
ST8: POST /gitlab/:username
Descripción: Crea un GitlabUser, lo conecta con un usuario previamante creado a través de su id y guarda el User
*/


}
