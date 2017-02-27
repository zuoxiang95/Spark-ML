package dataMining.MLlib

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.{Vector, Vectors}

/**
	* Created by hp on 2016/11/9.
	*/
object TestVector extends App{
//	创建密集向量数据Vectors.dense
	val vd: Vector = Vectors.dense(1, 2, 3)
	println(vd(2))
//  创建稀疏向量数据，sparse函数的第一个参数代表向量的长度，一般要大于等于输入向量的长度，
//  第二个参数代表数据的下标值，第三个参数是输入的向量
	val vs: Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(5, 6, 7, 8))
	println(vs(2))
	println(vs)
}
