package MG;

import java.util.ArrayList;

public class RunMG {
	public static void main(String[] args) {
		ArrayList<Agent> agentList = new ArrayList<>();
		int N = 101; // agent size
		int M = 87; // number of agents that can use the hub 
		int capacity = 40; // hub capacity
		GamePlay play = new GamePlay(capacity, agentList);
		boolean lastMinority = false;
		boolean currentMinority = false;
		int counter = 0;
		int currentHistory;
		double totalCostPerSimulation = 0; // temp total cost of all agents in one game
		double avgCostPerSimulation = 0; // temp cost for each agent in one game
		double sum = 0.0; // temp sum
		double sum2 = 0.0;
		double avgCostPerPeriod = 0.0; // 10000 simulations in a period
		double avgCostPerRun = 0.0; // 1000 runs
		// ArrayList<Integer> totalHistory = new ArrayList<>();
		ArrayList<Double> costHistory = new ArrayList<>();
		ArrayList<Integer> agentIndex = new ArrayList<>();
		ArrayList<Double> overAllCost = new ArrayList<>();


		for (int p = 0; p < 40; p++) {
			M = M +1;
		
		
		// 1000 runs with different agent pools
		for (int i = 0; i < 1000; i++) {
			// initialize agents with two strategies each
			for (int j = 0; j < N; j++) {
				agentList.add(new Agent(j));
				agentIndex.add(j);
			}
			for (int k = 0; k < M; k++) {
				agentList.get(k).setCanUseHub(true);
			}
			/*
			 * Random t = new Random(); int q = 1; for (int p = i; p > 0; p--) {
			 * numberDraw = t.nextInt(102 - q);
			 * agentList.get(agentIndex.get(numberDraw)).setCanUseHub(true);
			 * agentIndex.remove(numberDraw); q++; }
			 */

			// assign a random history in the beginning of the each run
			currentHistory = (int) Math.random() * 4;

			// 10000 repeated simulations with a fixed agent pool of 101 agents
			while (counter < 10000) {
				totalCostPerSimulation = 0.0;
				avgCostPerSimulation = 0.0;

				// play a round
				currentMinority = play
						.playGameAndPublishMinority(currentHistory);
				play.updateAgentCostAndScores(currentMinority, currentHistory);
				play.updateStrategyScores(currentMinority, currentHistory);
				// update case
				if (currentMinority) {
					if (lastMinority) {
						currentHistory = 3;
					} else {
						currentHistory = 1;
					}
				} else if (lastMinority) {
					currentHistory = 2;
				} else {
					currentHistory = 0;
				}

				// keep track of attendance history for later use
				//totalHistory.add(play.getAttendence());

				// calculate average cost per agent in this run
				for (Agent agent : agentList) {
					totalCostPerSimulation += agent.getActualCost();
				}
				avgCostPerSimulation = totalCostPerSimulation / 101.0;
				costHistory.add(avgCostPerSimulation);

				// update last case
				lastMinority = currentMinority;
				counter++;
			}

			// calculate average cost per agent per run through 10000 runs
			for (double cost : costHistory) {
				sum += cost;
			}
			avgCostPerPeriod = sum / costHistory.size();
			overAllCost.add(avgCostPerPeriod);
			
			//System.out.println(avgCostPerPeriod);
			// clear the settings
			counter = 0;
			costHistory.clear();
			sum = 0.0;
			avgCostPerPeriod = 0.0;
			agentList.clear();
			agentIndex.clear();
		}
		
		for (double cost2: overAllCost) {
			sum2 += cost2;
		}
		avgCostPerRun = sum2 / overAllCost.size();
		System.out.println(avgCostPerRun);
		// clear the settings
		sum2 = 0.0;
		overAllCost.clear();
		avgCostPerRun = 0.0;
	}
  }
}
