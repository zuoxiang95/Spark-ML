package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/8.
	* function:countByValue
	* 计算数据集中数据出现的次数，并且以map的形式返回
	*/
object CountByValue extends App{
	val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("CountByValue")
	val sc = new SparkContext(conf)
	val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6))
	val result = arr.countByValue()
	result foreach print
	sc.stop()
}
