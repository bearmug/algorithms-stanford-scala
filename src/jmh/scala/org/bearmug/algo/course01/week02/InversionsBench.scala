package org.bearmug.algo.course01.week02

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class InversionsBench {

  var nums: Vector[Int] = Vector()

  @Param(Array("2", "4", "8"))
  var par: Int = 0

  @Setup
  def setup(): Unit = {
    nums = (1 to 10000 reverse).toVector
  }

  @Benchmark
  def inversionsPar(): Int = Inversions(nums).inversionsPar(par)

  @Benchmark
  def inversionsSeq(): Int = Inversions(nums).inversionsSimple()
}
