package relationshipBuilding.service;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.SimpleWhere;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.dto.EmployeesViewDto;
import relationshipBuilding.dto.EmployeesViewDto.EmployeesDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.EmployeesForm;
import relationshipBuilding.form.UpdateForm;

/**
 * 人間関係構築アプリの共通ロジックをまとめたクラス.
 * @author nagaimidori
 *
 */
public class CommonService extends S2AbstractService<Employee> {

	
	/**
	 * リストの中身の順番を逆にするメソッド.
	 * @param employeesList 順番を逆にしたいリスト
	 * @return 順番を逆にしたメソッド.
	 */
	public List<Employee> reverseEmployeesList(List<Employee> employeesList) {
		
		List<Employee> reverceEmployeesList = new ArrayList<Employee>();
		
		for(int i = employeesList.size() -1 ; 0 <= i ; i--) {
			reverceEmployeesList.add(employeesList.get(i));
		}
		
		return reverceEmployeesList;
	}
	
	/**
	 * フォームにエンティティの値をセットするメソッド.
	 * @param updateForm　更新用のアクションフォーム
	 * @param employee　社員情報のエンティティ
	 */
	public void overWriteFormByEntity(UpdateForm updateForm, Employee employee) 
			throws EmployeesStatusException {
		
		updateForm.empName = employee.empName;
		updateForm.empFrigana = employee.empFrigana;
		updateForm.birthday = employee.birthday;
		updateForm.telephoneNumber = employee.telephoneNumber;
		updateForm.empNote = employee.note;
		updateForm.status = convertEmployeesStatusIntToString(employee.status);
		updateForm.birthdayYear = splitBirthday(employee.birthday)[0];
		updateForm.birthdayMonth = splitBirthday(employee.birthday)[1];
		updateForm.birthdayDay = splitBirthday(employee.birthday)[2];
	}
	
	/**
	 * フォームから入力された値をまとめたDtoを作成するクラス.
	 * @param employeesForm フォームから入力された値が格納されているクラス
	 * @return フォームから入力された値が格納されたDtoを格納したリスト
	 */
	public EmployeesViewDto createEmployeesDtoFromForm(EmployeesForm employeesForm) {
		
		//表示用のDto
		EmployeesViewDto employeesViewDto = new  EmployeesViewDto();
		
		//フォームで年・月・日に分かれて取得される誕生日を一つにまとめる
		employeesForm.birthday = 
				employeesForm.birthdayYear + "/" + employeesForm.birthdayMonth + "/" + employeesForm.birthdayDay;
    	
		//フォームから取得された値からDtoを作成する
		EmployeesDto employeesDisp = new EmployeesDto(employeesForm);
		
		//表示用のDtoに先ほど作成したDtoを追加する
		employeesViewDto.employeesList.add(employeesDisp);
		
		return employeesViewDto;
	}
	
	/**
	 * 社員情報のステータス(話したか話していないか)を数字に変換するメソッド.
	 * @param strStatus　文字列で表された社員情報のステータス
	 * @return 数字に変換された社員情報のステータス
	 */
	public int convertEmployeesStatusStringToInt(String strStatus) 
			throws EmployeesStatusException{
		
		//ステータスのデフォルトは「話していない」
		int intStatus = 0;
		
		//0:話していない 1:話したに変換
		if(strStatus.equals("話していない")) {
			intStatus = 0;
		} else if(strStatus.equals("話した")) {
			intStatus = 1;
		} else {
			throw new EmployeesStatusException("不正なステータスです");
		}
		
		return intStatus;
	}
	/**
	 * 社員情報のステータス(話したか話していないか)を文字列に変換するメソッド.
	 * @param intStatus　数値で表された社員情報のステータス
	 * @return　文字列に変換された社員情報のステータス
	 */
	public String convertEmployeesStatusIntToString(int intStatus) 
			throws EmployeesStatusException{

		//ステータスのデフォルトは「話していない」
		String strStatus = "話していない";
		
		//0:話していない 1:話したに変換
		if(intStatus == 0) {
			strStatus = "話していない";
		} else if(intStatus == 1) {
			strStatus = "話した";
		} else {
			throw new EmployeesStatusException("不正なステータスです");
		}

		return strStatus;
	}
	
	/**
	 * 指定したIdをもつエンティティをセレクトするメソッド.
	 * @param id ユーザーが指定したId
	 * @return　条件に一致するエンティティが格納されたリスト
	 */
	public List<Employee> findEmployeesById(Integer id) {
		
		
		return select().where(new SimpleWhere().eq("empno", id))
						.getResultList();
	}
	
	/**
	 * 指定したIdをもつエンティティをセレクトするメソッド.
	 * @param id ユーザーが指定したId
	 * @return　条件に一致するエンティティが格納されたリスト
	 */

	public Employee findEmployeeById(Integer employeeId) {

		return  select().where(new SimpleWhere().eq("empno", employeeId))
				.getSingleResult();
	}
	
	/**
	 * リストに格納されているエンティティをDtoに変換し、Dtoのリストに格納するメソッド.
	 * @param employees エンティティが格納されたリスト
	 * @param employeesDtoList エンティティをDtoに変換したものを格納したリ
	 * スト
	 */
	public void createEmployeesDto(List<Employee> employees, List<EmployeeDto> employeesDtoList) 
			throws EmployeesStatusException {
		
		for (Employee employee : employees) {
			EmployeeDto employeeDto = new EmployeeDto();
			createEmployeeViewDto(employeeDto,employee);
			employeesDtoList.add(employeeDto);
		}

	}
	/**
	 * Dtoを格納したリストを作成するメソッド.
	 * @param employees 社員情報を格納するエンティティが格納されたリスト
	 * @return　作成したDtoを格納したリスト
	 */
	public EmployeesViewDto createEmployeesDto(List<Employee> employees) {
		
		EmployeesViewDto employeesViewDto = new EmployeesViewDto();
		
		for (Employee employee : employees) {		
			EmployeesDto employeesDisp = new EmployeesDto(employee);
			employeesViewDto.employeesList.add(employeesDisp);
		}
		
		return employeesViewDto;
	}
	
	/**
	 * エンティティの値が格納されたDtoを作成するメソッド.
	 * @param employeeDto 社員情報が格納されたDto
	 * @param employees 社員情報に関するエンティティ
	 */
	public void createEmployeeViewDto(EmployeeDto employeeDto, Employee employees) 
			throws EmployeesStatusException {

		//エンティティの値をDtoにセットします
		employeeDto.empName = employees.empName;
		employeeDto.empFrigana = employees.empFrigana;
		employeeDto.birthday = employees.birthday;
		employeeDto.telephoneNumber = employees.telephoneNumber;
		employeeDto.birthdayYear = splitBirthday(employees.birthday)[0];
		employeeDto.birthdayMonth = splitBirthday(employees.birthday)[1];
		employeeDto.birthdayDay = splitBirthday(employees.birthday)[2];
		employeeDto.empNote = employees.note;
		employeeDto.status = convertEmployeesStatusIntToString(employees.status);
		employeeDto.empId = employees.empno;
		employeeDto.registrationDate = employees.registrationDate;
		
	}
	
	/**
	 * フォームにセットされた値をまとめた社員情報Dtoを作成するメソッド.
	 * @param employeesForm フォームに格納された値
	 * @return 作成されたDto
	 */
	public void createEmployeeViewDto(EmployeesForm employeesForm,EmployeeDto employeeDto) {
		
		//エンティティの値をDtoにセットします
		employeeDto.empName = employeesForm.empName;
		employeeDto.empFrigana = employeesForm.empFrigana;
		employeeDto.birthdayYear = employeesForm.birthdayYear;
		employeeDto.birthdayMonth = employeesForm.birthdayMonth;
		employeeDto.birthdayDay = employeesForm.birthdayDay;
		employeeDto.telephoneNumber = employeesForm.telephoneNumber;
		employeeDto.empNote = employeesForm.empNote;
		employeeDto.status = employeesForm.status;
	}
	
	/**
	 * フォームにセットされた値をまとめた社員情報Dtoを作成するメソッド.
	 * @param employeesForm フォームに格納された値
	 * @return 作成されたDto
	 */
	public void createEmployeeViewDto(UpdateForm updateForm,EmployeeDto employeeDto) {
		
		//エンティティの値をDtoにセットします
		employeeDto.empName = updateForm.empName;
		employeeDto.empFrigana = updateForm.empFrigana;
		employeeDto.birthdayYear = updateForm.birthdayYear;
		employeeDto.birthdayMonth = updateForm.birthdayMonth;
		employeeDto.birthdayDay = updateForm.birthdayDay;
		employeeDto.telephoneNumber = updateForm.telephoneNumber;
		employeeDto.empNote = updateForm.empNote;
		employeeDto.status = updateForm.status;
		employeeDto.empId = updateForm.selectId;
		
	}
	
	/**
	 * 全てのカラムの数を数えるメソッド.
	 * @return 全てのカラムの数
	 */
	public long getAllActiveEmployeesCount() {
		return select().where(new SimpleWhere().eq("dleteFlag", 0)).getCount();
	}
	

	/**
	 * 誕生日のセレクトボックスの値を引き継ぐためにentityに保存されている日付を年月日で区切るメソッド.
	 * @param birthday
	 * @return 日付を年月日で区切ったものを格納した配列
	 */
	public String[] splitBirthday(String birthday) {
			
		String str = birthday;
		
		//社員情報を"/"で分割することで年月日で分割する
		String[] convertBirtyday = str.split("/", 0);
		
		return convertBirtyday;
	}
	
	
}
