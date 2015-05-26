
package relationshipBuilding.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

/**
 * 社員情報をまとめるためのDtoクラス.
 * @author nagaimidori
 *
 */
@Component(instance = InstanceType.SESSION)
public class EmployeeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//社員の名前を格納する変数
	public String empName;

	//社員のフリガナを格納する変数
	public String empFrigana;

	//社員の誕生日を格納する変数
	public String birthday;

	//社員の電話番号を格納する変数
	public String telephoneNumber;

	//社員と話して得た情報を格納する変数
	public String empNote;

	//社員と話したか話していないかを判別する
	//0:話していない　1:話した
	public String status;

	//誕生日ー年
	public String birthdayYear;

	//誕生日ー月
	public String birthdayMonth;

	//誕生日ー日
	public String birthdayDay;

	//一覧ページから行を取得するために取得するId
	public Integer empId;
	
	//登録日を格納する変数
	public  Timestamp registrationDate;


}
