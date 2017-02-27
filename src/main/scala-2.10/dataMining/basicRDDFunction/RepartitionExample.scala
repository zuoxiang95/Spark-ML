package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/7.
	* function:repartition
	* 与coalesce类似，都是对数据进行重新分区
	*/
object RepartitionExample extends App{
	val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("")
	val sc = new SparkContext(conf)
	var arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6))
	println(arr.partitions.length)
	arr = arr.repartition(3)
	println(arr.partitions.length)
  sc.stop()
}
