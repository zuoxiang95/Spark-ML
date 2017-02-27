package dataMining.bigData

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/28.
	*/
object CardStatistical {
	def main(args: Array[String]): Unit = {
		System.setProperty("spark.sql.warehouse.dir", "D:/play/Spark-ML/spark-warehouse")
		System.setProperty("spark.driver.memory", "512m")
		val conf = new SparkConf()
		  .setMaster("local")
		  .setAppName("CardStatistical")

		val sc = new SparkContext(conf)
		val sqlContext = new SQLContext(sc)

		val jdbcDF = sqlContext.read
			.format("jdbc")
			.option("url", "jdbc:postgresql://localhost:5432/ZZBS")
			.option("dbtable", "card_train")
			.option("user", "postgres")
			.option("password", "zuoxiang")
			.load()

		val prop = new java.util.Properties
		prop.setProperty("user","postgres")
		prop.setProperty("password","zuoxiang")

		val result = jdbcDF.groupBy("studentID", "consumptionLocation", "consumptionWay").sum("consumptionAmount").show(5)
		sc.stop()
	}

}
