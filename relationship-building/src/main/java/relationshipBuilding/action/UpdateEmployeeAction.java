
package relationshipBuilding.action;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.UpdateForm;
import relationshipBuilding.service.CommonService;
import relationshipBuilding.service.UpdateEmployeeService;

/**
 * 社員情報の更新機能を制御するアクションクラス.
 * @author nagaimidori
 *
 */
public class UpdateEmployeeAction {
		
	//社員情報をまとめるためのDto
	@Resource
	public EmployeeDto employeeDto;
	
	//社員情報を格納するフォーム
	@ActionForm
	@Resource
	public UpdateForm updateForm;
	
	//社員情報の更新機能に関するサービスクラス
	@Resource
	protected UpdateEmployeeService updateService;
	
	//共通の機能に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	public Timestamp registrateDate;
	
	/**
	 * 社員情報の更新機能を制御するアクションクラスが呼び出された時に実行されるメソッド.
	 * @return　社員情報の更新画面を返します
	 */
	@Execute(validator = false)
	public String index() throws EmployeesStatusException {
		
		//選択された社員の情報を社員IDを利用して取得する
		Employee employee  = commonService.findEmployeeById(updateForm.selectId);
		
		employeeDto.empId = updateForm.selectId;
		
		commonService.overWriteFormByEntity(updateForm, employee);
		
		return "updateForm.jsp";
	}
	
	/**
	 * 社員情報の更新用フォームの更新確認ボタンを押下した時に呼び出されるメソッド.
	 * @return 社員情報の更新確認画面を返します
	 */
	@Execute(validator = true , input = "updateForm.jsp") 
	public String updateConfirm() {
		int empId = employeeDto.empId;
		commonService.createEmployeeViewDto(updateForm,employeeDto);
		employeeDto.empId = empId;
		return "updateConfirm.jsp";
	}

	/**
	 * 社員情報の更新確認画面の更新ボタンを押下した時に呼び出されるメソッド.
	 * @return　社員情報の更新完了画面を返します
	 */
	@RemoveSession(name = {"employeeDto","employeeIdDto"})
	@Execute(validator = false) 
	public String update() throws EmployeesStatusException {
		
		if(employeeDto.empName == null ||employeeDto.empName =="") {
			
			return "/pleaseRetry/";
		} else {
			
			//登録時間を変数に格納します
			Employee employee = commonService.findEmployeeById(employeeDto.empId);
			
			registrateDate = employee.registrationDate;
			
			//指定した行のデータをIdで指定して更新します
			updateService.updateEmployeeById(employeeDto, employeeDto.empId,registrateDate);

			return "updateDone.jsp";
		}
	}

}