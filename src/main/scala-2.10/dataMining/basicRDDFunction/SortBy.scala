package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	* function: sortBy
	* sortBy第一个参数是选择排序的参数，第二个参数是升序或者降序
	*/
object SortBy extends App{
	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("testRDDMethod")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array((1,"a"), (5,"e"), (3,"c"), (2,"b"), (4,"d")))
	val result = arr.sortBy(word => word._1)
	result foreach print
	sc.stop()
}
