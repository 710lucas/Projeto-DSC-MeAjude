package com.si.meAjude.controllers;

import com.si.meAjude.service.UserSerivce;
import com.si.meAjude.service.dtos.user.UserDTO;
import com.si.meAjude.service.dtos.user.UserSaveDTO;
import com.si.meAjude.service.dtos.user.UserUpdateDTO;
import com.si.meAjude.util.PageableUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@Tag(name = "User", description = "Users management APIs")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserSerivce userService;


    @Operation(
            summary = "Create a New User",
            description = "Create a new user by providing the necessary information in the request body. " +
                    "The user will be created with the role of USER."
           )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserSaveDTO dto){
        return new ResponseEntity<>(userService.save(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve User by ID",
            description = "Get detailed information about a user by specifying its unique identifier. Returns a UserDTO containing user details such as id, username, email, and other attributes."
           )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }


    @Operation(
            summary = "Retrieve All Users",
            description = "Get a paginated list of all users. You can customize the page size, sort field, and sort direction. Returns a Page of UserDTOs containing user details such as id, username, email, and other attributes."
           )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> getAll(
            @PageableDefault(size = 10) Pageable page,
            @RequestParam(name = "sortField", required = false, defaultValue = "name") String sortField,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
    return userService.getAll(PageableUtil.getPageableWithSort(page, sortField, sortDirection));
    }

    @Operation(
            summary = "Update User by ID",
            description = "Update user details by specifying its unique identifier. Provide the updated information in the request body using the UserUpdateDTO. Returns the updated UserDTO with details such as id, username, email, and other attributes."
            )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserUpdateDTO userUpdateDTO, @PathVariable Long id){
        return new ResponseEntity<>(userService.update(userUpdateDTO, id), HttpStatus.OK);
    }


    @Operation(
            summary = "Logical Delete User by ID",
            description = "Perform a logical deletion of a user by specifying its unique identifier. This operation marks the user as deleted without physically removing it. Returns the deleted UserDTO with details such as id, username, email, and other attributes."
            )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> logicDelete(@PathVariable Long id){
        return new ResponseEntity<>(userService.logicDelete(id), HttpStatus.OK);
    }
}
