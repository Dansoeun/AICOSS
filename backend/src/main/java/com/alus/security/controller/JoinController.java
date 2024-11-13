package com.alus.security.controller;


import com.alus.security.dto.JoinDTO;
import com.alus.security.service.JoinService;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@Controller
//public class JoinController {
//
//    @Autowired
//    private JoinService joinService;
//
//    @GetMapping("/join")
//    public String joinP(){
//        return "join";
//    }
//
//    @PostMapping("/joinProc")
//    public String joinProcess(@RequestBody JoinDTO joinDTO){
//
//        //System.out.println(joinDTO.getUsername());
//        joinService.joinProcess(joinDTO);
//
//        return "redirect:/login";
//    }
//}

@Controller
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String joinP() {
        return "join";  // 회원가입 페이지를 반환
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody JoinDTO joinDTO) {
        String result = joinService.joinProcess(joinDTO);
        return ResponseEntity.ok(result); // 결과를 JSON 형식으로 반환
    }
}
