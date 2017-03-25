/**
 * 
 */
package views;

/**
 * @author bunny
 *
 */
public class SightingsListWindow extends JFrame implements Observer {

	private SightingsList sightingsList;
	
	public SightingsListWindow(SightingsList sightingsList, SightingsListController controller) {
		
		this.sightingsList = sightingsList;
		setTitle(sightingsList.getState());
		
	}
	
}
