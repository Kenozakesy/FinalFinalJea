package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by Gebruiker on 28-2-2019.
 */
@Interceptor
@MyInterceptorBinding
public class MyInterceptor  {

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception { //zou system log kunnen zijn
        Object[] parameters = context.getParameters();
        if (parameters.length > 0 && parameters[0] instanceof String) {
            String param = (String) parameters[0];
            parameters[0] = "Hi " + param + " !";
            context.setParameters(parameters);
        }
        return context.proceed();
    }
}
