package com.app.userservice.controllers;


import com.app.userservice.config.KeycloakConfig;
import com.app.userservice.entities.UserRole;
import com.app.userservice.services.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    //tested and using it in the front end
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    @PostMapping("/CreateUser")
    public ResponseEntity<UserRepresentation> addUser(@RequestBody UserRepresentation userRep) {
        Keycloak k = KeycloakConfig.getInstance();
        try (Response response = k.realm("InternshipsRealm").users().create(userRep)) {
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                log.info("Error occurred while creating user: " + response.readEntity(String.class));
                throw new RuntimeException("Error occurred while creating user: " + response.readEntity(String.class));
            } else {
                UserRepresentation userRepresentation = k.realm("InternshipsRealm").users().search(userRep.getUsername()).get(0);
                userService.assignRoles(userRepresentation.getId(), userRep.getRealmRoles());
                return ResponseEntity.ok(userRepresentation);
            }
        }
    }

    //tested and using it in the front end
    @PreAuthorize("hasAnyAuthority('SuperAdmin', 'Agentesprit')")
    @PutMapping("/UpdateUser")
    public ResponseEntity<UserRepresentation> updateUser(@RequestBody UserRepresentation userRep) {
        Keycloak k = KeycloakConfig.getInstance();

        UserRepresentation userRepresentation = k.realm("InternshipsRealm").users().search(userRep.getUsername()).get(0);
        userRep.setId(userRepresentation.getId()); // setting the id of the user to be updated
        try {
            k.realm("InternshipsRealm").users().get(userRep.getId()).update(userRep);
            return ResponseEntity.ok(userRep);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(null);

        }
    }


    //tested and using it in the front end
    @GetMapping("/GetUserByEmail/{email}")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public UserRepresentation getUserByEmail(@PathVariable String email) {
        Keycloak k = KeycloakConfig.getInstance();
        log.info("executing getUserByEmail");
        try {
            return k.realm("InternshipsRealm").users().searchByEmail(email, true).get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    @PreAuthorize("hasAnyAuthority('SuperAdmin', 'Agentesprit')")
    @GetMapping("/GetUserByUserName/{username}")
    public UserRepresentation getUserByUserName(@PathVariable String username) {
        Keycloak k = KeycloakConfig.getInstance();
        log.info("executing getUserByEmail");
        try {
            return k.realm("InternshipsRealm").users().searchByUsername(username, true).get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    // tested on postman
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    @DeleteMapping("/DeleteUser/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        Keycloak k = KeycloakConfig.getInstance();
        UserRepresentation userRepresentation = k.realm("InternshipsRealm").users().search(username).get(0);
        try {
            k.realm("InternshipsRealm").users().get(userRepresentation.getId()).remove();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok( "User deleted successfully in Keycloak");
    }

    //tested and using it in Postman
    @GetMapping("/Mysession")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public Authentication authentication(Authentication authentication) {
        log.info("Authentication: {}", authentication);
        return authentication;
    }

    //tested and using it in the front end
    @GetMapping("/GetAllUsers")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public List<UserRepresentation> getAllUsers() {
        Keycloak k = KeycloakConfig.getInstance();
        log.info("executing getAllUsers");
        try {
            return k.realm("InternshipsRealm").users().list();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping("/CreateUsersFromExcel")
    @PreAuthorize("hasAnyAuthority('SuperAdmin')")
    public ResponseEntity<String> addUsersFromExcel(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<String> responses = new ArrayList<>();
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (currentRow.getRowNum() == 0) { // Skip header row
                    continue;
                }
                UserRepresentation userRep = new UserRepresentation();
                userRep.setUsername(currentRow.getCell(0).getStringCellValue());
                userRep.setEmail(currentRow.getCell(1).getStringCellValue());
                userRep.setFirstName(currentRow.getCell(3).getStringCellValue());
                userRep.setLastName(currentRow.getCell(4).getStringCellValue());
                userRep.setEmailVerified(true);
                userRep.setEnabled(true);
                userRep.setRealmRoles(List.of(UserRole.etudiant.toString()));
                CredentialRepresentation credRep = new CredentialRepresentation();
                credRep.setTemporary(true);
                credRep.setType("password");
                credRep.setValue("password");
                userRep.setCredentials(List.of(credRep));

                ResponseEntity<UserRepresentation> response = addUser(userRep);

                if (response.getStatusCode() != HttpStatus.OK) {
                    responses.add("Error occurred while creating user: " + userRep.getEmail() + " - " + "at line " + currentRow.getRowNum());
                }
            }
            workbook.close();
            if (!responses.isEmpty()) {
                return ResponseEntity.ok(responses.stream().reduce("", (a, b) -> a + "\n" + b));
            }
            return ResponseEntity.ok("Users created successfully from Excel file");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing Excel file: " + e.getMessage());
        }
    }


}
