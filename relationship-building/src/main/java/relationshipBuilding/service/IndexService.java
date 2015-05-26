
package relationshipBuilding.service;

import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.SimpleWhere;

import relationshipBuilding.constants.RelationshipBuildingConstants;
import relationshipBuilding.dto.AnalysisEmployeesDto;
import relationshipBuilding.entity.Employee;

/**
 * トップページ(ダッシュボード)における解析計算を行うクラス.
 * @author nagaimidori
 *
 */
public class IndexService extends S2AbstractService<Employee>{
	
	/**
	 * 全てのカラムの数を数えるメソッド.
	 * @return 全てのカラムの数
	 */
	public long getAllActiveEmployeesCount() {
		
		//論理削除されていないすべてのカラムの数を算出
		long allActiveEmployeesCount = select().where(new SimpleWhere().eq("dleteFlag", 0)).getCount();
		
		return allActiveEmployeesCount;
	}
	
	/**
	 * 特定のステータス(話したか・話していないか)を保有するカラムの数を数えるメソッド.
	 * @param status 話したか・話していないか 0:話していない 1:話した
	 * @return　特定のステータス(話したか・話していないか)を保有するカラムの数
	 */
	public long getActiveEmployeesCountByStatus(Integer status) {
		
		//論理削除されていないかつ指定したステータスを持つカラムの数を算出
		long activeEmployeesCountByStatus = 
				select().where(new SimpleWhere().eq("status", status),new SimpleWhere().eq("dleteFlag", 0)).getCount();
		return activeEmployeesCountByStatus;
    }
	
	/**
	 * 割合を算出するメソッド.
	 * @param totalNumber 母数
	 * @param denominator 対象となっている数
	 * @return 算出した割合
	 */
	public long calculationRate(long denominator, long totalNumber) {
		
		//対象となっている数を母数で割り、割合を算出する
		long answer = Math.round((double)(denominator) / (double)(totalNumber)*100);
		
		return  answer;
	}
	/**
	 * ダッシュボードに表示する値をDtoに格納するクラス.
	 * @param analysisEmployeesDto ダッシュボードに表示する値を格納するDto
	 * @return ダッシュボードに表示する値を格納するDtoに値をセットしたもの
	 */
	public AnalysisEmployeesDto createAnalysisEmployeeDto(AnalysisEmployeesDto analysisEmployeesDto) {
		
		//社員情報テーブルの全てのカラム数を取得
		analysisEmployeesDto.totalEmployeesNumber = getAllActiveEmployeesCount();
    	
    	//社員情報テーブルにおけるステータスが1(話している)の社員カラム数を取得
		analysisEmployeesDto.talkedEmployeesNumber = 
    			getActiveEmployeesCountByStatus(RelationshipBuildingConstants.TALKED_WIDTH_THE_EMPLOYEE);
    	
    	//社員情報テーブルにおけるステータスが0(話していない)の社員カラム数を取得
		analysisEmployeesDto.notTalkedEmployeesNumber = 
    			getActiveEmployeesCountByStatus(RelationshipBuildingConstants.NOT_TALKED_WIDTH_THE_EMPLOYEE);
    	
		//0で割らないために母数がゼロかどうか確認してから割合をセットする
    	if(analysisEmployeesDto.totalEmployeesNumber != 0) {
    		
    		//社員情報テーブルにおける話したことがある人の割合を計算
    		analysisEmployeesDto.talkedEmployeesRate 
    			= calculationRate(analysisEmployeesDto.talkedEmployeesNumber, analysisEmployeesDto.totalEmployeesNumber);
    		
    		//社員情報テーブルにおける話したことがない人の割合を計算
    		analysisEmployeesDto.notTalkedEmployeesRate  
    			= calculationRate(analysisEmployeesDto.notTalkedEmployeesNumber , analysisEmployeesDto.totalEmployeesNumber);
    	} else {
    		//母数がゼロの場合、割合も0
    		analysisEmployeesDto.talkedEmployeesRate = 0;
    		analysisEmployeesDto.notTalkedEmployeesRate = 0;
    	}
    	
    	return analysisEmployeesDto;
	}
	
	

}
