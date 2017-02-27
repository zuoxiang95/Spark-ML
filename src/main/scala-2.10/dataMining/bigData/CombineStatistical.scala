package dataMining.bigData

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SaveMode}

/**
	* Created by hp on 2016/11/30.
	*/
object CombineStatistical extends App{
	// 重置数据库的地址
	System.setProperty("spark.sql.warehouse.dir", "D:/play/Spark-ML/spark-warehouse")

	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("LinkToPG")
	val sc = new SparkContext(conf)
	val sqlContext = new SQLContext(sc)

	val prop = new java.util.Properties
	prop.setProperty("user","postgres")
	prop.setProperty("password","zuoxiang")

	//从数据库中读取数据到spark中
	val jdbcDF1 = sqlContext.read
		.format("jdbc")
		.option("url", "jdbc:postgresql://localhost:5432/ZZBS")
		.option("dbtable", "statistical_card")
		.option("user", "postgres")
		.option("password", "zuoxiang")
		.load()

	val jdbcDF2 = sqlContext.read
		.format("jdbc")
		.option("url", "jdbc:postgresql://localhost:5432/ZZBS")
		.option("dbtable", "subsidy_train_copy")
		.option("user", "postgres")
		.option("password", "zuoxiang")
		.load()


	jdbcDF1.show(2)
	jdbcDF2.show(2)


	val result = jdbcDF1.join(jdbcDF2, "studentid")
	val dataResult = result.write.mode(SaveMode.Append).jdbc("jdbc:postgresql://localhost:5432/ZZBS","decision_tree_label",prop)
	sc.stop()
}
