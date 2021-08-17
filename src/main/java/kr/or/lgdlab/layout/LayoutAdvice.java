package kr.or.lgdlab.layout;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.io.Writer;

/**
 *  Description : mustache {{mainLayout}} template을 처리하기 위한 layout 객체
 *
 *  수정일       수정자       수정내용
 *  ---------   ---------   -------------------------------
 *  2021-08-11  ljw         최초생성
 *
 *  Copyright (C) by LG Discovery Lab. All right reserved.
 */
@ControllerAdvice
public class LayoutAdvice {

    @Autowired
    private MainLayout mainLayout;

    @ModelAttribute("mainLayout")
    public Mustache.Lambda layout() {
        return mainLayout;
    }
}

@Slf4j
@Component
class MainLayout implements Mustache.Lambda {

    private String body;

    @Autowired
    private Mustache.Compiler compiler;

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        log.debug("Layout : execute");
        body = frag.execute();
        compiler.compile("{{>common/layout}}").execute(frag.context(), out);
    }
}