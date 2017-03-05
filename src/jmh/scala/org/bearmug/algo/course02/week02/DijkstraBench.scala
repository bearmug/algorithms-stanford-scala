package org.bearmug.algo.course02.week02

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * Benchmark                            Mode  Cnt  Score   Error  Units
  *DijkstraBench.shortestPaths         thrpt    5  2.552 ± 0.134  ops/s
  */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class DijkstraBench {

  var gr: Map[Int, List[(Int, Int)]] = Map.empty

  @Setup
  def setup() = {
    gr = (1 to 10000).map { _ =>
      Random.nextInt(1000) ->
        (1 to 30).map { _ =>
          (Random.nextInt(1000), Random.nextInt(1000))
        }.toList
    }.toMap
  }

  @Benchmark
  def shortestPaths(): List[(Int, Int)] = Dijkstra(gr).shortestPaths(gr.keySet.head)
}
