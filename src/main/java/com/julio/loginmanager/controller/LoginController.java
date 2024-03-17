package com.julio.loginmanager.controller;

import com.julio.loginmanager.dtos.LoginDto;
import com.julio.loginmanager.services.LoginManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "Login")
public class LoginController {

    @Autowired
    LoginManagerService loginManagerService;

    @Operation(
            description = "Post endpoint for create login keeper registers",
            summary = "This is the summary for create login keeper registers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Register created successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/login-manager")
    public ResponseEntity<Object> saveLoginInfo (@RequestBody LoginDto loginDto){
        return loginManagerService.saveLoginInfo(loginDto);
    }

    @Operation(
            description = "Endpoint to get saved login information",
            summary = "This is the summary for get login keeper registers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Information obtained successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/login-manager")
    public ResponseEntity<Object> getAllLoginsInfo() {
        return loginManagerService.getAllLoginsInfo();
    }

    @PostMapping("/login-manager/get-data/{loginId}")
    public ResponseEntity<Object> getByServiceId(@PathVariable (value ="loginId") UUID loginManagerID){
        return loginManagerService.getLoginById(loginManagerID);
    }

    @PutMapping("/login-manager")
    public ResponseEntity<Object> updateLoginKeeper(@RequestBody @Valid LoginDto loginDto){
        return loginManagerService.updateLoginInfo(loginDto);
    }

    @DeleteMapping("/login-manager/{loginId}")
    public ResponseEntity<Object> deleteLogin(@PathVariable (value ="loginId") UUID loginManagerID) {
        return loginManagerService.deleteLogin(loginManagerID);
    }


}
