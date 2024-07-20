package scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val sparkSession = 
        SparkSession.builder()
                    .appName("stockMarketParser")
                    .master("local[*]") //only runs on PC, with * threads
                    .config("spark.driver.bindAddress", "127.0.0.1") //spark flips out without a default bind address
                    .config("spark.driver.extraJavaOptions", "-Dlog4j.configuration=file:project/log4j.properties")
                    .getOrCreate()

    sparkSession.sparkContext.setLogLevel("DEBUG")

    val dataFile = "data/aaplHistory.csv"
    val dataframe = sparkSession.read
                                .option("header", value = true) //indicates we have a header in our file
                                .option("lineSep", "\r\n") //indicates that rows are separated by \r\n
                                .option("delimiter", ",") //separates the columns
                                .csv(dataFile)
    
    //add a new column to indicate the stock. lit for literal value
    val dataframeWithStock = dataframe.withColumn("Stock", lit("AAPL"))

    dataframeWithStock.printSchema()
    dataframeWithStock.show()

    //now that's all good, but then we want to send the data to a database...
    val jdbcHostname = "127.0.0.1"
    val jdbcPort = 57800
    val jdbcDatabase = "sparkTutorial"
    val jdbcUsername = "root"
    val jdbcPassword = "*******"

    val jdbcUrl = s"jdbc:mariadb://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}"

    val connectionProperties = new java.util.Properties()
    connectionProperties.put("user", jdbcUsername)
    connectionProperties.put("password", jdbcPassword)

    //Write DataFrame to MariaDB
    val tableName = "stockData"
    //only use write without mode function if you are creating a new table
    //otherwise, add the mode("append") function call
    dataframeWithStock.write
                      .mode("append")
                      .jdbc(jdbcUrl, tableName, connectionProperties)
    //dataframeWithStock.write.saveAsTable(s"${jdbcDatabase}.${tableName}")
    
    sparkSession.stop()
  }
}