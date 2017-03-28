package mappanelclasses;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RangeSelectedListener implements ItemListener {
	int counter;
	private RipleyDataModel ripley;
	private MainWindow window;
	
	public RangeSelectedListener(RipleyDataModel ripley, MainWindow window) {
		this.ripley = ripley;
		this.window = window;
		counter = 0;
	}

	public void itemStateChanged(ItemEvent arg0) throws InvalidRangeException {
		if (window.rangeSelected()) {
			if (counter == 1) {
				counter = 0;
				if (Integer.parseInt(window.getFrom()) > Integer.parseInt(window.getTo())) {
					System.err.println("You have selected an invalid range. Try again.");
				} else {
					ripley.updateSelectedIncidents(window.getFrom(), window.getTo());
				}
			} else {
				counter = 1;
			}
		}
	}
}
