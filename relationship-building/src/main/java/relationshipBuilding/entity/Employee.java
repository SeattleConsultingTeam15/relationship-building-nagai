
package relationshipBuilding.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * employeeテーブルのエンティティクラス.
 * @author nagaimidori
 *
 */
@Entity
@Table(name = "employees")
public class Employee {

	/**
	 * 社員カラムの通し番号
	 */
	@Id //主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) //データーベース固有の識別子
	public Integer empno;
	/**
	 * 社員の名前
	 */
	@Column(name = "emp_name")
	public String empName;
	/**
	 * 社員の名前のふりがな
	 */
	@Column(name = "emp_frigana")
	public String empFrigana;
	/**
	 * 社員の誕生日
	 */
	@Column(name = "birthday")
	public String birthday;
	/**
	 * 社員の電話番号
	 */
	@Column(name = "telephone_number")
	public String telephoneNumber;
	/**
	 * 社員情報(話をして仕入れた情報)
	 */
	@Column(name = "note")
	public String note;
	/**
	 * 社員情報の削除に関するフラグ
	 * 0:削除されていない
	 * 1:削除されている
	 */
	@Column(name = "dlete_flag")
	public Integer dleteFlag;
	/**
	 * 社員と話しているか話していないかを表す
	 * 1:話している
	 * 0:話していない
	 */
	@Column(name = "status")
	public Integer status;
	
	/**
	 * 社員情報を登録した日
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "registrate_date")
	public Timestamp registrationDate;
	
	/**
	 * 社員情報を更新した日
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	public Timestamp updateDate;

}
