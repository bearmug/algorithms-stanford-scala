package org.bearmug.algo.course02.week04

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * Benchmark                     Mode  Cnt       Score       Error  Units
  *TwoSumBench.calcBench        thrpt    5       0.082 ±     0.010  ops/s
  *TwoSumBench.satisfyForBench  thrpt    5  112697.025 ± 31278.765  ops/s
  *
  */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class TwoSumBench {

  var set: Set[Long] = Set.empty

  var sum: TwoSum = _

  @Setup
  def setup() = {
    set = Seq.fill(10000)(Random.nextInt(1000000).toLong).toSet
    sum = TwoSum(set)
  }

  @Benchmark
  def calcBench(): Int = sum.calcFor(300000)

  @Benchmark
  def satisfyForBench(): Boolean = sum.satisfyFor(300000L)
}
