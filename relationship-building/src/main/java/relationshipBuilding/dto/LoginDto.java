
package relationshipBuilding.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;


/**
 * ユーザー情報をセッションに格納するDtoクラス.
 * @author nagaimidori
 *
 */
@Component(instance = InstanceType.SESSION)
public class LoginDto implements Serializable {
	
	//セッション上に同じ名前のDtoオブジェクトがたくさんできるため見分けをつけるためシリアライズする
	private static final long serialVersionUID = 1L;
	
	/** ユーザID */
	public String userId;

	/** ユーザ名 */
	public String userName;

	/** ログインフラグ */
	public boolean isLogin = false;
	
	/** ログイン情報に登録されているパスワードを格納する変数*/
	public String password;
	
	/**ログイン状態を表すステータス 2 ログイン未記入 0 ログイン失敗 1ログイン中*/
	public int loginStatus;
}
