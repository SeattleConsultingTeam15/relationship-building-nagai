
package relationshipBuilding.action;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.DeleteForm;
import relationshipBuilding.service.CommonService;
import relationshipBuilding.service.DeleteEmployeeService;

/**
 * 社員情報の削除機能を制御するアクションクラス.
 * @author nagaimidori
 *
 */
public class DeleteEmployeeAction {
	
	
	//社員情報をまとめるためのDto
	//セッション管理しています
	public EmployeeDto employeeDto;

	//社員情報の削除機能に関するサービスクラス
	@Resource
	protected DeleteEmployeeService deleteService;

	//共通の機能に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	@ActionForm
	@Resource
	protected DeleteForm deleteForm;

	/**
	 * 社員情報の削除機能を制御するアクションクラスが呼び出された時に実行されるメソッド.
	 * @return　社員情報の削除確認画面を返します
	 */
	@Execute(validator = false)
	public String index() throws EmployeesStatusException{
		
		//選択された社員の情報をIDを元に取得する
		Employee employee = commonService.findEmployeeById(deleteForm.selectId);
		
		//社員IDを用いて選択した社員情報からDtoを作成するメソッド.
		commonService.createEmployeeViewDto(employeeDto,employee);
		
		return "deleteConfirm.jsp";
	}

	/**
	 * 社員情報の削除確認画面の削除ボタンを押下した時に呼び出されるメソッド.
	 * @return 社員情報の削除完了画面を返します
	 */
	@RemoveSession(name = "employeeDto")
	@Execute(validator = false) 
	public String delete() {
		
		if(employeeDto.empName == null) {
			
			return "/pleaseRetry/";
			
		} else {
			//選択された社員の情報をIDを元に取得する
			Employee employee = commonService.findEmployeeById(employeeDto.empId);
			//選択した行のデータを社員のIdで指定して削除します
			deleteService.deleteEmployeeById(employee);
		
		return "deleteDone.jsp";
		}
	}
	

}