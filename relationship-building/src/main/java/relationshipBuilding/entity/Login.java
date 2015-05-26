
package relationshipBuilding.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * loginテーブルのエンティティクラス.
 * @author nagaimidori
 *
 */
@Entity
@Table(name = "login")
public class Login {

	public static final String TABLE = "login";
	
	/**
	 * ログイン情報に登録されているユーザー名を格納する変数
	 */
	@Column(name = "user_name")
	public String userName;
	
	/**
	 * ログイン情報に登録されているパスワードを格納する変数
	 */
	public String password;
	
	/**
	 * ログイン情報を論理削除するのに用いるデリートフラグを格納する変数
	 */
	@Column(name = "delete_flag")
	public Integer deleteFlag;
	
	/**
	 * ログイン情報を登録した日付を格納する変数
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "registration_date")
	public Date registrationDate;
	
	/**
	 * ログイン情報を更新した日付を格納する変数
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	public Date updateDate;
	
}
