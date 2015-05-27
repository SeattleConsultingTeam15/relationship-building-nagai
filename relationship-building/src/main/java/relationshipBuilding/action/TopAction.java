
package relationshipBuilding.action;
import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import relationshipBuilding.dto.AnalysisEmployeesDto;
import relationshipBuilding.dto.LoginDto;
import relationshipBuilding.service.IndexService;

/**
 * ホーム画面(ダッシュボード)を制御するアクションクラス.
 * @author nagaimidori
 *
 */
public class TopAction {
	
	//トップページの機能関するサービスクラス
	@Resource
	protected IndexService indexService;
	
	//ダッシュボードに表示する変数を格納するDto
	@Resource
	public AnalysisEmployeesDto analysisEmployeesDto;
	
	//ログイン情報を格納するDto
	@Resource
	public LoginDto loginDto;

    /**
     * トップページにアクセスした時に呼び出されるメソッド.
     * @return トップページを返します
     */
	@Execute(validator = false)
	public String index() {
    	/*更新しました1*/
		//ダッシュボードに表示する値(話した人・話していない人の数,割合をDtoにセットする)
    	indexService.createAnalysisEmployeeDto(analysisEmployeesDto);
    	
        return "index.jsp";
	}
    
}
