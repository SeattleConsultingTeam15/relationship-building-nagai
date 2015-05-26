
package relationshipBuilding.form;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxbytelength;
import org.seasar.struts.annotation.Required;

/**
 * 社員登録フォームから入力された値を格納するクラス.
 * @author nagaimidori
 *
 */

public class EmployeesForm {

	//社員の名前を格納する変数
	@Required(arg0 = @Arg(key = "名前", resource = false, position = 1)) //必須項目
	@Maxbytelength(maxbytelength = 200, arg0 = @Arg(key = "名前", resource = false, position = 1),
	arg1 = @Arg(key = "25文字", resource = false, position = 1))//25文字以内
	@Mask(mask = "^[^ -~｡-ﾟ]+$", arg0 = @Arg(key = "名前(全角)", resource = false, position = 1))//全角
	public String empName;

	//社員のフリガナを格納する変数
	@Maxbytelength(maxbytelength = 200, arg0 = @Arg(key = "フリガナ", resource = false, position = 1)) //25文字以内
	@Mask(mask = "^[ァ-ヶ]+$", arg0 = @Arg(key = "フリガナ(全角カタカナ)", resource = false, position = 1)) //全角カタカナ
	public String empFrigana;

	//社員の電話番号を格納する変数^[0-9\-]+$
	@Mask(mask = "^[0-9-]+$",  
			arg0 = @Arg(key = "例)xxx-xxxx-xxxx", 
			resource = false, position = 1))

	public String telephoneNumber;

	//社員と話して得た情報を格納する変数
	@Maxbytelength(maxbytelength = 1600,arg0 = @Arg(key = "note", resource = false, position = 1),
			arg1 = @Arg(key = "200文字", resource = false, position = 1)) //200文字以内
	public String empNote;

	@Required(arg0 = @Arg(key = "「この社員さんと話したか」", resource = false, position = 1)) //必須項目
	//社員と話したか話していないかを判別する
	//話した、話していないの2パターンが用意されている
	public String status;

	//誕生日ー年
	public String birthdayYear;

	//誕生日ー月
	public String birthdayMonth;

	//誕生日ー日
	public String birthdayDay;

	//誕生日を格納する変数
	public String birthday;

	//一覧ページにて指定した行を取り出すために取得するIdを格納する変数
	public Integer selectId;
}
