package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	* function: groupBy
	* 未知
	*/
object GroupBy extends App{
	val conf = new SparkConf()
		.setAppName("GroupBy")
		.setMaster("local")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array(1, 2, 3, 4, 5))
	val result1 = arr.groupBy(myFilter1(_), 1)
	val result2 = arr.groupBy(myFilter2(_), 2)
  result1 foreach print
	println("------------------")
	result2 foreach print
	sc.stop()


	def myFilter1(num: Int): Unit = {
		num >= 3
	}
	def myFilter2(num: Int): Unit = {
		num < 3
	}
}
