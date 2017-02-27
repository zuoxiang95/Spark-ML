name := "Spark-ML"

version := "1.0"

scalaVersion := "2.10.6"

resolvers ++= Seq(
	"maven Repository" at "http://repository.cloudera.com/artifactory/cloudera-repos/"
//	"maven Repository" at "http://repo.hortonworks.com/content/repositories/releases/"
)

libraryDependencies ++= Seq(
	"org.apache.spark" %  "spark-core_2.10"      % "2.0.0",
	"org.apache.spark" %  "spark-sql_2.10"       % "2.0.0",
	"org.apache.spark" %  "spark-streaming_2.10" % "2.0.0",
	"org.apache.spark" %  "spark-mllib_2.10"     % "2.0.0",
	"org.postgresql"   %  "postgresql"           % "9.4-1201-jdbc41"
)
    