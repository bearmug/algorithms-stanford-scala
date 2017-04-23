package org.bearmug.algo.course02.week03

import java.util.concurrent.TimeUnit

import org.bearmug.algo.course02.week03.MFactory._
import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * MedianBench.addHeap            thrpt    5       215.527 ±       4.205  ops/s
  *MedianBench.addHeapDual        thrpt    5       102.160 ±       0.993  ops/s
  *MedianBench.addListHeap        thrpt    5       231.365 ±       3.983  ops/s
  *MedianBench.addListHeapDual    thrpt    5       158.848 ±       7.223  ops/s
  *MedianBench.addListPlain       thrpt    5       351.982 ±       8.576  ops/s
  *MedianBench.addPlain           thrpt    5       343.175 ±      10.108  ops/s
  *MedianBench.calcHeap           thrpt    5   1721001.551 ±   22275.313  ops/s
  *MedianBench.calcHeapDual       thrpt    5  40102966.933 ± 3821772.618  ops/s
  *MedianBench.calcPlain          thrpt    5       215.677 ±       3.014  ops/s
  *MedianBench.medianSumHeap      thrpt    5        80.459 ±       5.728  ops/s
  *MedianBench.medianSumHeapDual  thrpt    5        95.776 ±       4.398  ops/s
  *MedianBench.medianSumPlain     thrpt    5         0.043 ±       0.019  ops/s
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
    plainMedian = plain() ++ l
    singleHeapMedian = singleHeap() ++ l
    dualHeapMedian = dualHeap() ++ l
  }

  @Benchmark
  def addPlain(): Median = l.foldLeft(plain())((m, i) => m + i)

  @Benchmark
  def addHeap(): Median = l.foldLeft(singleHeap())((m, i) => m + i)

  @Benchmark
  def addHeapDual(): Median = l.foldLeft(dualHeap())((m, i) => m + i)

  @Benchmark
  def addListPlain(): Median = plain() ++ l

  @Benchmark
  def addListHeap(): Median = singleHeap() ++ l

  @Benchmark
  def addListHeapDual(): Median = dualHeap() ++ l

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
