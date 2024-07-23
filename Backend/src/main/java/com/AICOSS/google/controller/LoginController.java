@RequestMapping("")
public String session(HttpServletRequest request){

    HttpSession session = request.getSession(true);
}

session.setAttribute("키", "값");

session.setMaxInactiveInterval(60 * 60); //1시간

@RequestMapping("")
public String getSessionAttribute(HttpSession session){

    String value = session.getAttribute("key");

}

@RequestMapping("")
public String getSessionAttribute(@SessionAttribute(name = "키", required = false) String value){

}

session.invalidate();