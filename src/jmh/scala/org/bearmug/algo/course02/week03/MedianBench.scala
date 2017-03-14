package org.bearmug.algo.course02.week03

import java.util.concurrent.TimeUnit

import org.bearmug.algo.course02.week03.MFactory._
import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * Benchmark                              Mode  Cnt         Score        Error  Units
  *MedianBench.addHeap                   thrpt    5       231.754 ±     46.140  ops/s
  *MedianBench.addHeapDual               thrpt    5       129.028 ±      1.544  ops/s
  *MedianBench.addListHeap               thrpt    5       238.251 ±      4.953  ops/s
  *MedianBench.addListHeapDual           thrpt    5       124.804 ±      1.323  ops/s
  *MedianBench.addListPlain              thrpt    5       396.950 ±     23.134  ops/s
  *MedianBench.addPlain                  thrpt    5       429.844 ±      6.089  ops/s
  *MedianBench.calcHeap                  thrpt    5   1352475.869 ±  78659.761  ops/s
  *MedianBench.calcHeapDual              thrpt    5  55355068.901 ± 510635.733  ops/s
  *MedianBench.calcPlain                 thrpt    5       213.683 ±      3.011  ops/s
  *MedianBench.medianSumHeap             thrpt    5        92.535 ±      1.106  ops/s
  *MedianBench.medianSumHeapDual         thrpt    5       125.834 ±     16.723  ops/s
  *MedianBench.medianSumPlain            thrpt    5         0.043 ±      0.002  ops/s
  *
  */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class MedianBench {
  var l: List[Int] = List.empty

  var plainMedian: Median = _
  var singleHeapMedian: Median = _
  var dualHeapMedian: Median = _

  @Setup
  def setup() = {
    l = Seq.fill(10000)(Random.nextInt(10000000)).distinct.toList
    plainMedian = forList(l)(plain())
    singleHeapMedian = forList(l)(singleHeap())
    dualHeapMedian = forList(l)(dualHeap())
  }

  @Benchmark
  def addPlain(): Median = l.foldLeft(plain())((m, i) => m + i)

  @Benchmark
  def addHeap(): Median = l.foldLeft(singleHeap())((m, i) => m + i)

  @Benchmark
  def addHeapDual(): Median = l.foldLeft(dualHeap())((m, i) => m + i)

  @Benchmark
  def addListPlain(): Median = forList(l)(plain())

  @Benchmark
  def addListHeap(): Median = forList(l)(singleHeap())

  @Benchmark
  def addListHeapDual(): Median = forList(l)(dualHeap())

  @Benchmark
  def medianSumPlain(): Int = medianSum(l)(plain())

  @Benchmark
  def medianSumHeap(): Int = medianSum(l)(singleHeap())

  @Benchmark
  def medianSumHeapDual(): Int = medianSum(l)(dualHeap())

  @Benchmark
  def calcPlain(): Int = plainMedian.calc

  @Benchmark
  def calcHeap(): Int = singleHeapMedian.calc

  @Benchmark
  def calcHeapDual(): Int = dualHeapMedian.calc
}
