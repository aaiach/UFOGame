package mappanelclasses;

public class Main {
	public static void main(String[] args) {
		RipleyDataModel dataModel = new RipleyDataModel();
		MainWindow mainWindow = new MainWindow(dataModel);
		dataModel.addObserver(mainWindow);
		mainWindow.setVisible(true);
	}
}
