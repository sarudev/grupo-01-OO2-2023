package com.oo2.grupo01.annotations;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.oo2.grupo01.Utils.JWT;
import com.oo2.grupo01.models.UserData;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthRoleAspect {
  @Autowired
  HttpServletRequest req;

  @Pointcut("@annotation(com.oo2.grupo01.annotations.AuthRole)")
  public void authRoleAnnotation() {
  }

  @Around("authRoleAnnotation()")
  public Object logAuthRoleAccess(ProceedingJoinPoint joinPoint) throws Throwable {
    var annRole = UserData.roleToValue(getAnnotationRole(joinPoint));

    if (annRole > -1) {
      var cookie = JWT.getJwt(req);
      System.out.println(cookie);

      if (cookie == null || cookie.getValue().equals("")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("{\"error\":\"Token missing\"}");
      }

      String jwtCookieValue = cookie.getValue();
      System.out.println(jwtCookieValue);

      var userData = UserData.toMap(UserData.decodeJWTpayload(jwtCookieValue));
      var jwtRole = UserData.roleToValue((String) userData.get("role"));

      if (jwtRole == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("{\"error\":\"Invalid token\"}");
      }

      if (jwtRole < annRole) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("{\"error\":\"Permission missing\"}");
      }
    }

    return joinPoint.proceed();
  }

  private String getAnnotationRole(ProceedingJoinPoint joinPoint) {
    Method method = getMethod(joinPoint);
    AuthRole testingAnnotation = method.getAnnotation(AuthRole.class);
    return testingAnnotation.value();
  }

  private Method getMethod(ProceedingJoinPoint joinPoint) {
    Method[] methods = joinPoint.getSignature().getDeclaringType().getMethods();
    String methodName = joinPoint.getSignature().getName();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        return method;
      }
    }
    throw new IllegalArgumentException("Method not found: " + methodName);
  }
}
