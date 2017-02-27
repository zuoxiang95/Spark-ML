package dataMining.MLlib

import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint


/**
	* Created by hp on 2016/11/9.
	*/
object TestLabeledPoint extends App{
  val vd: Vector = Vectors.dense(2, 0, 6)
	val pos = LabeledPoint(1, vd)
	println(pos.features)
	println(pos.label)
	val vs: Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(5, 6, 7, 8))
	val neg = LabeledPoint(2, vs)
	println(neg.features)
	println(neg.label)
}
