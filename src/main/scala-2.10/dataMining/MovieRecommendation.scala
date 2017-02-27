package dataMining

import org.apache.spark.mllib.recommendation._
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/16.
	*/
object MovieRecommendation extends App{
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("MovieRecommendation")
	val sc = new SparkContext(conf)
	val data_path = "C:/Users/hp/Desktop/movies.txt"
	val data = sc.textFile(data_path).map{ line =>
		val token = line.split(",")
		Rating(token(0).toInt, token(1).toInt, token(2).toDouble)
	}.cache()
  val model = ALS.trainImplicit(data, 10, 5, 0.01, 1.0)


	model.userFeatures.mapValues(_.mkString(", ")).first()

	val recommendations = model.recommendProducts(2, 5)
	recommendations foreach println

	sc.stop()
}
