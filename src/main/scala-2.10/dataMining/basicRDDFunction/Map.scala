package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	* function: map
	* 与flatMap不同，flatMap主要是对数据集整体进行操作，返回的是一个map；而map是对数据集中的每一个数据进行单独操作
	*/
object Map extends App {
	val conf = new SparkConf()
		.setAppName("Map")
		.setMaster("local")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6))
	val result = arr.map(x => x + 1).collect
	result foreach println
	sc.stop()
}
