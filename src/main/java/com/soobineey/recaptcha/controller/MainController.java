package com.soobineey.recaptcha.controller;


import com.soobineey.recaptcha.dto.MainDto;
import com.soobineey.recaptcha.service.MainService;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@Component
public class MainController {

  @NotNull
  MainService service;

  // 인덱스 페이지 (로그인 페이지)
  @GetMapping("/")
  public String index() {
    System.out.println("GET");
    String index = "index";
    if (index.indexOf('?') != -1) {
      index = index.substring(0, index.indexOf("?"));
    }
    System.out.println(index);
    return index;
  }

  @PostMapping("/")
  public String postIndex() {
    System.out.println("POST");
    return "index";
  }

  // 회원가입 페이지 (이동)
  @GetMapping("/goJoin")
  public String goJoin() {
    return "join";
  }

  // 정보 입력 후 회원가입 버튼 클릭시 (최종 인덱스 페이지로 이동)
  @PostMapping("/joined")
  public String join(MainDto dto) {

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String joinDate = format.format(date);

    dto.setMemberJoinDate(joinDate);

    System.out.println(dto.getMemberId());
    System.out.println(dto.getMemberPw());
    System.out.println(dto.getMemberEmail());
    System.out.println(dto.getMemberJoinDate());

    service.insertMember(dto);

    return "index";
  }


  @RequestMapping(value = "/login", method = RequestMethod.POST)
//  public String login(MainDto dto, HttpSession session, @RequestParam("id") String id, @RequestParam("pw") String pw) {
  public String login(MainDto dto, HttpSession session) {
    System.out.println(dto.getMemberId());
    System.out.println(dto.getMemberPw());

//    System.out.println(id);
//    System.out.println(pw);
//
//    dto.setMemberId(id);
//    dto.setMemberPw(pw);

    if (service.selectMember(dto) != null) {
      System.out.println("로그인 중 입니다.");

      session.setAttribute("id", dto.getMemberId());

      return "success";
    } else {
      System.out.println("뭔가 틀렸음");
      return "fail";
    }
  }

  @RequestMapping("/success")
  public String success() {
    System.out.println("goodSuccess 통과");
    return "success";
  }

  @RequestMapping("/fail")
  public String fail() {
    System.out.println("fail 통과");
    return "fail";
  }
}
