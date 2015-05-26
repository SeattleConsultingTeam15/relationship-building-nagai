
package relationshipBuilding.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import relationshipBuilding.entity.Employee;
import relationshipBuilding.form.EmployeesForm;


/**
 * Dtoクラス.
 * @author nagaimidori
 *
 */

public class EmployeesViewDto {
	
	//Dtoを格納するリスト(表示用)
	public List<EmployeesDto> employeesList = new ArrayList<>();
	
	/**
	 * 社員情報をまとめて扱うためのDto
	 * @author nagaimidori
	 *
	 */
	public static class EmployeesDto {
		
		/**
		 * フォームから入力した値をまとめるためのコンストラクタ.
		 * @param employeesForm フォームから入力された値が格納されるクラス.
		 */
		public EmployeesDto(EmployeesForm employeesForm) {
			
			this.selectId = employeesForm.selectId;
			this.empName = employeesForm.empName;
			this.empFrigana = employeesForm.empFrigana;
			this.birthday = employeesForm.birthdayYear + "/" + employeesForm.birthdayMonth + "/" + employeesForm.birthdayDay;
			this.telephoneNumber = employeesForm.telephoneNumber;
			this.empNote = employeesForm.empNote;
			this.status = employeesForm.status;

		}
		
		/**
		 * エンティティをまとめるためのコンストラクタ.
		 * @param entity　エンティティが格納されるクラス.
		 */
		public EmployeesDto(Employee entity) {

			this.empName = entity.empName;
			this.empFrigana = entity.empFrigana;
			this.status = convertStatusIntToString(entity.status);
			this.birthday = entity.birthday;
			this.telephoneNumber = entity.telephoneNumber;
			this.empNote = entity.note;	
			this.selectId = entity.empno;
			this.registrationDate = entity.registrationDate;
			this.updateDate = entity.updateDate;
			
		}
		
		/**
		 * エンティティでは数値で格納されているステータスを日本語に変換するメソッド.
		 * @param entityStatus
		 * @return 日本語かされたステータス
		 */
		public String convertStatusIntToString(int entityStatus) {
			
			//どちらにも属さなかった場合はエラー
			String strStatus = "error";
			
			if(entityStatus == 0) {
				strStatus = "話していない";
			} else if(entityStatus == 1) {
				strStatus = "話した";
			}
			
			return strStatus; 
		}
		
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
		
		//一覧ページから行を取得するために取得するId
		public Integer selectId;
		
		//登録日時を格納する変数
		public Date registrationDate;
		
		//更新日時を格納する変数
		public Date updateDate;
	}

}
