package org.bearmug.algo.course02.week01

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * Benchmark          Mode  Cnt   Score   Error  Units
  *SCCBench.sccCalc  thrpt    5  19.664 ± 0.498  ops/s
  *
  */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class SCCBench {

  var data: List[(Int, Int)] = List.empty

  @Setup
  def setup() = {
    data = (1 to 1000).map(_ => (Random.nextInt(1000), Random.nextInt(1000))).toList
  }

  @Benchmark
  def sccCalc(): List[Int] = SCC(data).calc()
}
