package org.bearmug.algo.course01.week01

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

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
