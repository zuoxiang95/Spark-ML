package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/7.
	* function: cartesian
	* 计算两个rdd的笛卡尔积
	*/
object Cartesian extends App{
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("Cartesian ")
	val sc = new SparkContext(conf)
	val arr1 = sc.parallelize(Array(1, 2, 3, 4, 5, 6))
	val arr2 = sc.parallelize(Array(6, 5, 4, 3, 2, 1))
	val result = arr1 cartesian arr2
	result foreach print
	sc.stop()
}
