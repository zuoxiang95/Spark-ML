package algorithm

/**
	* Created by hp on 2017/2/28.
	*/
object LinearRegressionDemo extends App {
  def demoWithSGD(featureMatrix: List[List[Double]], label: List[Double]): Unit ={
	  var theta: List[Double] = List(0.0, 0.0)
	  var loss: Double = 10.0  //定义损失
	  val step: Double = 0.001 //定义随机梯度下降步长

	  //进行迭代
	  for(
		  i <- 0 to featureMatrix.length - 1        //迭代次数
		  if loss > 0.01        //收敛条件损失小于0.01
	  ){
		  var h: Double = 0.0   //h为回归值
		  var erroSum: Double = 0.0     //定义总的回归误差
		  for(j <- 0 to (featureMatrix(1).length - 1)){
			  h += featureMatrix(i)(j) * theta(j)  //计算回归值
		  }
		  erroSum += (h - label(i))  //计算回归值与真实值的差值
		  var newTheta: List[Double] = List()

		  for(j <- 0 to (featureMatrix(1).length - 1)){
			  //梯度下降法中更新权向量的值
			  val cacheTheta = theta(j) + step * erroSum * featureMatrix(i)(j)
			  newTheta = newTheta :+ cacheTheta
		  }
		  newTheta foreach println
		  println("errorSum: " + erroSum)
		  theta = newTheta
		  //更新误差
		  var currentLoss: Double = 0
		  for(m <- 0 to featureMatrix.length - 1) {
			  var sum: Double = 0
			  for (n <- 0 to featureMatrix(1).length - 1) {
				  sum += featureMatrix(m)(n) * theta(n)
			  }
			  currentLoss += (sum - label(m)) * (sum - label(m))
		  }
		  loss = currentLoss
		  println("The equation loss ==>>>>> " + loss + ", i ==>>>>" + i)
	  }
  }

	val data: List[List[Double]] = List(List(1, 4), List(2, 5), List(5, 1), List(4, 2))
	val label: List[Double] = List(19, 26, 19, 20)
	demoWithSGD(data, label)
}
