
package relationshipBuilding.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import relationshipBuilding.constants.RelationshipBuildingConstants;
import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.dto.EmployeeIdDto;
import relationshipBuilding.dto.EmployeesViewDto;
import relationshipBuilding.dto.SearchWordDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;
import relationshipBuilding.form.SearchForm;
import relationshipBuilding.service.CommonService;
import relationshipBuilding.service.ListService;

/**
 * 社員情報の一覧ページを制御するアクションクラス.
 * @author nagaimidori
 *
 */
public class SearchEmployeeAction {
	
	//ページリンクを埋め込むための文字列(HTML)を格納する変数
    public String link;
    
    //検索結果が0の時のメッセージ
    public String noSearchMessage;

    //社員情報を表示するためのDto(エンティティを格納するリストを作成する)
	public EmployeesViewDto employeesViewDto;
    
    //Dtoを格納するリスト(表示用)
    public List<EmployeeDto> employeesList = new ArrayList<>();
	
    //エンティティを格納するリスト(表示用)
    public List<Employee> employeeEntityList = new ArrayList<>();

	//社員のIDを格納するためのDto
	@Resource
	protected EmployeeIdDto employeeIdDto;
	
	//社員の検索ワードを格納するためのDto
	@Resource
	protected SearchWordDto searchWordDto;
	
    //一覧画面から入力した値を格納するフォーム
	@ActionForm
	@Resource
	protected SearchForm searchForm;
	
	//前のページの有無
	public boolean hashNext = false;
	
	//次のページの有無
	public boolean hashPrev = false;
	
	//社員情報の全件数
	public long totalEmployeesNumber;
	
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
	@Execute(validator = false)
	public String index() throws EmployeesStatusException {
		
		int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);
		
		//社員数の全件数を取得
		totalEmployeesNumber = commonService.getAllActiveEmployeesCount();
		
		//ページごとの社員情報を取得
		listService.selectEmployeesListPerPage(page,employeeEntityList);
		
		commonService.createEmployeesDto(employeeEntityList,employeesList);
		
		//前のページがあるかどうかを調べる
		if(page != 0) {
			hashPrev = true;
		}
		
		//次のページがあるかどうかを調べる
		if((page + 1) * RelationshipBuildingConstants.EMPLOYEESLIST_PER_PAGE < totalEmployeesNumber) {
			hashNext = true;
		}
		
		
		return "search.jsp";
	}
	
	
	/**
	 * 社員情報の一覧ページの更新するボタンを押下した時に呼び出されるメソッド.
	 * @return 社員情報更新機能を制御するアクションクラスを呼び出します
	 */
	@Execute(validator = false, redirect = true) 
	public String goUpdateForm() {
		
		//選択された社員のIDを格納する変数
		String selectId = searchForm.selectId;
		
		//選択した社員のIDをDtoに格納する
		employeeIdDto.selectId = Integer.parseInt(selectId);
		
		//社員情報を更新するクラスの呼び出し
		return "/update/";
	}
	/**
	 * 社員情報の一覧ページの削除ボタンを押下した時に呼び出されるメソッド.
	 * @return　社員情報削除機能を制御するアクションクラスを呼び出します
	 */
	@Execute(validator = false, redirect = true) 
	public String deleteConfirm() {
		
		//選択された社員のIDを格納する変数
		String selectId = searchForm.selectId;
		
		//選択した社員のIDをDtoに格納する
		employeeIdDto.selectId = Integer.parseInt(selectId);
		
		//社員情報を削除するクラスの呼び出し
		return "/delete/";
	}
	
	/**
	 * 一覧ページの詳細ページボタンを押したときに呼び出されるメソッド.
	 * @return 詳細ページを返します
	 */
	@Execute(validator = false, redirect = true) 
	public String goDescribeForm() {
		
		//選択された社員のIDを格納する変数
		String selectId = searchForm.selectId;
		
		//選択した社員のIDをDtoに格納する
		employeeIdDto.selectId = Integer.parseInt(selectId);
		
		//社員情報を削除するクラスの呼び出し
		return "/describe/";
	}
	
	/**
	 * 社員情報を検索するメソッド.
	 * @return 検索結果を反映させた一覧ページを返します
	 */
	@Execute(validator = false)
	public String searchEmployees() throws EmployeesStatusException {
		
//		searchWordDto.searchWords = listForm.searchWords;
//		
//		return "/search/";
		//検索ワードを格納する変数
		searchWord = searchForm.searchWords;
		
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