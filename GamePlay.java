package MG;

import java.util.ArrayList;
import java.util.BitSet;

public class GamePlay {
	private BitSet allActions;
	private ArrayList<Agent> agentList;
	private double congestionCost;

	public void setAllActions(BitSet allActions) {
		this.allActions = allActions;
	}

	// constructor
	public GamePlay(int GameListSize, ArrayList<Agent> agentList) {
		this.agentList = agentList;
		allActions = new BitSet(101);
		this.congestionCost = 50.0;
	}

	public int getAttendence() {
		return allActions.cardinality();
	}

	// check whether people who go the bar are minority
	public boolean playGameAndPublishMinority(int currentHistory) {

		for (int i = 0; i < agentList.size(); i++) {
			allActions.set(i, agentList.get(i).getAction(currentHistory));
		}
		return allActions.cardinality() <= 40;
	}

	// update real scores of each agent
	public void updateAgentCostAndScores(boolean isMinority, int currentHistory) {
		for (Agent agent : agentList) {
			if (isMinority) {
				if (agent.getAction(currentHistory)) {
					agent.setActualCost(1.0);
					agent.setScore(agent.getScore() + 1);
				} else {
					agent.setActualCost(agent.getCost());
				}
			}
			else if (agent.getAction(currentHistory)) {
				agent.setActualCost(1.0 + congestionCost);
				if ((1.0 + congestionCost) < agent.getCost()) {
					agent.setScore(agent.getScore() + 1);
				} 
			} else {
				agent.setActualCost(agent.getCost());
				if ((1.0 + congestionCost) > agent.getCost()) {
					agent.setScore(agent.getScore() + 1);
				}
			}
		}
	}

	// update virtual scores of each strategy
	public void updateStrategyScores(boolean isMinority, int currentHistory) {
		for (Agent agent : agentList) {
			for (Strategy strategy : agent.getStrategies()) {
				if (isMinority) {
					if (strategy.getContent()[currentHistory]) {
						strategy.setVscore(strategy.getVscore() + 1);
					}
				}
				else if (strategy.getContent()[currentHistory]) {
					if ((1.0 + congestionCost) < agent.getCost()) {
						strategy.setVscore(strategy.getVscore() + 1);
					} 
				} else {
					if ((1.0 + congestionCost) > agent.getCost()) {
						strategy.setVscore(strategy.getVscore() + 1);
					}
				}
			}
		}
	}
}
