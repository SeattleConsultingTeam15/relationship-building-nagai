
package relationshipBuilding.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.dto.EmployeeIdDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.DescribeForm;
import relationshipBuilding.service.CommonService;

/**
 * 社員情報の詳細ページを制御するクラス.
 * @author nagaimidori
 *
 */
public class DescribeEmployeeAction {
	
	//社員情報を表示するためのDto(エンティティを格納するリストを作成する)
	public EmployeeDto employeeDto;
	
	//社員情報を格納するフォーム
	@ActionForm
	@Resource
	public DescribeForm describeForm;
	
	//共通の機能に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	//社員情報のIdを格納したDto
	public EmployeeIdDto employeeIdDto;
	
	/**
	 * 一覧画面の詳細ボタンを押したときに呼び出されるメソッド.
	 * @return　詳細画面のjsp
	 */
	@Execute(validator = false)
	public String index() throws EmployeesStatusException {
		
		//選択された社員情報を社員IDを利用してセレクトする
		Employee employee = commonService.findEmployeeById(describeForm.selectId);
		
		//選択した社員のIDを用いてDBから社員情報を取ってくる→取ってきたものをDtoでまとめる
		commonService.createEmployeeViewDto(employeeDto, employee);
		
		return "describe.jsp";
	}
	
}
