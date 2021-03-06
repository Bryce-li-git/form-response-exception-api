package com.bryceli.api.config;

import com.bryceli.api.annotation.NotResponseBody;
import com.bryceli.api.exception.APIException;
import com.bryceli.api.vo.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Bryce
 * @date 2020/7/27 17:31
 * @description 全局响应信息处理类
 * @Version 1.0
 */

/**
 * 注意哦，这里要加上需要扫描的包
  */
@RestControllerAdvice(basePackages = {"com.bryceli.api.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 重写的这两个方法是用来在controller将数据进行返回前进行增强操作，
     * supports方法要返回为true才会执行beforeBodyWrite方法，
     * 所以如果有些情况不需要进行增强操作可以在supports方法里进行判断。
     * 对返回数据进行真正的操作还是在beforeBodyWrite方法中，
     * 我们可以直接在该方法里包装数据，这样就不需要每个接口都进行数据包装了，省去了很多麻烦。
     * @param returnType
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        // 如果方法上加了我们的自定义注解也没有必要进行额外的操作
        return !(returnType.getParameterType().equals(ResultVO.class) || returnType.hasMethodAnnotation(NotResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }
        // 将原本的数据包装在ResultVO里
        return new ResultVO<>(data);
    }
}
