package relationshipBuilding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.ComplexWhere;

import relationshipBuilding.constants.RelationshipBuildingConstants;
import relationshipBuilding.dto.EmployeeDto;
import relationshipBuilding.entity.Employee;
import relationshipBuilding.exception.EmployeesStatusException;


/**
 * 人間関係構築アプリのロジックを指定するクラス.
 * @author nagaimidori
 */
public class ListService extends S2AbstractService<Employee> {
	
	//共通の機能に関するサービスクラス
	@Resource
	protected CommonService commonService;
	
	/**
	 * 登録データ数に応じて次のページ遷移先を選択するメソッド.
	 * @param allActiveEmployeesCount
	 * @return
	 */
	public String getNextPassConsiderResisterData(long allActiveEmployeesCount) {
		
		//次の遷移先を格納する変数
		String nextPass;
		
		//表示するデータがある場合とない場合で次のページ遷移先を指定する
		if(allActiveEmployeesCount == 0) {
			nextPass =  "noDataList.jsp";
		} else {
			//一ページ目を返す
			nextPass =  "list.jsp";
		}
		
		return nextPass;
	}
	
	/**
	 * 検索結果が0の場合のにメッセージを返すメソッド.
	 * @param searchEmployeesLength
	 * @return 検索結果が0件の時に表示するメッセージ
	 */
	public String makeNoSearchEmployeeMessage(int searchEmployeesLength) {
		
		//検索結果が0件の場合、検索結果があった場合に応じて次のページ遷移先を決める
		//検索結果が0件の場合→検索結果0のページへ
		if(searchEmployeesLength == 0) {
			return "検索条件に一致する社員情報は0件です";
		} else {
			return "";
		}
	}
	
	/**
	 * 総ページ数を計算するメソッド.
	 * @param totalDataNumber　全データ数を格納する変数
	 * @param restDataPerPage　全データをページごとのデータ数で割ったときのあまりを格納する変数
	 * @return
	 */
	public int calculatePageNumber(int totalDataNumber, int restDataPerPage) {
		//ページ数を格納する変数
		int pageNumber;
		
		//全データ数をページごとのデータ数で割ったときのあまりによって総ページ数を計算する
		if(restDataPerPage > 0) {
			pageNumber = totalDataNumber / RelationshipBuildingConstants.EMPLOYEESLIST_PER_PAGE + 1;
		} else {
			pageNumber = totalDataNumber / RelationshipBuildingConstants.EMPLOYEESLIST_PER_PAGE;
		}
		
		return pageNumber;
	}

	public void  selectEmployeesListPerPage (int pageNumber,List<Employee> employeesList) {
		
		employeesList = jdbcManager.from(Employee.class)
				.orderBy("empno").limit(RelationshipBuildingConstants.EMPLOYEESLIST_PER_PAGE)
				.offset(pageNumber * RelationshipBuildingConstants.EMPLOYEESLIST_PER_PAGE)
				.getResultList();

		
		
	}
	
	/**
	 * ページングのために10件ずつ社員情報をセレクトするメソッド.
	 * @param pageNo ページ番号
	 * @return セレクトされた社員情報が格納されたリスト
	 */
	public void selectEmployeesPerPage(String pageNo,int pageNumber, int restPageNumber,List<EmployeeDto> employeesList) 
			throws EmployeesStatusException {
		
		//urlから取得したページ数は文字列型なので整数型に変換する
 		Integer intPageno = Integer.parseInt(pageNo);
 		
 		/*activeなレコードのみを取得→10件ずつemployeesViewDtoに格納*/
        List<Employee> employeesActive = select().where(new ComplexWhere().eq("dleteFlag", 0)).orderBy("empno")
				.getResultList(); 
        
        List<Employee> reverceEmployeesActive = commonService.reverseEmployeesList(employeesActive);
        
        //ページ単位で表示する社員情報を格納するリスト
        List<Employee> pagedemployeesList = new ArrayList<Employee>();
        
      //全データ数を10で割った余りが0以上かつ現在のページ数が最大ページ数の場合
        if(restPageNumber > 0 && intPageno == pageNumber){
        	
        	//ページあたりに表示するデータの数は10件ではなく全データ数を10で割った余りになる場合
        	for(int i = (intPageno - 1) * 10; i < (intPageno - 1) * 10 + restPageNumber; i++) {
            	
            	pagedemployeesList.add(reverceEmployeesActive.get(i));
            	
            }
        } else {
        	
        	//ページあたりに表示するデータの数が10件の場合
        	for(int i = (intPageno- 1) * 10 ; i < (intPageno - 1) * 10 + 10; i++) {
            	
            	pagedemployeesList.add(reverceEmployeesActive.get(i));
            	
            }
        }     
        
        commonService.createEmployeesDto(pagedemployeesList, employeesList);
        
    }
	
	/**
	 * ページングのために10件ずつ社員情報をセレクトするメソッド.
	 * @param pageNo ページ番号
	 * @return セレクトされた社員情報が格納されたリスト
	 */
	public void selectSearchedEmployeesPerPage(String pageNo,int pageNumber, int restPageNumber,List<EmployeeDto> employeesViewList,List<Employee> employeeList) 
			throws EmployeesStatusException {
		
		//urlから取得したページ数は文字列型なので整数型に変換する
 		Integer intPageno = Integer.parseInt(pageNo);
 		
        List<Employee> reverceEmployeesActive = commonService.reverseEmployeesList(employeeList);
        
        //ページ単位で表示する社員情報を格納するリスト
        List<Employee> pagedemployeesList = new ArrayList<Employee>();
        
      //全データ数を10で割った余りが0以上かつ現在のページ数が最大ページ数の場合
        if(restPageNumber > 0 && intPageno == pageNumber){
        	
        	//ページあたりに表示するデータの数は10件ではなく全データ数を10で割った余りになる場合
        	for(int i = (intPageno - 1) * 10; i < (intPageno - 1) * 10 + restPageNumber; i++) {
            	
            	pagedemployeesList.add(reverceEmployeesActive.get(i));
            	
            }
        } else {
        	
        	//ページあたりに表示するデータの数が10件の場合
        	for(int i = (intPageno- 1) * 10 ; i < (intPageno - 1) * 10 + 10; i++) {
            	
            	pagedemployeesList.add(reverceEmployeesActive.get(i));
            	
            }
        }     
        
        commonService.createEmployeesDto(pagedemployeesList, employeesViewList);
        
    }
	
	/**
     * 画面に出力するページリンクを文字列で作成するメソッド.
     * @param currentPageNo カレントのページ番号(1 origin)
     * @param pageNumber 総レコード数
     * @param restPageNumber 1ページ当たりのレコード数
     * @return ページリンク文字列
     */
    public String makePagingLink(int currentPageNo, int pageNumber, String currentPage) {
        
        //ページ数の分だけ存在するページリンクを格納する配列
        String[] links = new String[pageNumber];
        
        //現在のページなら太字で表し、選択中のページでなければリンクを貼るHTMLタグを作成し、配列に格納する
        for( int i=1; i <= pageNumber; i++ ) {
            if( i == currentPageNo ) {
                links[i-1] = String.format("<span><b>%d</b></span>", currentPageNo);
            } else {
                links[i-1] = String.format("<a title='Page %d' href='/relationship-building/" + currentPage + "/paged-list/%d'>%d</a>", i, i, i);
            }
        }
        
        //作成したHTMを連結させる
        return makeConnectPageLink(" ", links);
    }
    
    /**
     * ページングのリンクを出力するために文字列として連結させる
     * @param pageLinkConnector 連結文字列
     * @param pageLinks 連結したい文字列の配列
     * @return 連結されたページリンク(各要素は空白をはさんで連結される)
     */
    private static String makeConnectPageLink(String pageLinkConnector, String[] pageLinks) {
    	
    	
        StringBuffer buf = new StringBuffer();
        
        //ページリンクをスペースで挟んで連結する
        for( int i = 0; i <= pageLinks.length -1 ; i++ ) {

        	buf.append(pageLinks[i]);

        	if( i == pageLinks.length -1 ) {
        		break;
        	}
        	buf.append(pageLinkConnector);
        }
        
        //StringBufferをStringに変換
        return buf.toString();
    }
    
    public List<String> splitSearchWord(String searchWords,List<String> devidedSearchWordList) {
    	
    	//入力された検索ワードを空白で分割し、配列に格納する
    	String[] searchWordList = searchWords.split("　", 0);
    	
    	for(String searchWord : searchWordList) {
    		
    		String[] devidedSearchWord = searchWord.split(" ", 0);
    		
    		for(int i = 0; i < devidedSearchWord.length; i++) {
    			
    			devidedSearchWordList.add(devidedSearchWord[i]);
    		}
    	}
    	return devidedSearchWordList;
    }
 
    /**
     * 検索ワードを用いてDBから社員情報をセレクトするメソッド.
     * @param devidedSearchWordList
     * @return
     */
    public List<Employee> selectBySearchWord(String searchWords) {
    	
    	//検索結果を格納する社員情報のエンティティ型の変数を格納するリスト
    	List<Employee> searchedEmployeesList = new ArrayList<Employee>();
    	
    	//すべての検索結果を格納する社員情報のエンティティ型の変数を格納するリスト
    	List<Employee> allSearchedEmployeesList = new ArrayList<Employee>();
    	
    	//重複を取り除いた社員情報を格納するリスト
    	List<Employee> notOverlapSearchedEmployeesList = new ArrayList<Employee>();
    	
    	//全角スペース、半角スペース共に分割された検索ワード
    	List<String> devidedSearchWordList = new ArrayList<String>();
    	
    	//検索ワードをスペースごとに区切る
    	splitSearchWord(searchWords, devidedSearchWordList);
    	//個別の検索結果を格納する社員情報のエンティティ型の変数を格納するリスト
    	
    	for(int i = 0; i < devidedSearchWordList.size(); i++) {

    		//検索ワードの前後に%をつける
    		String searchWord = "%" + devidedSearchWordList.get(i) + "%";
    		
    		//sql文を実行	
    		searchedEmployeesList = jdbcManager.selectBySql(Employee.class,
    				"select * from employees where emp_name like ? "
    						+ "or emp_frigana like ? "
    						+"or birthday like ? "
    						+"or telephone_number like ? "
    						+"or note like ? "
    						,searchWord,searchWord,searchWord,searchWord,searchWord
    				).getResultList();

    		//セレクトしたものをリストに追加する
    		allSearchedEmployeesList.addAll(searchedEmployeesList);

    	}
    	
    	//セレクトしたデータの重複を削除する
    	removeOberlapList(allSearchedEmployeesList, notOverlapSearchedEmployeesList);
    	
    	return notOverlapSearchedEmployeesList;
    }
    
    /**
     * 社員情報リストの重複項目を社員IDを利用して削除するメソッド.
     * @param employeeList 社員情報が格納されているリスト
     * @param notOverlapSearchedEmployeesList 重複をなくした社員情報が格納されたリスト
     */
    public void removeOberlapList(List<Employee> employeeList, List<Employee> notOverlapSearchedEmployeesList) {
    	
    	//社員情報の重複を取り除くために用いるマップ
    	Map<Integer, Employee> notOverlapMap = new HashMap<Integer, Employee>();
    	
    	//重複して検索された値を取り除く
    	for(Employee employee : employeeList) {
    		notOverlapMap.put(employee.empno, employee);
    	}
    	
    	//重複を取り除いた値をリストに戻す
    	for(Map.Entry<Integer, Employee> employeeMap : notOverlapMap.entrySet()) {
    		notOverlapSearchedEmployeesList.add(employeeMap.getValue());
    	}
    	
    }
    
}
