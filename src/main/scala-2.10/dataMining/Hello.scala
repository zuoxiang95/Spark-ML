package dataMining

import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/4.
	*/
object Hello {
	def main(args: Array[String]): Unit = {
		val sparkConf = new SparkConf().setMaster("local").setAppName("hello!PI!")
		val sc = new SparkContext(sparkConf)
		val fileText = "C:/Users/hp/Desktop/aa.txt"
		val data = sc.textFile(fileText)
		data.flatMap(_.split(" "))
			.map((_, 1))
		  .reduceByKey(_ + _)
		  .collect()
		  .foreach(println)
		sc.stop()
	}
}
