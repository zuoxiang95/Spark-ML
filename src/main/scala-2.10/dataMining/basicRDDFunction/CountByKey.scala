package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/8.
	* function: countByKey
	* 计算键值对的key出现的个数
	*/
object CountByKey extends App {
	val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("CountByKey")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array(("a", 1), ("a", 2), ("b", 1), ("c", 1)))
	val result = arr.countByKey()
	result foreach print
	sc.stop()
}
