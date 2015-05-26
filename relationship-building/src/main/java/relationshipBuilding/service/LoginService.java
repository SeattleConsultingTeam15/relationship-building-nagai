package relationshipBuilding.service;

import java.util.List;
import javax.annotation.Resource;
import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.SimpleWhere;
import relationshipBuilding.constants.RelationshipBuildingConstants;
import relationshipBuilding.dto.LoginDto;
import relationshipBuilding.entity.Login;
import relationshipBuilding.form.LoginForm;

/**
 * ログイン画面のロジックを指定するクラス
 * @author nagaimidori
 *
 */
public class LoginService extends S2AbstractService<Login> {
	
	
	/**
	 * セッションに保持されているデータを格納するDto
	 */
	@Resource
	protected LoginDto loginDto;
	
	/**
	 * ログインフォームから取得した値をもとに、ログイン情報を格納するDtoを作成するメソッド.
	 * @param loginDto　ログイン情報を格納するDto
	 * @param loginForm　ログインフォームから取得された値を格納するForm
	 */
	public void createLoginDto(LoginDto loginDto,LoginForm loginForm) {

		//formの値をDtoに格納する
		loginDto.userName = loginForm.userName;
		loginDto.password = loginForm.password;
	}
	
	public String setLoginErrorMessage(int loginStatus) {
		
		String loginErrorMessage = "";
		//ログイン状態にあるとき、ログイン名とユーザー名が一致していない場合、エラー処理をする
		if(loginDto.loginStatus == RelationshipBuildingConstants.LOGIN_FAILED) {
			loginErrorMessage = "ユーザー名とIDが一致していません";
		}
		return loginErrorMessage;
	}
 	
	/**
	 * ユーザー名とID名が一致しているかどうか調べるメソッド.
	 * @param loginDto ログインフォームから入力された値
	 * @return ログイン成功/失敗 0:失敗 1:成功
	 */
	public int checkLoginStatus(LoginDto loginDto) {
		
		//次に遷移するjspの値を格納する文字列
		int isLogin;
	
		//ユーザー名がそもそも入力されているかチェック
		if(this.findLoginDataByName(loginDto.userName).size()== 0 ||this.findLoginDataByName(loginDto.userName).size() == 0){
		
			isLogin = RelationshipBuildingConstants.LOGIN_NULL; //ログイン未入力
			
		} else {
			
			String insertedPassword = this.findLoginDataByName(loginDto.userName).get(0).password;
			
			//ユーザー名が存在した場合、パスワードは合っているのかチェック
			if(insertedPassword.equals(loginDto.password)) {
				
				//合っていた場合：ホーム画面に遷移
				isLogin = RelationshipBuildingConstants.LOGIN_SUCCESS; //ログイン成功
				
			} else {
				
				//合っていなかった場合：もう一度ログイン画面に遷移
				isLogin = RelationshipBuildingConstants.LOGIN_FAILED; //ログイン失敗
			}
			
		}
		
		return isLogin;
	}
	
	
	/**
	 * ログインに成功した時に次の画面遷移先を指定するメソッド.
	 * @param loginStatus　ログイン状態(true:ログイン中 false:ログアウト中)
	 * @return　次の画面遷移先
	 */
	public String getLoginNextPass(int loginStatus) {
		
		String nextJsp;
		
		// ログイン成功した場合
		if(loginStatus == 1) {

			// 遷移先判定
			nextJsp = "/top/";

			// セッションの初期化（セキュリティ上必要）
			//resetLoginSession();

			loginDto.isLogin = true;

		// ログインエラー　未入力
		} else {
			
			nextJsp = "/login/";

		}
		return nextJsp;
	}
	
	/**
	 * ログインIDが一致するユーザー情報を取得するメソッド.
	 * @param userName ユーザーが入力しがユーザー名
	 * @return　条件に一致するエンティティが格納されたリスト
	 */
	public List<Login> findLoginDataByName(String userName) {
		
		//社員情報テーブルからユーザー名が一致しているカラムをセレクトする
		return select().where(new SimpleWhere().eq("userName", userName))
						.getResultList();
	}
	
}
