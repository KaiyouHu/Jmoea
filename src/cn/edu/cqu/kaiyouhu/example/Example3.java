package cn.edu.cqu.kaiyouhu.example;

import java.io.IOException;

import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.analysis.collector.Accumulator;

public class Example3 {
	public static void main(String[] args) throws IOException {
		// setup the instrumenter to record the generational distance metric
		Instrumenter instrumenter = new Instrumenter()
				.withProblem("UF1")
				.withFrequency(100)
				.attachElapsedTimeCollector()
				.attachGenerationalDistanceCollector();
		
		// use the executor to run the algorithm with the instrumenter
		new Executor()
				.withProblem("UF1")
				.withAlgorithm("NSGAII")
				.withMaxEvaluations(10000)
				.withInstrumenter(instrumenter)
				.run();
		
		Accumulator accumulator = instrumenter.getLastAccumulator();
		
		// print the runtime dynamics
		System.out.format("  NFE    Time      Generational Distance%n");
		
		for (int i=0; i<accumulator.size("NFE"); i++) {
			System.out.format("%5d    %-8.4f  %-8.4f%n",
					accumulator.get("NFE", i),
					accumulator.get("Elapsed Time", i),
					accumulator.get("GenerationalDistance", i));
		}
	}
}
