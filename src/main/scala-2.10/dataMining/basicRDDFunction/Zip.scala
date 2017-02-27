package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	*/
object Zip extends App{
	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("testRDDMethod")
	val sc = new SparkContext(conf)
	val arr1 = Array(1, 2, 3, 4, 5)
	val arr2 = Array("a", "b", "c", "d", "e")
	val arr3 = Array("f", "g", "h", "i", "j")
	val arr4 = arr1.zip(arr2).zip(arr3)
	arr4 foreach println
	sc stop()
}
