package springBootInitialDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootInitialDemo.dto.Greeting;
import springBootInitialDemo.dto.UserResponseDto;
import springBootInitialDemo.service.IUserService;

@RestController
@RequestMapping("/v1")
public class InitialController {

    private final IUserService userService;
    private static final String template = "Hello, %s!";

    @Autowired
    public InitialController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String helloWorld() { return "Hello World!!!"; }

    @GetMapping("/test")
    public String helloGradle() {
        return "Hello Gradle!";
    }

    @GetMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable(name = "name")String name){
        return new Greeting (String.format(template, name));
    }

    //@PutMapping(value ="", consumes = {"application/json"})
    @GetMapping("/user/{uuid}")
    public ResponseEntity<UserResponseDto> updatePrescription(
            @PathVariable(name="uuid") String user) throws Exception {


        UserResponseDto userResponseDto = userService.getUser("pp");

        System.out.println(userResponseDto.getName());
        System.out.println(userResponseDto.getSurname());
        System.out.println(userResponseDto.getGender());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }


}
