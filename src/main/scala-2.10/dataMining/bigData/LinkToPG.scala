package dataMining.bigData

import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by ZX on 2016/11/25.
	*/
object LinkToPG extends App {
	// 重置数据库的地址
	System.setProperty("spark.sql.warehouse.dir", "D:/play/Spark-ML/spark-warehouse")

	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("LinkToPG")
	val sc = new SparkContext(conf)
	val sqlContext = new SQLContext(sc)


	//从数据库中读取数据到spark中
	val jdbcDF = sqlContext.read
		.format("jdbc")
		.option("url", "jdbc:postgresql://localhost:5432/ZZBS")
		.option("dbtable", "borrow_train")
		.option("user", "postgres")
		.option("password", "zuoxiang")
		.load()

	val prop = new java.util.Properties
	prop.setProperty("user","postgres")
	prop.setProperty("password","zuoxiang")

	// 对学生的id进行统计在图书馆借书的次数
	val result = jdbcDF.groupBy("studentID")
		.count()
		.sort("count").select("studentID", "count")

	// 将结果写入到数据的表中
	val dataResult = result.write.mode(SaveMode.Append).jdbc("jdbc:postgresql://localhost:5432/ZZBS","dataframe_test",prop)

	sc.stop()




}
