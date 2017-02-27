package dataMining.MLlib

import breeze.linalg.{DenseMatrix, DenseVector, diag}

/**
	* Created by hp on 2016/11/14.
	*/
object TestBreeze extends App{
  val m1 = DenseMatrix.zeros[Double](3, 3)
	val v1 = DenseVector.zeros[Double](3)
	val v2 = DenseVector.ones[Double](3)
	val v3 = DenseVector.fill(3){5.0}
	val v4 = DenseVector.range(1, 10, 2)
	val m2 = DenseMatrix.eye[Double](3)
	val m3 = diag(DenseVector(1, 2, 3))
	println(m1,m2)
	println(v1, v2, v3, v4)
}
