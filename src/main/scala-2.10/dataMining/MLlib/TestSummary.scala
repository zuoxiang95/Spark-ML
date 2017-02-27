package dataMining.MLlib

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/11.
	* colStats是按列来计算数据的描述性统计量
	*/
object TestSummary extends App {
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("TestSummary")
	val sc = new SparkContext(conf)
	val rdd = sc.textFile("C:/Users/hp/Desktop/aa.txt")
	  .map(_.split(" ")
	    .map(_.toDouble))
	  .map(line => Vectors.dense(line))
	val summary = Statistics.colStats(rdd)
//	按列计算均值、方差、曼哈顿距离、欧几里得距离
//	println(summary.mean)
//	println(summary.variance)
//	println(summary.normL1)
//	println(summary.normL2)

	val rddX = sc.textFile("C:/Users/hp/Desktop/aa.txt")
	  .flatMap(_.split(" ")
	    .map(_.toDouble))

	val rddY = sc.textFile("C:/Users/hp/Desktop/bb.txt")
		.flatMap(_.split(" ")
			.map(_.toDouble))


	val correlation: Double = Statistics.corr(rddX, rddY, "spearman")
  println(correlation)
	sc.stop()
}
