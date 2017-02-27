package dataMining.MLlib

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed._
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/10.
	*/
object TestRowMatrix extends App {
  val conf = new SparkConf()
	  .setMaster("local")
	  .setAppName("TestRowMatrix")
	val sc = new SparkContext(conf)
//	行矩阵
	val rdd = sc.textFile("C:/Users/hp/Desktop/aa.txt")
	  .map(_.split(" ")
	    .map(_.toDouble))
	  .map(line => Vectors.dense(line))
	val rm = new RowMatrix(rdd)
	println(rm.numCols())
	println(rm.numCols())

//	带行索引的行矩阵
	val rdd2 = sc.textFile("C:/Users/hp/Desktop/aa.txt")
	  .map(_.split(" ")
	    .map(_.toDouble))
	  .map(line => Vectors.dense(line))
	  .map((vd) => IndexedRow(vd.size, vd))
	val irm = new IndexedRowMatrix(rdd2)
	println(irm.getClass)
	println(irm.rows.foreach(println))

//	坐标矩阵
	val rdd3 = sc.textFile("C:/Users/hp/Desktop/aa.txt")
		.map(_.split(" ")
			.map(_.toDouble))
		.map(vue => (vue(0).toLong, vue(1).toLong, vue(2).toLong))  //转化成坐标的形式
	  .map(vue2 => MatrixEntry(vue2._1, vue2._2, vue2._3))   //转化为坐标矩阵的格式
	val crm = new CoordinateMatrix(rdd3)  //实例化坐标矩阵
	println(crm.entries.foreach(println))

	sc.stop()
}
