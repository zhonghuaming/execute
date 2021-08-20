package cn.huaming.captcha;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CaptchaController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    public static void main(String[] args) {
//        SpecCaptcha captcha = new SpecCaptcha(130, 48, 6);
//        captcha.setCharType(Captcha.TYPE_DEFAULT);
//        String verCode = captcha.text().toLowerCase();
//        System.out.println(captcha.toBase64());
//        System.out.println(verCode);

        ArithmeticCaptcha captcha =
                new ArithmeticCaptcha(130, 48, 2);
        String verCode = captcha.text().toLowerCase();
        System.out.println(captcha.toBase64());
        System.out.println(verCode);
    }
}