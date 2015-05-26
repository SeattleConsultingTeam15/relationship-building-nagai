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
public class ResisterEmployeeService extends S2AbstractService<Employee> {
	
	//共通の処理に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	/**
	 * Dtoに格納された値をデータベースに挿入するメソッド.
	 * @param employeeDto 挿入したいデータを格納したDto
	 * @return データーベースを更新した行数
	 */
	public int insertEmployee(EmployeeDto employeeDto) throws EmployeesStatusException {
		
		Employee entity = new Employee();
		
		//現在のタイムスタンプを格納する変数
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		//エンティティの中にDtoに格納されていた値をセットする
		entity.empName= employeeDto.empName;
		entity.empFrigana = employeeDto.empFrigana;
		entity.birthday = 
				employeeDto.birthdayYear + "/" + employeeDto.birthdayMonth + "/" + employeeDto.birthdayDay;
		entity.telephoneNumber = employeeDto.telephoneNumber;
	    entity.note = employeeDto.empNote;
	    entity.status = commonService.convertEmployeesStatusStringToInt(employeeDto.status);
	    entity.dleteFlag = 0;
	    entity.updateDate = now;
	    entity.registrationDate = now;
		return insert(entity);
		
	}
	
}
