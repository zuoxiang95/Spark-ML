package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/8.
	* function: filter
	* 对数据集的数据进行过滤
	*/
object Filter extends App{
  val conf = new SparkConf()
	  .setAppName("Filter")
	  .setMaster("local")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6))
  val result = arr filter(_ > 4)
	result foreach println
	sc.stop()
}
