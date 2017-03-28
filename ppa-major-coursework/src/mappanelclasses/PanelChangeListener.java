package mappanelclasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelChangeListener implements ActionListener {
	private RipleyDataModel ripley;
	private boolean forwards;
	
	public PanelChangeListener(RipleyDataModel ripley, boolean forwards) {
		this.ripley = ripley;
		this.forwards = forwards;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (forwards) {
			ripley.nextPanel();
		} else {
			ripley.previousPanel();
		}
	}
}
