//package relationshipBuilding.service;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.seasar.extension.jdbc.service.S2AbstractService;
//import org.seasar.extension.jdbc.where.SimpleWhere;
//
//import relationshipBuilding.dto.EmployeesViewDto;
//import relationshipBuilding.dto.EmployeesViewDto.EmployeesDto;
//import relationshipBuilding.entity.Employees;
//import relationshipBuilding.form.EmployeesForm;
//
///**
// * 人間関係構築アプリのロジックを指定するクラス.
// * @author nagaimidori
// *
// */
//public class EmployeesService extends S2AbstractService<Employees> {
//	
//	/**
//	 *　データベースに入っている全てのカラムを取得するメソッド.
//	 * @return 全てのエンティティをまとめたDtoが格納されたリストを返します
//	 */
//	public EmployeesViewDto getAllDto() {
//		
//		//全てのエンティティをセレクトします
//		List<Employees> employees = findAll();
//		
//		//セレクトされたエンティティをまとめたDtoを作成します
//		EmployeesViewDto employeesViewDto = createDto(employees);
//		
//		return employeesViewDto;
//	}
//	
//	/**
//	 * 論理削除されていないカラムを取得するメソッド.
//	 * @return 論理削除されていないエンティティをまとめたDtoが格納されたリストを返します
//	 */
//	public EmployeesViewDto getActiveDto() {
//		
//		//論理削除に関するフラグが0(削除されている)のエンティティをセレクトします
//		List<Employees> employees = select().where(new SimpleWhere().eq("dleteFlag", 0))
//				.getResultList();
//		
//		//セレクトされたエンティティをまとめたDtoを作成します
//		EmployeesViewDto employeesViewDto = createDto(employees);
//		
//		return employeesViewDto;
//	}
//	
//	/**
//	 * フォームから入力された値をまとめたDtoを作成するクラス.
//	 * @param employeesForm フォームから入力された値が格納されているクラス
//	 * @return フォームから入力された値が格納されたDtoを格納したリスト
//	 */
//	public EmployeesViewDto createDtoFromInput(EmployeesForm employeesForm) {
//		
//		EmployeesViewDto employeesViewDto = new  EmployeesViewDto();
//		EmployeesDto employeesDisp = new EmployeesDto(employeesForm);
//		employeesViewDto.employeesList.add(employeesDisp);
//		
//		return employeesViewDto;
//	}
//	
//	/**
//	 * Dtoに格納された値をデータベースに挿入するメソッド.
//	 * @param employeesDto 挿入したいデータを格納したDto
//	 * @return データーベースを更新した行数
//	 */
//	public int insert(EmployeesDto employeesDto) {
//		
//		Employees entity = new Employees();
//		
//		Timestamp now = new Timestamp(System.currentTimeMillis());
//		
//		//エンティティの中にDtoに格納されていた値をセットする
//		entity.empName= employeesDto.empName;
//		entity.empFrigana = employeesDto.empFrigana;
//		entity.birthday = employeesDto.birthday;
//		entity.telephoneNumber = employeesDto.telephoneNumber;
//	    entity.note = employeesDto.empNote;
//	    entity.status = convertStatusStringToInt(employeesDto.status);
//	    entity.dleteFlag = 0;
//	    entity.updateDate = now;
//	    entity.registrationDate = now;
//		return insert(entity);
//		
//	}
//	
//	/**
//	 * 社員情報のステータス(話したか話していないか)を数字に変換するメソッド.
//	 * @param dtoStatus　文字列で表された社員情報のステータス
//	 * @return 数字に変換された社員情報のステータス
//	 */
//	public int convertStatusStringToInt(String dtoStatus) {
//		
//		//ステータスのデフォルトは「話していない」
//		int status = 0;
//		
//		if(dtoStatus.equals("話していない")) {
//			status = 0;
//		} else if(dtoStatus.equals("話した")) {
//			status = 1;
//		}
//		
//		return status;
//	}
//	
//	
//	/**
//	 * 指定したIdをもつエンティティをセレクトするメソッド.
//	 * @param id ユーザーが指定したId
//	 * @return　条件に一致するエンティティが格納されたリスト
//	 */
//	public List<Employees> findById(Integer id) {
//		
//		return select().where(new SimpleWhere().eq("empno", id))
//						.getResultList();
//	}
//	
//	/**
//	 * 一覧ページの表における指定された行を削除するメソッド.
//	 * @param employeesList 指定された行における社員情報を格納したエンティティが格納されているリスト
//	 */
//	
//	public void deleteById(List<Employees> employeesList) {
//
//		for (Employees employee : employeesList) {
//			//デリートフラグを立てる
//			employee.dleteFlag = 1;
//			update(employee);
//		}
//		
//	}
//	
//	/**
//	 * 一覧ページの表における指定された行を更新するメソッド.
//	 * @param employeesList　指定された行における社員情報を格納したエンティティが格納されているリスト
//	 */
//	public void updateById(List<Employees> employeesList,EmployeesForm employeesForm) {
//	
//		Timestamp now = new Timestamp(System.currentTimeMillis());
//		
//		//エンティティにフォームに入力された更新用の値をセットする.
//		for (Employees employee : employeesList) {
//			employee.empno = employeesForm.selectId;
//			employee.empName = employeesForm.empName;
//			employee.empFrigana = employeesForm.empFrigana;
//			employee.birthday = employeesForm.birthday;
//			employee.telephoneNumber = employeesForm.telephoneNumber;
//			employee.status = convertStatusStringToInt(employeesForm.status);
//			employee.updateDate = now;
//			update(employee);
//		}
//		
//	}
//	
//	/**
//	 * Dtoを格納したリストを作成するメソッド.
//	 * @param employees 社員情報を格納するエンティティが格納されたリスト
//	 * @return　作成したDtoを格納したリスト
//	 */
//	public EmployeesViewDto createDto(List<Employees> employees) {
//		
//		EmployeesViewDto employeesViewDto = new EmployeesViewDto();
//		
//		for (Employees employee : employees) {		
//			EmployeesDto employeesDisp = new EmployeesDto(employee);
//			employeesViewDto.employeesList.add(employeesDisp);
//		}
//		return employeesViewDto;
//	}
//	
//	/**
//	 * 特定のステータス(話したか・話していないか)を保有するカラムの数を数えるメソッド.
//	 * @param status 話したか・話していないか 0:話していない 1:話した
//	 * @return　特定のステータス(話したか・話していないか)を保有するカラムの数
//	 */
//	public long getCount(Integer status) {
//        return select().where(new SimpleWhere().eq("status", status),new SimpleWhere().eq("dleteFlag", 0)).getCount();
//    }
//	
//	/**
//	 * 全てのカラムの数を数えるメソッド.
//	 * @return 全てのカラムの数
//	 */
//	public long getAllCount() {
//		return select().where(new SimpleWhere().eq("dleteFlag", 0)).getCount();
//	}
//	
//	/**
//	 * ページングのために10件ずつ社員情報をセレクトするメソッド.
//	 * @param pageno ページ番号
//	 * @return セレクトされた社員情報が格納されたリスト
//	 */
//	public EmployeesViewDto selectPerPage(String pageno,int pageNumber, int restPageNumber) {
//		
//		//urlから取得されたページ番号は文字列型のため整数型に変換
// 		Integer intPageno = Integer.parseInt(pageno);
// 		
// 		/*activeなレコードのみを取得→10件ずつemployeesViewDtoに格納*/
//        List<Employees> employeesActive = select().where(new SimpleWhere().eq("dleteFlag", 0)).orderBy("empno")
//				.getResultList(); 
//        
//        //ページに表示する社員情報を格納するリスト
//        List<Employees> PagedemployeesList = new ArrayList<Employees>();
//        
//        //全データ数を10で割った余りが0以上かつ現在のページ数が最大ページ数の場合
//        if(restPageNumber > 0 && intPageno == pageNumber){
//        	
//        	//ページあたりに表示するデータの数は10県ではなく全データ数を10で割った余りになる場合
//        	for(int i = (intPageno - 1) * 10; i < (intPageno - 1) * 10 + restPageNumber; i++) {
//            	
//            	PagedemployeesList.add(employeesActive.get(i));
//            	
//            }
//        } else {
//        	//ページあたりに表示するデータの数が10県の場合
//        	for(int i = (intPageno- 1) * 10 ; i < (intPageno - 1) * 10 + 10; i++) {
//            	
//            	PagedemployeesList.add(employeesActive.get(i));
//            	
//            }
//        }
//        
//      //セレクトされたエンティティをまとめたDtoを作成します
// 		EmployeesViewDto employeesViewDto2 = createDto(PagedemployeesList);
// 		
// 		return employeesViewDto2;
//
//    }
//	
//	/**
//     * 画面に出力するページリンクを文字列で作成するメソッド.
//     * @param currentPageNo カレントのページ番号(1 origin)
//     * @param pageNumber 総レコード数
//     * @param restPageNumber 1ページ当たりのレコード数
//     * @return ページリンク文字列
//     */
//    public String makePagingLink(int currentPageNo, int pageNumber) {
//        
//        //ページ数の分だけ存在するページリンクを格納する配列
//        String[] links = new String[pageNumber];
//        
//        //現在のページなら太字で表し、選択中のページでなければリンクを貼るHTMLタグを作成し、配列に格納する
//        for( int i=1; i <= pageNumber; i++ ) {
//            if( i == currentPageNo ) {
//                links[i-1] = String.format("<span><b>%d</b></span>", currentPageNo);
//            } else {
//                links[i-1] = String.format("<a title='Page %d' href='/relationship-building/list/paged-list/%d'>%d</a>", i, i, i);
//            }
//        }
//        
//        //作成したHTMを連結させる
//        return connectPageLink(" ", links);
//    }
//    
//    /**
//     * ページングのリンクを出力するために文字列として連結させる
//     * @param pageLinkConnector 連結文字列
//     * @param pageLinks 連結したい文字列の配列
//     * @return 連結されたページリンク(各要素は空白をはさんで連結される)
//     */
//    private static String connectPageLink(String pageLinkConnector, String[] pageLinks) {
//    	
//        StringBuffer pageLinkBuf = new StringBuffer();
//        
//        //ページリンクをスペースで挟んで連結する
//        for( int i = 0; i <= pageLinks.length -1 ; i++ ) {
//
//        	pageLinkBuf.append(pageLinks[i]);
//
//        	if( i == pageLinks.length -1 ) {
//        		break;
//        	}
//        	pageLinkBuf.append(pageLinkConnector);
//        }
//        
//        //StringBufferをStringに変換
//        return pageLinkBuf.toString();
//    }
//}
