package org.bearmug.algo.course01.week01

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

/**
  * Performance report for given input
  *
  * Benchmark                                 (par)                                                              (s1)                                                              (s2)   Mode  Cnt    Score     Error  Units
  *MultiplicationAlgoBench.karatsubaMult         3  314...592  271...627  thrpt    5  561.377 ± 137.897  ops/s
  *MultiplicationAlgoBench.parKaratsubaMult      3  314...592  271...627  thrpt    5  881.810 ±  25.142  ops/s
  *MultiplicationAlgoBench.parRecursiveMult      3  314...592  271...627  thrpt    5  492.557 ±  82.328  ops/s
  *MultiplicationAlgoBench.recursiveMult         3  314...592  271...627  thrpt    5  330.224 ±   6.759  ops/s
  *
  */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class MultiplicationAlgoBench {

  @Param(Array("3141592653589793238462643383279502884197169399375105820974944592"))
  var s1: String = ""

  @Param(Array("2718281828459045235360287471352662497757247093699959574966967627"))
  var s2: String = ""

  @Param(Array("3"))
  var par: Int = 0

  @Benchmark
  def recursiveMult(): SNum = SNum.recursiveSeq(s1, s2)

  @Benchmark
  def karatsubaMult(): SNum = SNum.karatsubaSeq(s1, s2)

  @Benchmark
  def parRecursiveMult(): SNum = SNum.recursivePar(s1, s2, par)

  @Benchmark
  def parKaratsubaMult(): SNum = SNum.karatsubaPar(s1, s2, par)
}
