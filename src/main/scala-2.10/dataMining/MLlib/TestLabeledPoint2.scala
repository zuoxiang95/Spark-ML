package dataMining.MLlib

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	*/
object TestLabeledPoint2 extends App{
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("TestLabeledPoint2")
	val sc = new SparkContext(conf)

	val mu = MLUtils.loadLibSVMFile(sc, "C:/Users/hp/Desktop/aa.txt")
	mu foreach println
}
