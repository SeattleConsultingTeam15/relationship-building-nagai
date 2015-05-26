
package relationshipBuilding.dto;

/**
 * トップページのダッシュボードに表示する値をまとめたDtoクラス.
 * @author nagaimidori
 *
 */
public class AnalysisEmployeesDto {

	//ログインをするときの値をまとめたDto
	public LoginDto loginDto;

	//全社員さんの人数
	public long totalEmployeesNumber;

	//話したことがある社員さんの人数
	public long talkedEmployeesNumber;

	//話したことがない社員さんの人数
	public long notTalkedEmployeesNumber;

	//話したことがある社員さんの全体における割合
	public long talkedEmployeesRate;

	//話したことがない社員さんの全体における割合
	public long notTalkedEmployeesRate;

}
