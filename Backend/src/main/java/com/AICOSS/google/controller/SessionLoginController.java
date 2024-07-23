@GetMapping(value = {"", "/"})
public String home(Model model, @SessionAttribute(name = "memberId", required = false) Long memberId) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    Member loginMember = memberService.getLoginMemberById(memberId);
    if (loginMember != null) {
        model.addAttribute("name", loginMember.getName());
    }

    return "home";
}

@GetMapping("/join")
public String joinPage(Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    model.addAttribute("joinRequest", new JoinRequest());
    return "join";
}

@PostMapping("/join")
public String join(@Valid @ModelAttribute JoinRequest joinRequest,
                   BindingResult bindingResult, Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    if (memberService.checkLoginIdDuplicate(joinRequest.getLoginId())) {
        bindingResult.addError(new FieldError("joinRequest", "loginId", "ID가 존재합니다."));
    }


    if (!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
        bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "비밀번호가 일치하지 않습니다."));
    }

    if (bindingResult.hasErrors()) {
        return "join";
    }

    memberService.join(joinRequest);

    return "redirect:/session-login";
}

@GetMapping("/login")
public String loginPage(Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    model.addAttribute("loginRequest", new LoginRequest());
    return "login";
}

@PostMapping("/login")
public String login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
                    HttpServletRequest request, Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    Member member = memberService.login(loginRequest);
    if (member == null) {
        bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
    }

    if (bindingResult.hasErrors()) {
        return "login";
    }

    request.getSession().invalidate();
    HttpSession session = request.getSession(true);
    session.setAttribute("memberId", member.getId());
    session.setMaxInactiveInterval(60 * 60);

    return "redirect:/session-login";
}

@GetMapping("/logout")
public String logout(HttpServletRequest request, Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }

    return "redirect:/session-login";
}

@GetMapping("/info")
public String memberInfo(@SessionAttribute(name = "memberId", required = false) Long memberId, Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션로그인");

    Member loginMember = memberService.getLoginMemberById(memberId);

    if (loginMember == null) {
        return "redirect:/session-login/login";
    }

    model.addAttribute("member", loginMember);
    return "info";
}

@GetMapping("/admin")
public String adminPage(@SessionAttribute(name = "memberId", required = false) Long memberId, Model model) {

    model.addAttribute("loginType", "session-login");
    model.addAttribute("pageName", "세션 로그인");
    Member loginMember = memberService.getLoginMemberById(memberId);

    if(loginMember == null) {
        return "redirect:/session-login/login";
    }
    if(!loginMember.getRole().equals(MemberRole.ADMIN)) {
        return "redirect:/session-login";
    }
    return "admin";
}
}