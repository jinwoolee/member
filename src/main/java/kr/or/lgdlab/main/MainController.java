package kr.or.lgdlab.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class MainController {

    @RequestMapping(path = "/main")
    public String main() {
        log.debug("MainController.main()");
        return "main";
    }
}
