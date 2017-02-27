package dataMining.bigData

import java.io.{File, PrintWriter}

import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


/**
	* Created by hp on 2016/11/30.
	*/
object TestDecisionTree extends App{

	val conf = new SparkConf()
		.setMaster("local")
		.setAppName("DecisionTree")
	val sc = new SparkContext(conf)
	val data_path = "C:/Users/hp/Desktop/train_dataset.txt"
	val rawData = sc.textFile(data_path)
	val data = rawData.map{ line =>
		val values = line.split(",").map(_.toDouble)
		val featureVector = Vectors.dense(values.init)  //创建特征向量
	  val label = values.last
		LabeledPoint(label, featureVector)  //决策树的训练集格式第一个值为标签，后面为特征向量。注意标签是从0开始
	}
	//将样本数据切分为二个部分，第一部分为训练集0.7，第二部分为测试集0.3
	val Array(trainData, testData, cvData) = data.randomSplit(Array(0.4, 0.4, 0.2))
	cvData.cache()
	trainData.cache()
	testData.cache()


	def getMetrics(model: DecisionTreeModel, data: RDD[LabeledPoint]): MulticlassMetrics ={
		val predictionAndLabels = data.map{example =>
			(model.predict(example.features), example.label)
		}
		new MulticlassMetrics(predictionAndLabels)
	}

	//训练模型
//	val model = DecisionTree.trainClassifier(trainData, 7, Map[Int, Int](), "gini", 4, 100)
//	val metrics = getMetrics(model, testData)



//	输出训练模型的准确度
//	(0 until 1).map{
//		cat => (metrics.precision(cat), metrics.recall(cat))
//	}.foreach(println)
//	println(metrics.precision)

//		val evaluation =
//			for(impurity <- Array("gini", "entropy");
//			    depth    <- Array(5, 10, 15, 20, 23);
//			    bins     <- Array(50, 100, 200, 300))
//			yield {
//				val model = DecisionTree.trainClassifier(trainData, 4, Map[Int, Int](), impurity, depth, bins)
//				val predictionAndLabels = cvData.map{example =>
//					(model.predict(example.features), example.label)
//				}
//				val accuracy = new MulticlassMetrics(predictionAndLabels).precision
//				((impurity, depth, bins), accuracy)
//			}
//		evaluation.sortBy(_._2).reverse.foreach(println)

	val forest = RandomForest.trainClassifier(trainData, 4, Map[Int, Int](), 50, "auto", "entropy", 5, 100)

	val predictionAndLabel = testData.map{ point =>
		val score = forest.predict(point.features)
		(score, point.label)
	}

	val fnm = "C:/Users/hp/Desktop/result.txt"
	val writer = new PrintWriter(new File(fnm),"UTF-8")

	//对测试集进行预测

	val printPredict = predictionAndLabel.take(predictionAndLabel.count().toInt)
	println("prediction" + "\t" + "label")
	for(i <- 0 to (printPredict.length - 1)){
		writer.write(printPredict(i)._1 + "\t" + printPredict(i)._2 + "\n")
		println(printPredict(i)._1 + "\t" + printPredict(i)._2)
	}



//	val testCount = predictionAndLabel.count().toInt
//	val trueCount = predictionAndLabel.filter(x => x._1.toInt == x._2.toInt).count()
//	println(testCount)
//	println(trueCount)



	sc.stop()

}
