package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/7.
	*/
object CacheTest extends App{
	val conf = new SparkConf()
		.setMaster("local")
	  .setAppName("CacheTest")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array("abc", "b", "c", "d", "e", "f"))
	println(arr)
	println("-------------------------")
	println(arr.cache())
	arr foreach println
	sc.stop()
}
