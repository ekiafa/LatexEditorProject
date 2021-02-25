package controller.commands;

import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.MainWindow;

public class ChangeVersionsStrategyCommand implements Command {
	private MainWindow mainWindow;
	private VersionsStrategy strategy;
	
	public ChangeVersionsStrategyCommand(MainWindow mainWindow) {
		super();
		this.mainWindow=mainWindow;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		VersionsStrategy strategyType = mainWindow.getVersionsManager().getStrategy();
		if(strategyType.equals("stable") && mainWindow.getVersionsManager().getStrategy() instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(mainWindow.getVersionsManager().getStrategy().getEntireHistory());
			strategy = newStrategy;
			mainWindow.getVersionsManager().enable();
		}
		else if(strategyType.equals("volatile") && mainWindow.getVersionsManager().getStrategy() instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(mainWindow.getVersionsManager().getStrategy().getEntireHistory());
			strategy = newStrategy;
			mainWindow.getVersionsManager().enable();
		}
	}

}
