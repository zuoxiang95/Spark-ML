package dataMining.MLlib

import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/14.
	*/
object CollaborativeMovie extends App {
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("MovieSource")
	val sc = new SparkContext(conf)
	val users = sc.parallelize(Array("zx", "syl", "lzt", "xyq", "zxg"))
	val films = sc.parallelize(Array("Spider", "Doctor", "Cat", "News", "Computer"))
	var source = Map[String, Map[String, Int]]()
	var filmSource = Map[String, Int]()
	getSource
	val name = "syl"
	users.foreach(user => println(name + " 相对于 " + user +"的相似性分数是： " + getCorrSource(name, user)))
  sc.stop()


	def getSource: Map[String, Map[String, Int]] = {
		val user1 = Map("Spider" -> 2, "Doctor" -> 3, "Cat" -> 1, "News" -> 0, "Computer" -> 1)
		val user2 = Map("Spider" -> 1, "Doctor" -> 2, "Cat" -> 2, "News" -> 1, "Computer" -> 4)
		val user3 = Map("Spider" -> 2, "Doctor" -> 1, "Cat" -> 0, "News" -> 1, "Computer" -> 4)
		val user4 = Map("Spider" -> 3, "Doctor" -> 2, "Cat" -> 0, "News" -> 5, "Computer" -> 3)
		val user5 = Map("Spider" -> 5, "Doctor" -> 3, "Cat" -> 1, "News" -> 1, "Computer" -> 2)
		source += ("zx" -> user1)
		source += ("syl" -> user2)
		source += ("lzt" -> user3)
		source += ("xyq" -> user4)
		source += ("zxg" -> user5)
		source
	}

	def getCorrSource(users1: String, users2: String): Double ={
		val user1FilmSource = source.get(users1).get.values.toVector
		val user2FilmSource = source.get(users2).get.values.toVector
		val member = user1FilmSource.zip(user2FilmSource).map(d => d._1 * d._2).sum.toDouble
		val temp1 = math.sqrt(user1FilmSource.map(
			num => math.pow(num, 2)
		).sum)
		val temp2 = math.sqrt(user2FilmSource.map(
			num => math.pow(num, 2)
		).sum)
		val denominator = temp1 * temp2
		member / denominator
	}

}
