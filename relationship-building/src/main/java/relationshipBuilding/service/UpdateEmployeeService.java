package relationshipBuilding.service;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.service.S2AbstractService;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;

/**
 * 人間関係構築アプリのロジックを指定するクラス.
 * @author nagaimidori
 *
 */
public class UpdateEmployeeService extends S2AbstractService<Employee> {
	
	//共通の機能に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	/**
	 * 一覧ページの表における指定された行を更新するメソッド.
	 * @param employeesList　指定された行における社員情報を格納したエンティティが格納されているリスト
	 */
	public void updateEmployeeById(EmployeeDto employeeDto, Integer selectId,Timestamp registrateDate) 
			throws EmployeesStatusException {

		//現在のタイムスタンプを格納する変数
		Timestamp now = new Timestamp(System.currentTimeMillis());

		Employee employee = new Employee();
		
		employee.empno = selectId;
		employee.empName = employeeDto.empName;
		employee.empFrigana = employeeDto.empFrigana;
		employee.birthday = 
				employeeDto.birthdayYear + "/" + employeeDto.birthdayMonth + "/" + employeeDto.birthdayDay;
		employee.telephoneNumber = employeeDto.telephoneNumber;
		employee.status = commonService.
				convertEmployeesStatusStringToInt(employeeDto.status);
		employee.updateDate = now;
		employee.dleteFlag = 0;
		employee.registrationDate = registrateDate;
		
		update(employee);
	}

}
	
