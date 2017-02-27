package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/8.
	* function:distinct
	* 对数据集进行去重
	*/
object Distinct extends App {
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("Distinct")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array("a", "a", "a", "b", "c", "c"))
	val result = arr.distinct()
	result foreach println
	sc.stop()
}
