package com.piggsoft.lucky.validate;

import com.piggsoft.lucky.constants.Constants;
import com.piggsoft.lucky.exception.PiggException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <br>Created by fire pigg on 2016/01/20.
 *
 * @author piggsoft@163.com
 */
public class Validate {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validate.class);
    private static final String COUNT_SUBFIX = "_count";
    private static Map<Method, ParamsValidate> PARAMS_VALIDATE_CACHE = new ConcurrentHashMap<Method, ParamsValidate>();

    public static void validate(HttpServletRequest request, HttpServletResponse response, Method method) {

        ParamsValidate paramsValidate = PARAMS_VALIDATE_CACHE.get(method);
        if (null == paramsValidate) {
            paramsValidate = AnnotationUtils.findAnnotation(method, ParamsValidate.class);
            if (null == paramsValidate) {
                return;
            }
            PARAMS_VALIDATE_CACHE.put(method, paramsValidate);
        }

        HttpSession session = request.getSession();
        validateICode(request, paramsValidate, session);
        validatePostToken(request, paramsValidate, session);
        validateParams(request, paramsValidate);
    }

    private static void validateParams(HttpServletRequest request, ParamsValidate paramsValidate) {
        ParamValidate[] pvs = paramsValidate.value();
        if (pvs != null) {
            for (ParamValidate pv : pvs) {
                String name = pv.name();
                String errorMsg = pv.errorMsg();
                String value = request.getParameter(name);
                String sp = request.getServletPath();
                if (value != null) {
                    String regex = pv.regex();
                    if (!"".equals(regex)) {//需要正则验证
                        if (!value.matches(regex)) {
                            LOGGER.warn(sp + ";参数格式错误" + name + "=" + value);
                            throw new PiggException("PARAMETER FORMAT ERROR", "".equals(errorMsg) ? ("参数格式错误" + name + "=" + value) : errorMsg);
                        }
                    }
                } else if (pv.required()) {
                    LOGGER.warn(sp + ";缺少必要参数：" + name);
                    throw new PiggException("MISSING PARAMETER", "".equals(errorMsg) ? ("缺少必要参数：" + name) : errorMsg);
                }
            }
        } else {
            LOGGER.warn("ParamValidate is empty");
        }
    }

    private static void validatePostToken(HttpServletRequest request, ParamsValidate paramsValidate, HttpSession session) {
        if (paramsValidate.postToken()) {//校验重复提交
            String postToken = request.getParameter("postToken");
            if (postToken == null || !postToken.equals(session.getAttribute(Constants.POST_TOKEN))) {
                throw new PiggException("REPEAT SUBMIT", "请勿重复提交");
            } else {
                session.setAttribute(Constants.POST_TOKEN, UUID.randomUUID().toString());
            }
        }
    }

    private static void validateICode(HttpServletRequest request, ParamsValidate paramsValidate, HttpSession session) {
        if (paramsValidate.iCode()) {
            Object code = session.getAttribute(Constants.ICODE);
            String icode = request.getParameter(Constants.ICODE.toLowerCase());
            String sessionCodeKey = Constants.ICODE + COUNT_SUBFIX;
            if (icode == null || code == null || !icode.equalsIgnoreCase((String) code)) {
                Object count = session.getAttribute(sessionCodeKey);//验证码比较次数
                int c = 1;
                String errorMsg = "验证码错误";
                if (count instanceof Integer) {
                    c = (Integer) count;
                    if (c < 5) {
                        session.setAttribute(sessionCodeKey, ++c);
                    } else {//验证码比较 5次及以上
                        session.removeAttribute(Constants.ICODE);
                        session.removeAttribute(sessionCodeKey);
                        errorMsg = "验证码错误，请刷新验证码后再输入！";
                    }
                } else {
                    session.setAttribute(sessionCodeKey, c);
                }
                throw new PiggException("VERIFICATION CODE ERROR", errorMsg);
            } else {
                session.removeAttribute(Constants.ICODE);
                session.removeAttribute(sessionCodeKey);
            }
        }
    }

}
