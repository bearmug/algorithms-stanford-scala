package org.bearmug.algo.course01.week04

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

/**
  * Benchmark                 Mode  Cnt  Score   Error  Units
  *KargerBench.kargerPlain  thrpt    5  0.331 ± 0.018  ops/s
  *
  */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class KargerBench {

  val f = "/karger.data"

  @Benchmark
  def kargerPlain(): Int = Karger(f).minCut()
}
