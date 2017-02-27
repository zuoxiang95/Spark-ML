package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	*/
object testRDDMethod extends App{
	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("testRDDMethod")
	val sc = new SparkContext(conf)
	val str = sc.parallelize(Array("one", "two", "three", "four", "five"))
	val result = str reduce fun1
	result foreach print
  sc stop()

	def fun1(str1: String, str2: String): String ={
		val str = if(str1.length >= str2.length) str1 else str2
    str
	}
}
