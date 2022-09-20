package ch.zli.coworkingSpace.controller;

import ch.zli.coworkingSpace.model.MemberEntity;
import ch.zli.coworkingSpace.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/members/{id}")
    public ResponseEntity<Optional<MemberEntity>>
    getMember(@PathVariable Long id) {
        Optional<MemberEntity> user = memberService.loadOne(id);

        if (user.isPresent()) {
            System.out.println("Accessing single date, HTTP: 200");
            return ResponseEntity
                    .status(HttpStatus.OK)  // HTTP 200
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        } else {
            System.out.println("Accessing single joke, HTTP: 404");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberEntity>> getMembers() {
        return ResponseEntity
                .status(HttpStatus.OK) // HTTP 200
                .contentType(MediaType.APPLICATION_JSON)
                .body(memberService.loadAll());
    }

    @PostMapping("/members")
    public ResponseEntity<MemberEntity>
    addMember(@RequestBody MemberEntity member) {
        System.out.println("booking created");

        memberService.create(member);
        return ResponseEntity
                .status(HttpStatus.CREATED)  // HTTP 201
                .contentType(MediaType.APPLICATION_JSON)
                .body(member);
    }

}

