package dataMining.MLlib

import org.apache.spark.ml.linalg.Matrices

/**
	* Created by hp on 2016/11/10.
	*/
object TestMatrix extends App {
//	创建一个矩阵，第一个参数是矩阵的行，第二个是矩阵的列，最后是数据，储存矩阵是按列放的
  val mx = Matrices.dense(2, 2, Array(1, 2, 3, 4))
	println(mx)
}
