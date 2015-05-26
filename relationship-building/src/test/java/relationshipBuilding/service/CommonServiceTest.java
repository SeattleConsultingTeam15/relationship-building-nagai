
package relationshipBuilding.service;

import static org.junit.Assert.*;

import org.junit.Test;

import relationshipBuilding.exception.EmployeesStatusException;


/**
 * 人間関係構築アプリの共通ロジックをまとめたクラスのテストクラス.
 * @author nagaimidori
 *
 */
public class CommonServiceTest {

	/**
	 * リストの中身の順番を逆にするメソッドのテストメソッド.
	 */
	@Test
	public void testReverseEmployeesList() {
		

	}

	/**
	 * 社員情報のステータス(話したか話していないか)を文字列から数字に変換するメソッド.
	 */
	@Test
	public void testConvertEmployeesStatusStringToInt() throws EmployeesStatusException{
		
		//要件
		//ステータスが「話していない」・「話した」のときに期待値が返ってくる
		//また、それいがいのときにきちんと例外を投げられる

		CommonService commonService = new CommonService();

		//ステータスが「話していない」のとき
		assertEquals(0, commonService.convertEmployeesStatusStringToInt("話していない"));

		//ステータスが「話した」のとき
		assertEquals(1, commonService.convertEmployeesStatusStringToInt("話した"));


		//戻り値がエラーの時
		//ステータスが「話していない」・「話した」以外のとき
		try {
			commonService.convertEmployeesStatusStringToInt("あああ");//意図的に例外発生

		} catch (EmployeesStatusException e) {
			//意図した例外が発生しているかどうか調べる
			assertEquals("不正なステータスです", e.getMessage());
		}

	}

	/**
	 * 社員情報のステータス(話したか話していないか)を文字列から数字に変換するメソッド.
	 * @throws EmployeesStatusException 
	 */
	@Test
	public void testConvertEmployeesStatusIntToString() throws EmployeesStatusException {
		
		//要件
		//ステータスが「話していない」・「話した」のときに期待値が返ってくる
		//また、それいがいのときにきちんと例外を投げられる

		CommonService commonService = new CommonService();

		//ステータスが「話していない」のとき
		assertEquals("話していない", commonService.convertEmployeesStatusIntToString(0));

		//ステータスが「話した」のとき
		assertEquals("話した", commonService.convertEmployeesStatusIntToString(1));


		//戻り値がエラーの時
		//ステータスが(0,1)以外のとき
		try {
			commonService.convertEmployeesStatusIntToString(3);//意図的に例外発生

		} catch (EmployeesStatusException e) {
			//意図した例外が発生しているかどうか調べる
			assertEquals("不正なステータスです", e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#findEmployeesById(java.lang.Integer)}.
	 */
	@Test
	public void testFindEmployeesById() {
		
		
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#findEmployeeById(java.lang.Integer)}.
	 */
	@Test
	public void testFindEmployeeById() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#createEmployeesDto(java.util.List, java.util.List)}.
	 */
	@Test
	public void testCreateEmployeesDtoListOfEmployeeListOfEmployeeDto() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#createEmployeesDto(java.util.List)}.
	 */
	@Test
	public void testCreateEmployeesDtoListOfEmployee() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#createEmployeeViewDto(relationshipBuilding.dto.EmployeeDto, relationshipBuilding.entity.Employee)}.
	 */
	@Test
	public void testCreateEmployeeViewDtoEmployeeDtoEmployee() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#createEmployeeViewDto(relationshipBuilding.form.EmployeesForm, relationshipBuilding.dto.EmployeeDto)}.
	 */
	@Test
	public void testCreateEmployeeViewDtoEmployeesFormEmployeeDto() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#createEmployeeViewDto(relationshipBuilding.form.UpdateForm, relationshipBuilding.dto.EmployeeDto)}.
	 */
	@Test
	public void testCreateEmployeeViewDtoUpdateFormEmployeeDto() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#getAllActiveEmployeesCount()}.
	 */
	@Test
	public void testGetAllActiveEmployeesCount() {
		
	}

	/**
	 * Test method for {@link relationshipBuilding.service.CommonService#splitBirthday(java.lang.String)}.
	 */
	@Test
	public void testSplitBirthday() {
		
	}

}
