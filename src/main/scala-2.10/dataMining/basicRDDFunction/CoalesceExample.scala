package dataMining.basicRDDFunction

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/7.
	* function:coalesce
	* 将数据进行分区处理，第一个参数是分区个数，第二个布尔值参数是将数据分成更小的片时使用
	*/
object CoalesceExample extends App {
   val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("CoalesceExample")
	val sc = new SparkContext(conf)
	val arr1 = sc.parallelize(Array(1, 2, 3, 4, 5, 6))
	val result1 = arr1.aggregate(0)(math.max(_,_), _ + _)
	println(result1)
//	重新分区计算
	val arr2 = arr1.coalesce(2, true)
	val result2 = arr2.aggregate(0)(math.max(_,_), _ + _)
	println(result2)
	sc.stop()
}
