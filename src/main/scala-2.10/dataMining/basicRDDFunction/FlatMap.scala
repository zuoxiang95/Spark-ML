package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	* function: flatMap
	* 对数据集记性整体操作的一种函数，返回的也是数据集
	*/
object FlatMap extends App {
  val conf = new SparkConf()
		.setAppName("Filter")
		.setMaster("local")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6))
	val result = arr.flatMap(x => List(x + 1)).collect
	result foreach println
	sc.stop()
}
