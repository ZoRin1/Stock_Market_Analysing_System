/**
 * 
 */
package presentation;

import javafx.scene.Group;
import javafx.scene.Node;

/**
 * @author wang
 *
 */
public interface GroupFather {
	void init();
	Group getGroup();
	/**
	 * @param group1 
	 * 
	 */
	void setGroup1(Group1 group1);
	
	void addNode(Node node);
	
	void removeNode(Node node);
}
