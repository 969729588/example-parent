package com.milepost.exampleSingleBoot.auth.controller;

import com.milepost.api.util.EncryptionUtil;
import com.milepost.api.util.ImgCheckCodeUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleSingleBoot.auth.entity.Jwt;
import com.milepost.exampleSingleBoot.auth.service.LoginService;
import com.milepost.exampleSingleBoot.clientDetail.entity.ClientDetail;
import com.milepost.exampleSingleBoot.clientDetail.service.ClientDetailService;
import com.milepost.exampleSingleBoot.user.entity.User;
import com.milepost.exampleSingleBoot.user.service.UserServiceImpl;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/1/29.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ClientDetailService clientDetailService;

    private final static String IMG_CHECK_CODE = "imgCheckCode";

    /**
     * Oauth客户端id，只有这一个客户端
     */
    private final static String CLIENT_DETAIL = "test-client-id";

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/doLogin")
    public Response<Map<String, Object>> doLogin( HttpServletRequest request,
                                                    @RequestParam("username") String username,
                                                    @RequestParam("password") String password,
                                                    @RequestParam(value = "imgCheckCode", required = false) String imgCheckCode,
                                                    HttpSession session){
        Response<Map<String, Object>> response = null;
        try {
            //如果前端传入了验证码就验证，否则忽略
            if(StringUtils.isNotBlank(imgCheckCode)){
                String imgCheckCodeFromSession = (String)session.getAttribute(IMG_CHECK_CODE);
                if(imgCheckCode.equalsIgnoreCase(imgCheckCodeFromSession)){
                    //验证码通过
                    response = login(request, username, password);
                }else{
                    //验证码不通过
                    response = ResponseHelper.createFailResponse();
                    response.setMsg("验证码错误");
                }
                //清空验证码，以防同一个验证码被使用多次。
                session.removeAttribute(IMG_CHECK_CODE);
            }else{
                //忽略验证码
                response = login(request, username, password);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
    private Response<Map<String, Object>> login(HttpServletRequest request, String username, String password) throws Exception {
        //验证码通过
        Map<String, Object> result = new HashMap<>();
        //获取token
        ClientDetail clientDetail = clientDetailService.selectByPrimaryKey(CLIENT_DETAIL);
        if(clientDetail == null){
            throw new Exception("不存在OAuth客户端数据。");
        }
        String clientId = CLIENT_DETAIL;
        String clientSecret = clientDetail.getClientSecret();
        String basicAuthorization = "Basic " + EncryptionUtil.encodeWithBase64(clientId + ":" + clientSecret);//Basic Auth，即 client_id + ":" + client_secret 的base64编码
        String grantType = "password";//授权方式
        //这里可以使用OpenFeign的feign.auth.BasicAuthRequestInterceptor实现，那样会代码会更多，但可以借鉴其如何实现拦截器
        Jwt jwt = loginService.getToken(request, basicAuthorization, grantType, username, password);
        jwt.setBorn_time_millis(Instant.now().toEpochMilli());
        result.put("jwt", jwt);
        String token = jwt.getAccess_token();
        String tokenType = jwt.getToken_type();
        tokenType = tokenType.substring(0,1).toUpperCase() + tokenType.substring(1) + " ";
        //获取user
        User user = userServiceImpl.getUserByUsernameAndPassword(username, password);
        user.setPassword("******");//不返回密码
        result.put("user", user);
        return ResponseHelper.createSuccessResponse(result);
    }

    /**
     * 图片验证码
     * @param response
     */
    @GetMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session) {
        try {
            //禁止缓存
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            OutputStream out = response.getOutputStream();
            String imgCheckCode = ImgCheckCodeUtil.createImage(out);
            session.setAttribute(IMG_CHECK_CODE, imgCheckCode);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 判断前端的access_token是否有效，如无效则不能进入此方法，返回401，
     */
    @ResponseBody
    @RequestMapping(value = "/logined", method = RequestMethod.OPTIONS)
    public String logined(){
        return "{}";
    }
}
