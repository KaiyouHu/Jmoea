package cn.edu.cqu.kaiyouhu.example;

import java.io.IOException;

import org.moeaframework.Analyzer;
import org.moeaframework.Executor;

public class Example2 {
	public static void main(String[] args) throws IOException {
		String problem = "UF1";
		String[] algorithms = { "NSGAII", "GDE3", "eMOEA" };

		//setup the experiment
		Executor executor = new Executor()
				.withProblem(problem)
				.withMaxEvaluations(10000);

		Analyzer analyzer = new Analyzer()
				.withProblem(problem)
				.includeHypervolume()
				.showStatisticalSignificance();

		//run each algorithm for 50 seeds
		for (String algorithm : algorithms) {
			analyzer.addAll(algorithm, 
					executor.withAlgorithm(algorithm).runSeeds(50));
		}

		//print the results
		analyzer.printAnalysis();
	}
}
