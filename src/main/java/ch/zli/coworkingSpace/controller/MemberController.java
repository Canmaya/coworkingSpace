package ch.zli.coworkingSpace.controller;

import ch.zli.coworkingSpace.model.MemberEntity;
import ch.zli.coworkingSpace.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(
            summary = "Get one specific member by id",
            description = "Loads one specific member by id from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/members/{id}")
    public ResponseEntity<Optional<MemberEntity>>
    getMember(@PathVariable Long id) {
        Optional<MemberEntity> user = memberService.loadOne(id);

        if (user.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)  // HTTP 200
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get all members",
            description = "Loads all members from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/members")
    public ResponseEntity<List<MemberEntity>> getMembers() {
        return ResponseEntity
                .status(HttpStatus.OK) // HTTP 200
                .contentType(MediaType.APPLICATION_JSON)
                .body(memberService.loadAll());
    }

    @Operation(
            summary = "Create a new member",
            description = "Creates a new member in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/members")
    public ResponseEntity<MemberEntity>
    addMember(@RequestBody MemberEntity member) {
        memberService.create(member);
        return ResponseEntity
                .status(HttpStatus.CREATED)  // HTTP 201
                .contentType(MediaType.APPLICATION_JSON)
                .body(member);
    }

    @Operation(
            summary = "Update an existing user",
            description = "Updates one specific and existing user in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/members/{id}")
    public ResponseEntity<MemberEntity>
    updateMember(@RequestBody MemberEntity member) {

        memberService.create(member);
        return ResponseEntity.status(HttpStatus.CREATED)  // HTTP 201
                .contentType(MediaType.APPLICATION_JSON)
                .body(member);
    }

    @Operation(
            summary = "Delete an existing member",
            description = "Deletes one specific and existing member in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/members/{id}")
    public ResponseEntity<?>
    deleteMember(@PathVariable Long id) {
         memberService.delete(id);
            return ResponseEntity.noContent().build();  // HTTP 204
    }
}
