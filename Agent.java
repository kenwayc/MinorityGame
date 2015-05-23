package MG;

import java.util.ArrayList;

/**
 * 
 * @author Kenway
 * This is the Agent Class that creates a single Agent with 
 * two strategies.
 *
 */

 public class Agent {
	private int label; // the label of agent i
	private int score;
	private double cost; // transport cost
	private double actualCost;
	private boolean canUseHub; // whether it has access to the hub
	private ArrayList<Strategy> strategies; // the agent's total strategies available
	
	// a default constructor
	public Agent(int label) {
		this.label = label;
		this.score = 0;
		this.cost = (double) ((int) (50 * Math.random() + 1));
		this.actualCost =0.0;
		this.canUseHub = false; // by default
		this.strategies = new ArrayList<Strategy>();
		Strategy first = new Strategy("first");
		Strategy second = new Strategy("second");
		while (first.getContent().equals(second.getContent())) {
			second = new Strategy("second"); // in case the second is the same as the first strategy
		}
		strategies.add(first);
		strategies.add(second);
	}

	// a more extensive constructor
	public Agent(int label, ArrayList<Strategy> strategies) {
		this.label = label;
		this.strategies = strategies;
	}
	
	public ArrayList<Strategy> getStrategies() {
		return this.strategies;
	}

	public void setStrategies(ArrayList<Strategy> strategies) {
		this.strategies = strategies;
	}

	// decide to use the hub or not
	public boolean getAction(int currentHistory) {
		Strategy best = this.getBestStrategy();
		boolean action;
		action = best.getContent()[currentHistory];		
		return action && this.canUseHub;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLabel() {
		return this.label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public boolean getCanUseHub() {
		return canUseHub;
	}

	public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}

	public void setCanUseHub(boolean canUseHub) {
		this.canUseHub = canUseHub;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

/*	public Strategy getBestStrategy() {
		int bestScore = 0;
		int currentSize = 0;
		int index = 0;
		ArrayList<Strategy> currentStrategy = new ArrayList<Strategy>();
		for (Strategy s:this.strategies) {
			if (s.getVscore() > bestScore) {
				bestScore = s.getVscore();;
				currentStrategy.clear();
				currentStrategy.add(s);
			} else if (s.getVscore() == bestScore) {
				currentStrategy.add(s);
			}
		}
		currentSize = currentStrategy.size();
		index = (int) Math.random() * currentSize;
		return currentStrategy.get(index); // get a random strategy from the best strategy pool
	}*/
	
	// Comparing two strategies
	public Strategy getBestStrategy() {
		Strategy best;
		if (this.strategies.get(0).getVscore() > 
		this.strategies.get(1).getVscore()) {
			best = this.strategies.get(0);
		}
		else if (this.strategies.get(0).getVscore() < 
		this.strategies.get(1).getVscore()) {
			best = this.strategies.get(1);
		}
		else best = this.strategies.get((int)Math.random()*2);
		return best;
	}
	
	public String toString() {
		return "Agent" + this.label + "'s transport cost is " + this.cost;
	}


}