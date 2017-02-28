package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/7.
	* cache函数主要用于将数据缓存到内存中，在后续的action操作中重用时，速度更快
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
