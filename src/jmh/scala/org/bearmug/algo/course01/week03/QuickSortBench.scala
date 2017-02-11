package org.bearmug.algo.course01.week03

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class QuickSortBench {
  var nums: Vector[Int] = Vector()

  @Setup
  def setup(): Unit = {
    nums = Vector.fill(1000)(Random.nextInt(100000))
  }

  @Benchmark
  def sortFirst(): Vector[Int] = QuickSort(nums).sortFirst()

  @Benchmark
  def sortLast(): Vector[Int] = QuickSort(nums).sortLast()
}
