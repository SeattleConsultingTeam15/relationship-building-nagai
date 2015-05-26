
package relationshipBuilding.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.constants.RelationshipBuildingConstants;
import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.dto.EmployeeIdDto;
import relationshipBuilding.dto.EmployeesViewDto;
import relationshipBuilding.dto.SearchWordDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.ListForm;
import relationshipBuilding.service.CommonService;
import relationshipBuilding.service.ListService;

/**
 * 社員情報の一覧ページを制御するアクションクラス.
 * @author nagaimidori
 *
 */
public class ListEmployeeAction {
	
	//ページリンクを埋め込むための文字列(HTML)を格納する変数
    public String link;
    
    //検索結果が0の時のメッセージ
    public String noSearchMessage;

    //社員情報を表示するためのDto(エンティティを格納するリストを作成する)
	public EmployeesViewDto employeesViewDto;
    
    //Dtoを格納するリスト(表示用)
    public List<EmployeeDto> employeesList = new ArrayList<>();
	
	//社員のIDを格納するためのDto
	@Resource
	protected EmployeeIdDto employeeIdDto;
	
	//社員の検索ワードを格納するためのDto
	@Resource
	protected SearchWordDto searchWordDto;
	
    //一覧画面から入力した値を格納するフォーム
	@ActionForm
	@Resource
	protected ListForm listForm;
	
	//一覧情報機能に関するサービスクラス
	@Resource
	public ListService listService;
	
	//共通の機能に関するサービスクラス
	@Resource
	public CommonService commonService;
	
	//検索ワードを格納する変数
	public String searchWord;
	
	/**
	 * 一覧ページにアクセスした時に呼び出されるメソッド.
	 * @return ページングしている一覧ページの1ページ目
	 */
	@Execute(validator = false,redirect = true)
	public String index() {
		
		return "/listEmployee/paged-list/1";
	}
	
	/**
	 * 社員一覧ページをページングして一覧表示するメソッド.
	 * @return　社員情報が登録されている時:listアクション　社員情報が一つも登録されていない時:社員情報未登録ページ
	 */
	@Execute(validator = false, urlPattern= "paged-list/{pageNumber}")
    public String pagingPage() 
    		throws EmployeesStatusException {
		
		//urlから取得したページ番号を格納する変数
		String pageNumberFromUrl = listForm.pageNumber;
			
		//レコードの総件数
		int totalEmployees = (int) commonService.getAllActiveEmployeesCount(); //戻り値はlong型
		
		//10件に満たないあまりの件数
		int restPageNumber = totalEmployees % RelationshipBuildingConstants.EMPLOYEESLIST_PER_PAGE;
        
		//ページ総数を格納する変数にページ総数を格納する
		int pageNumber = listService.calculatePageNumber(totalEmployees,restPageNumber);
		
		//ページャー機能のリンク(HTML)を格納する
		link = listService.makePagingLink(Integer.parseInt(pageNumberFromUrl), pageNumber, "list");
		
		//ページごとに社員情報をセレクトし、Dto型のリストに格納する
		listService.selectEmployeesPerPage(pageNumberFromUrl, pageNumber, restPageNumber, employeesList);
		
		//社員情報の登録有無により次のページ遷移先を指定する
		String nextPass = listService.getNextPassConsiderResisterData(totalEmployees);
		
		return nextPass;
        
    }
	
	/**
	 * 社員情報を検索するメソッド.
	 * @return 検索結果を反映させた一覧ページを返します
	 */
	@Execute(validator = false)
//	@Execute(validator = false, urlPattern= "paged-list/{pageNumber} ? {searchName} = {searchValue} ")
	public String searchEmployees() throws EmployeesStatusException {

		//検索ワードを格納する変数
		searchWord = listForm.searchWords;
		
		//検索された社員情報を格納するリスト
		List<Employee> searchedEmployeeList = new ArrayList<>();
		
		//検索ワードを用いて条件に一致する社員情報をセレクトする
		searchedEmployeeList = listService.selectBySearchWord(searchWord);
		
		//検索結果を格納するリストからDtoを作成する
		commonService.createEmployeesDto(searchedEmployeeList, employeesList);

		
		//検索結果が0件だった時のメッセージを格納する
		noSearchMessage = listService.makeNoSearchEmployeeMessage(searchedEmployeeList.size());
		
		
		return "list.jsp";
		
	}
	
}