package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	* function: reduce
	* reduce函数与map不同点在与，reduce需要传入两个参数。主要作用是用于减少rdd中元素的个数
	*/
object Reduce extends App{
	val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("Reduce")
	val sc = new SparkContext(conf)
	val str = sc.parallelize(Array("one", "two", "three", "four", "five"))
	val result = str.reduce(_ + _)
	result foreach print
	sc.stop()
}
