package thinhld.ldt.ticketservice.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Log4j2
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(@NotNull MethodParameter methodParameter,
                            @NotNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter methodParameter,
                                  @NotNull MediaType mediaType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> aClass,
                                  @NotNull ServerHttpRequest serverHttpRequest,
                                  @NotNull ServerHttpResponse serverHttpResponse) {

        // Log response
        SystemLog.log(body, TypeLog.RESPONSE);

        return body;
    }
}