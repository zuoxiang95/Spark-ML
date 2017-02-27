package dataMining

import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/11/17.
	*/
object DecisionTreeTest extends App {
	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("DecisionTree")
	val sc = new SparkContext(conf)
	val data_path = "C:/Users/hp/Desktop/covtype_data.txt"
	val rawData = sc.textFile(data_path)
	val data = rawData.map{ line =>
		val values = line.split(",").map(_.toDouble)
		val featureVector = Vectors.dense(values.init)  //创建特征向量
		val label = values.last - 1
		LabeledPoint(label, featureVector)  //决策树的训练集格式第一个值为标签，后面为特征向量。注意标签是从0开始
	}
	//将样本数据切分为三个部分，第一部分为训练集0.8，第二部分为交叉验证集0.1，第三部分为测试集0.1
	val Array(trainData, cvData, testData) = data.randomSplit(Array(0.8, 0.1, 0.1))
	trainData.cache()
	cvData.cache()
	testData.cache()

	def getMetrics(model: DecisionTreeModel, data: RDD[LabeledPoint]): MulticlassMetrics ={
		val predictionAndLabels = data.map{example =>
			(model.predict(example.features), example.label)
		}
		new MulticlassMetrics(predictionAndLabels)
	}

	//训练模型
	val model = DecisionTree.trainClassifier(trainData, 7, Map[Int, Int](), "gini", 4, 100)
	val metrics = getMetrics(model, cvData)

  //输出训练模型的准确度
	(0 until 7).map{
		cat => (metrics.precision(cat), metrics.recall(cat))
	}.foreach(println)
	println(metrics.precision)

//	val evaluation =
//		for(impurity <- Array("gini", "entropy");
//		    depth    <- Array(1, 20);
//		    bins     <- Array(10, 300))
//		yield {
//			val model = DecisionTree.trainClassifier(trainData, 7, Map[Int, Int](), impurity, depth, bins)
//			val predictionAndLabels = cvData.map{example =>
//				(model.predict(example.features), example.label)
//			}
//			val accuracy = new MulticlassMetrics(predictionAndLabels).precision
//			((impurity, depth, bins), accuracy)
//		}
//	evaluation.sortBy(_._2).reverse.foreach(println)

	val forest = RandomForest.trainClassifier(trainData, 7, Map(10 -> 4, 11 -> 40), 20, "auto", "entropy", 30, 300)
	val inputData = cvData.map(_.features)
	forest.predict(inputData).take(10)

	sc.stop()
}
