package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/9.
	* function: keyBy
	*  按照一定的规则对数据集的所有数据一一生成一个key，输出（key，value）的形式
	*/
object KeyBy extends App {
	val conf = new SparkConf()
		.setAppName("Filter")
		.setMaster("local")
	val sc = new SparkContext(conf)
	val str1 = sc.parallelize(Array("one", "two", "three", "four", "five"))
	val str2 = str1.keyBy(word => word.length)
	str2 foreach println
	sc.stop()
}
