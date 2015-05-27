
package relationshipBuilding.action;
import java.util.HashMap;
import java.util.Map;
import net.arnx.jsonic.JSON;
import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

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
	
	public Integer result;

    /**
     * トップページにアクセスした時に呼び出されるメソッド.
     * @return トップページを返します
     */
	@Execute(validator = false)
	public String index() {
    	
		//ダッシュボードに表示する値(話した人・話していない人の数,割合をDtoにセットする)
    	indexService.createAnalysisEmployeeDto(analysisEmployeesDto);

    	/*jsonファイルの中身作成*/
    	Map<String, Object> jsonResponseData = new HashMap<String, Object>();
        
    	//話したことがある社員の人数
        jsonResponseData.put("talkedEmployeesNumber", 
        		(int)analysisEmployeesDto.talkedEmployeesNumber);
        
        //話したことがない社員の人数
        jsonResponseData.put("notTalkedEmployeesNumber", 
        		(int)analysisEmployeesDto.notTalkedEmployeesNumber);
        
        //社員の総数
        jsonResponseData.put("totalEmployeesNumber", 
        		(int)analysisEmployeesDto.totalEmployeesNumber);
        
        //社員総数に対する話したことがある社員の人数の割合
        jsonResponseData.put("talkedEmployeesRate", 
        		(int)analysisEmployeesDto.talkedEmployeesRate);
        
        //社員総数に対する話したことがない社員の人数の割合
        jsonResponseData.put("notTalkedEmployeesRate", 
        		(int)analysisEmployeesDto.notTalkedEmployeesRate);
     
        // JSONICライブラリによりJSON形式へ変換
        String jsonText = JSON.encode(jsonResponseData);
     
        ResponseUtil.write(jsonText, "application/json");
    	
        return "index.jsp";
	}
	

}
