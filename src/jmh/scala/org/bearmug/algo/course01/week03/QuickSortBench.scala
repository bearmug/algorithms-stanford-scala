package org.bearmug.algo.course01.week03

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * Benchmark                          Mode  Cnt     Score     Error  Units
  *QuickSortBench.sortFirst          thrpt    5  1276.507 ± 128.870  ops/s
  *QuickSortBench.sortFirstReverse   thrpt    5   129.192 ±  16.124  ops/s
  *QuickSortBench.sortFirstSorted    thrpt    5   129.995 ±  21.578  ops/s
  *QuickSortBench.sortLast           thrpt    5  1228.156 ±  66.801  ops/s
  *QuickSortBench.sortLastReverse    thrpt    5   117.942 ±  38.500  ops/s
  *QuickSortBench.sortLastSorted     thrpt    5   122.433 ±   3.591  ops/s
  *QuickSortBench.sortMedian         thrpt    5  1078.826 ± 269.859  ops/s
  *QuickSortBench.sortMedianReverse  thrpt    5   434.632 ±  82.597  ops/s
  *QuickSortBench.sortMedianSorted   thrpt    5  2399.205 ±  21.592  ops/s
  */

@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class QuickSortBench {

  var numRandom: Vector[Int] = Vector()
  var numSorted: Vector[Int] = Vector()
  var numRevers: Vector[Int] = Vector()

  @Setup
  def setup(): Unit = {
    numRandom = Vector.fill(1000)(Random.nextInt(100000))
    numSorted = (1 to 1000).toVector
    numRevers = (1 to 1000).reverse.toVector
  }

  @Benchmark
  def sortMedian(): Vector[Int] = QuickSort(numRandom).sortMedian()

  @Benchmark
  def sortMedianSorted(): Vector[Int] = QuickSort(numSorted).sortMedian()

  @Benchmark
  def sortMedianReverse(): Vector[Int] = QuickSort(numRevers).sortMedian()

  @Benchmark
  def sortFirst(): Vector[Int] = QuickSort(numRandom).sortFirst()

  @Benchmark
  def sortFirstSorted(): Vector[Int] = QuickSort(numSorted).sortFirst()

  @Benchmark
  def sortFirstReverse(): Vector[Int] = QuickSort(numRevers).sortFirst()

  @Benchmark
  def sortLast(): Vector[Int] = QuickSort(numRandom).sortLast()

  @Benchmark
  def sortLastSorted(): Vector[Int] = QuickSort(numSorted).sortLast()

  @Benchmark
  def sortLastReverse(): Vector[Int] = QuickSort(numRevers).sortLast()

  @Benchmark
  def sortFunc(): Vector[Int] = QuickSort(numRandom).sortFunc()

  @Benchmark
  def sortFuncSorted(): Vector[Int] = QuickSort(numSorted).sortFunc()

  @Benchmark
  def sortFuncReverse(): Vector[Int] = QuickSort(numRevers).sortFunc()
}
