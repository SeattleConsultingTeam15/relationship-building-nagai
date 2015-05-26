
package relationshipBuilding.form;


import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Required;

/**
 * ログインフォームに入力された値を格納するクラス.
 * @author nagaimidori
 *
 */
public class LoginForm {
	
	
	//ログイン情報に登録されているユーザー名を格納する変数
	@Required (arg0 = @Arg(key = "ユーザー名", resource = false, position = 1)) //必須項目
	@Mask(mask = "^[a-zA-Z0-9]+$", 
	arg0 = @Arg(key = "ユーザー名(半角英数字)", resource = false, position = 1))//半角英数字
	public String userName;
	
	 // ログイン情報に登録されているパスワードを格納する変数
	@Required (arg0 = @Arg(key = "パスワード", resource = false, position = 1)) //必須項目
	@Mask(mask = "^[a-zA-Z0-9]+$", 
	arg0 = @Arg(key = "パスワード(半角英数字)", resource = false, position = 1))//半角英数字
	public String password;

}
