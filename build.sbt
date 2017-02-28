name := "Spark-ML"

version := "1.0"

scalaVersion := "2.10.6"

javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled", "-Djna.nosys=true")

resolvers ++= Seq(
	"maven Repository" at "http://repository.cloudera.com/artifactory/cloudera-repos/"
)

libraryDependencies ++= Seq(
	"org.apache.spark" %  "spark-core_2.10"      % "2.0.0",
	"org.apache.spark" %  "spark-sql_2.10"       % "2.0.0",
	"org.apache.spark" %  "spark-streaming_2.10" % "2.0.0",
	"org.apache.spark" %  "spark-mllib_2.10"     % "2.0.0",
	"org.postgresql"   %  "postgresql"           % "9.4-1201-jdbc41"
)

licenses := Seq("Apache License 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))