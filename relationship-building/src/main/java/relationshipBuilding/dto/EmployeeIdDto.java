
package relationshipBuilding.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

/**
 * 社員のIdを格納するDtoクラス.
 * @author nagaimidori
 *
 */
@Component(instance = InstanceType.SESSION)
public class EmployeeIdDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ページングの際に使用するページ番号を格納する変数
	public Integer selectId;
}
