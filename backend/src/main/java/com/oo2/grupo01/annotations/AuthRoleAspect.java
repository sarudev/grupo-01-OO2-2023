package com.oo2.grupo01.annotations;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.oo2.grupo01.models.UserData;

import jakarta.servlet.http.Cookie;

@Aspect
@Component
public class AuthRoleAspect {

  @Pointcut("@annotation(com.oo2.grupo01.annotations.AuthRole)")
  public void authRoleAnnotation() {
  }

  @Around("authRoleAnnotation()")
  public Object logAuthRoleAccess(ProceedingJoinPoint joinPoint) throws Throwable {
    var annRole = UserData.roleToValue(getAnnotationRole(joinPoint));

    if (annRole > -1) {
      // Obtener el argumento jwtCookie desde la anotación @CookieValue
      String jwtCookieValue = null;
      Object[] args = joinPoint.getArgs();
      for (Object arg : args) {
        if (arg instanceof Cookie) {
          Cookie cookie = (Cookie) arg;
          if (cookie.getName().equals("JWT")) {
            jwtCookieValue = cookie.getValue();
            break;
          }
        }
      }

      // Verificar si el valor del jwtCookie está presente
      if (jwtCookieValue == null || jwtCookieValue.equals("")) {
        // Devolver una respuesta de error y detener la ejecución del endpoint
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("{\"error\":\"Token missing\"}");
      }

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

    // Continuar con la ejecución del método anotado
    Object result = joinPoint.proceed();

    return result;
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
