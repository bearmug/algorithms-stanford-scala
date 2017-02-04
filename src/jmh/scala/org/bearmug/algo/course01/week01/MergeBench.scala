package org.bearmug.algo.course01.week01

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

/**
  * Performance report for given input
  *
  * Benchmark           (par)   Mode  Cnt     Score      Error  Units
  *MergeBench.sortPar      1  thrpt    5  3810.854 ± 1253.732  ops/s
  *MergeBench.sortPar      3  thrpt    5  4141.927 ±  428.473  ops/s
  *MergeBench.sortPar      5  thrpt    5  3296.795 ±  359.086  ops/s
  *MergeBench.sortSeq      1  thrpt    5  3447.664 ±  373.079  ops/s
  *MergeBench.sortSeq      3  thrpt    5  3559.311 ±   21.955  ops/s
  *MergeBench.sortSeq      5  thrpt    5  3524.943 ±   88.169  ops/s
  *
  */

@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class MergeBench {

  var nums: Seq[Int] = Nil

  @Param(Array("1", "3", "5"))
  var par: Int = 0

  @Setup
  def setup(): Unit = {
    nums = Seq.fill(1000)(Random.nextInt(100000))
  }

  @Benchmark
  def sortPar(): Seq[Int] = Merge(nums, Ordering.Int).sortPar(par)

  @Benchmark
  def sortSeq(): Seq[Int] = Merge(nums, Ordering.Int).sort()
}
