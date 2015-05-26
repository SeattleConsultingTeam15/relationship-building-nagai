
package relationshipBuilding.interceptor;

/**
 * @author nagaimidori
 *
 */

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.SingletonS2Container;

import relationshipBuilding.dto.LoginDto;
import relationshipBuilding.form.LoginForm;
 //独自インターセプターを作成する場合に継承するものAbstractInterceptor
public class LoginInterceptor extends AbstractInterceptor {
	
	//オブジェクトを更新することをシリアライズという
	//オブジェクトは変更されていないのにクラスが変更された時にシリアルバージョンで一致しているか比較できるようにする
	private static final long serialVersionUID = 1L;
	
	//ログインに関するフォームクラス
	@ActionForm
	@Resource
	protected LoginForm loginForm;
	
	/**
	 * セッションに保持されているデータを格納するDto
	 */
	@Resource
	protected LoginDto loginDto;
	
	//Loggerクラスのインスタンスを作成
	private static final Logger logger
	=Logger.getLogger(ExceptionInterceptor.class);
	
	//MethodInvocation:インターセプターの管理を行い、順次実行し、アスペクト対象メソッド(ここではロジック)の呼び出しを行います。
	@Override //invokeをオーバーライド　invoke:リクエストが飛んだ時に必ず実行されるメソッド
	public Object invoke(MethodInvocation invocation) throws Throwable {

		try {
			//コンポーネント:S2コンテナに管理されるオブジェクト
			//ログインDtoからコンポーネントを取得する(ほとんどのjavaクラスはコンポーネント化できる)
			loginDto = SingletonS2Container.getComponent(LoginDto.class);

			// 実行メソッドではない もしくは ログインセッションがある場合
			if(!isExecuteMethod(invocation) || isLoggedIn(loginDto)) {
				
				//割り込んだメソッドを実行する
				return invocation.proceed();

			} else {
				//もしログインされていなかったらログインページに遷移させる
				return "/login/?redirect=true";

			}

		} catch (ClassCastException e) {
			
			logger.error("エラーが発生しました",e);
			return "/error/";
		}

    }
	
	/**
	 * 実行メソッドかどうかを判断する(@Executeがあるかどうかで判断する)メソッド.
	 * @param invocation
	 * @return true:実行メソッド  false:実行メソッドではない
	 */
	private boolean isExecuteMethod(MethodInvocation invocation) {
		
		return invocation.getMethod().isAnnotationPresent(Execute.class);
		
	}

	/**
	 *ログインしているかどうかを確認するメソッド.
	 * @param loginDto ログインに使用するユーザー情報を格納したDto
	 * @return ログイン成功:true ログイン失敗:false
	 */
	private boolean isLoggedIn(LoginDto loginDto) {
		
		// Sessionにユーザー情報が入っている、またisLoginフラグがtrueになっている場合はログインとみなされる
		return (loginDto != null && loginDto.isLogin);
	}

}
