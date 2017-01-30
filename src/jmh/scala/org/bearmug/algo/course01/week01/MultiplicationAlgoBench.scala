package org.bearmug.algo.course01.week01

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.util.Random

@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
class MultiplicationAlgoBench {

  @Param(Array("3141592653589793238462643383279502884197169399375105820974944592"))
  var s1: String = ""

  @Param(Array("2718281828459045235360287471352662497757247093699959574966967627"))
  var s2: String = ""

  @Benchmark
  def recursiveMult(): Recursive = Recursive.multiplyRecursive(s1, s2)

  @Benchmark
  def karatsubaMult(): Recursive = Recursive.multiplyKaratsuba(s1, s2)

  @Benchmark
  def parRecursiveMult(): Recursive = Recursive.parMultiplyRecursive(s1, s2)

  @Benchmark
  def parKaratsubaMult(): Recursive = Recursive.parMultiplyKaratsuba(s1, s2)
}
