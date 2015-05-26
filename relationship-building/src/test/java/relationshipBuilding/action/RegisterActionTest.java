/**
 * 
 */
package relationshipBuilding.action;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.seasar.struts.annotation.ActionForm;

import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.dto.EmployeesViewDto;
import relationshipBuilding.form.EmployeesForm;
import relationshipBuilding.service.CommonService;
import relationshipBuilding.service.ResisterEmployeeService;

/**
 * @author nagaimidori
 *
 */
public class RegisterActionTest {
	
	//社員情報を表示するためのDto(エンティティを格納するリストを作成する)
		public EmployeesViewDto employeesViewDto;
		@Resource
		//社員情報を表示するためのDto(エンティティを格納するリストを作成する)
		public EmployeeDto employeeDto;
		
		//社員情報を格納するフォーム
		@ActionForm
		@Resource
		public EmployeesForm employeesForm;

		//社員情報登録機能に関するサービスクラス
		@Resource
		protected ResisterEmployeeService resisterService;
		
		//社員情報登録機能に関するサービスクラス
		@Resource
		protected CommonService commonService;

	/**
	 * 社員情報の新規登録画面にアクセスした時に呼び出されるメソッドのテストメソッド.
	 */
	@Test
	public void testIndex() {
		
		// テスト対象のアクションをnew
        RegisterEmployeeAction test = new RegisterEmployeeAction();

        //戻り値のページ遷移先が登録画面になっていることを確認
        assertEquals("register.jsp", test.index());
	}

	/**
	 * 社員情報新の規登録画面の確認画面へボタンを押下した時に呼び出されるメソッドのテストメソッド.
	 */
	@Test
	public void testConfirm() {
		
		// テスト対象のアクションをnew
        RegisterEmployeeAction test = new RegisterEmployeeAction();
        
        //employeeFormを作成
        
        //employeeDtoを作成
        
        commonService.createEmployeeViewDto(employeesForm,employeeDto);
        
        //戻り値のページ遷移先が登録画面になっていることを確認
        assertEquals("confirm.jsp", test.confirm());
	}

	/**
	 * Test method for {@link relationshipBuilding.action.RegisterEmployeeAction#registerEmployee()}.
	 */
	@Test
	public void testRegisterEmployee() {
		
	}
}
