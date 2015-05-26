
package relationshipBuilding.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * 例外が発生した時にメソッドの呼び出し元に例外をスローするクラス.
 * @author nagaimidori
 *
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	/**
	 * 複数のオブジェクトが作成されてしまうため、シリアライズする
	 */
	private static final long serialVersionUID = 1L;

	//Loggerクラスのインスタンスを作成
	private static final Logger logger
	=Logger.getLogger(ExceptionInterceptor.class);

	@Override
	public Object invoke (MethodInvocation invocation) throws Throwable {

		try{
			return invocation.proceed();
		} catch(Exception ex) {

			logger.info("ログ出力します");

			logger.error("エラーが発生しました",ex);

			return "/error.jsp";
		}

	}
}


