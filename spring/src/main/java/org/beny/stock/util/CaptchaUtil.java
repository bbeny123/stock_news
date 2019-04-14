package org.beny.stock.util;


import org.beny.stock.dto.CaptchaResponse;
import org.beny.stock.exception.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.requireNonNull;
import static org.beny.stock.exception.StockError.CAPTCHA_ERROR;
import static org.beny.stock.exception.StockError.INTERNAL_SERVER_ERROR;

@Component
public class CaptchaUtil {

    private static RestTemplate restTemplate;
    private static String url;
    private static String site;
    private static String secret;
    private static boolean enabled;

    @Autowired
    private CaptchaUtil(RestTemplate restTemplate, @Value("${captcha.url:null}") String url, @Value("${captcha.site:null}") String site, @Value("${captcha.secret:null}") String secret, @Value("${captcha.enable:false}") boolean enabled) {
        CaptchaUtil.restTemplate = restTemplate;
        CaptchaUtil.url = url;
        CaptchaUtil.site = site;
        CaptchaUtil.secret = secret;
        CaptchaUtil.enabled = enabled;
    }

    public static void verifyCaptcha(String response) throws StockException {
        try {
            if (enabled && !requireNonNull(restTemplate.getForEntity(url, CaptchaResponse.class, secret, response).getBody()).isSuccess()) {
                throw CAPTCHA_ERROR.exception();
            }
        } catch (StockException ex) {
            throw ex;
        } catch (Exception ex) {
            throw INTERNAL_SERVER_ERROR.exception();
        }
    }

}
