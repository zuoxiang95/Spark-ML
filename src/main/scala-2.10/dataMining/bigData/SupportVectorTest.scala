package dataMining.bigData

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

/**
	* Created by hp on 2016/12/1.
	*/
object SupportVectorTest extends App {
	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("DecisionTree")
	val sc = new SparkContext(conf)
	Logger.getRootLogger.setLevel(Level.WARN)
	val data_path = "C:/Users/hp/Desktop/train_dataset01.txt"
	val rawData = sc.textFile(data_path)

	val data = rawData.map{ line =>
		val values = line.split(",").map(_.toDouble)
		val featureVector = Vectors.dense(values.init)  //创建特征向量
	val label = values.last
		LabeledPoint(label, featureVector)  //决策树的训练集格式第一个值为标签，后面为特征向量。注意标签是从0开始
	}

	//分个样本为测试集与训练集
	val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
	val training = splits(0).cache()
	val test = splits(1).cache()

	//新建逻辑回归模型，并训练
	val numIterations = 100
	val model = SVMWithSGD.train(training, numIterations)

	//测试训练样本
	val predictionAndLabel = test.map{ point =>
		val score = model.predict(point.features)
		(score, point.label)
	}

	//对测试集进行预测
	val printPredict = predictionAndLabel.take(30)
	println("prediction" + "\t" + "label")
	for(i <- 0 to printPredict.length - 1){
		println(printPredict(i)._1 + "\t" + printPredict(i)._2)
	}

	//计算误差
	val accuracy = 1 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()
	println("Area under ROC = " + accuracy)

	val testCount = predictionAndLabel.count().toInt
	val trueCount = predictionAndLabel.filter(x => x._1.toInt == x._2.toInt).count()
	println(testCount)
	println(trueCount)


	//保存模型
//	val modelPath = "C:/Users/hp/Desktop"
//	model.save(sc, modelPath)
	sc.stop()
}
